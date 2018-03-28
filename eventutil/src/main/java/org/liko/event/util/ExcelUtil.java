package org.liko.event.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Liko on 2018/1/31
 *
 */
public class ExcelUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private final HSSFWorkbook workbook;
    private final Map<String, String> mapping;
    private final String PATH = "T2.xls";
    private final Map<Integer, Map<Integer, String>> title = new HashMap<>();
    private Integer rowNum = 0;
    /**
     * 1. 正常情况我们把sheet name当做class name 使用
     * 2. sheet name 最大31个字符, 当class name 大于31个字符时, 我们需要在sheet name为mapping_的sheet中定义mapping关系
     */
    private static final String MAPPING_SHEET_NAME = "mapping_";

    public ExcelUtil() {
        workbook = readFile(PATH);
        mapping = getSheetAndClassMapping();
    }
    public ExcelUtil(String path) {
        workbook = readFile(path);
        mapping = getSheetAndClassMapping();
    }

    /**
     * 读取Excel
     * @param path Excel路径
     * @return HSSFWorkbook
     */
    private HSSFWorkbook readFile(String path) {
        URL resource = ExcelUtil.class.getClassLoader().getResource(path);
        try (FileInputStream fis = new FileInputStream(resource.getFile())) {
            return new HSSFWorkbook(fis);
        } catch (IOException e) {
            throw new RuntimeException(path + ", 此目录下的测试数据不存在！");
        }
    }

    /**
     * 读取sheet name和class name的映射关系
     * @return Sheet name 和class name的映射关系
     */
    private Map<String, String> getSheetAndClassMapping() {
        HSSFSheet sheet = workbook.getSheet(MAPPING_SHEET_NAME);
        Map<String, String> mapping = new HashMap<>();
        if (sheet != null) {
            mapping = convertSheet2Map(sheet);
        }
        return mapping;
    }

    /**
     * 将sheet转成Map<String, String>
     * @param sheet
     * @return Map<String, String>
     */
    private Map<String, String> convertSheet2Map(HSSFSheet sheet) {
        Map<String, String> result = new HashMap<>();
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            HSSFRow row = sheet.getRow(i);
            result.put(row.getCell(0).getStringCellValue(), row.getCell(i).getStringCellValue());
        }
        return result;
    }

    public <T> List<T> getClassByType(T t) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        rowNum = 0;
        title.clear();
        String className = t.getClass().getSimpleName();
        String sheetName = mapping.get(className);
        if (StringUtils.isEmpty(sheetName)) {
            sheetName = className;
        }
        return getInstanceBySheetNameAndType(sheetName, t);
    }

    public <T> List<T> getInstanceBySheetNameAndType(String sheetName, T t) throws IllegalAccessException, InstantiationException, NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        title.clear();
        rowNum = 0;
        HSSFSheet sheet = workbook.getSheet(sheetName);
        List result = new ArrayList();
        for (; rowNum <= sheet.getLastRowNum(); rowNum++) {
            T instance = getInstance(sheet, 0, rowNum, t);
            result.add(instance);
        }
        return result;
    }

    private <T> void setValue(T instance, HSSFCell cell, String fieldName) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Field field = getField(instance.getClass(), fieldName);
        field.setAccessible(true);
        Class<?> type = field.getType();
        if (type.getName().equals(String.class.getName())) {
            // 设置单元格类型, 避免[Cannot get a STRING value from a NUMERIC cell] 这个异常
            cell.setCellType(CellType.STRING);
            String cellValue = cell.getRichStringCellValue().getString();
            if (cellValue.equals("\"\"")) {
                cellValue = "";
            }
            field.set(instance, cellValue);
        } else if (type.getName().equals(Integer.class.getName()) || type.getName().equals(int.class.getName())) {
            cell.setCellType(CellType.NUMERIC);
            field.set(instance, ((Double)cell.getNumericCellValue()).intValue());
        } else if (type.getName().equals(Double.class.getName()) || type.getName().equals(double.class.getName())) {
            double cellValue;
            try {
                cell.setCellType(CellType.NUMERIC);
                cellValue = cell.getNumericCellValue();
            } catch (IllegalStateException e) {
                // 解决科学计数法异常
                BigDecimal bigDecimal = new BigDecimal(cell.getStringCellValue());
                cellValue = bigDecimal.doubleValue();
            }
            field.set(instance, cellValue);
        } else if (type.getName().equals(Long.class.getName()) || type.getName().equals(long.class.getName())) {
            cell.setCellType(CellType.NUMERIC);
            field.set(instance, ((Double)cell.getNumericCellValue()).longValue());
        } else if (type.getName().equals(List.class.getName())) {
            cell.setCellType(CellType.STRING);
            String cellValue = cell.getStringCellValue();
            // <bondPosList:CalcAssetBondPosListOutProp:6:7>
            if (cellValue.startsWith("<") && cellValue.endsWith(">")) {
                List subResult = new ArrayList();
                String str = cellValue.substring(1, cellValue.length() - 1);
                String[] split = str.split(":");
                String sheetName = split[0];
                String className = mapping.get(sheetName);
                if (StringUtils.isEmpty(className)) {
                    className = sheetName;
                }
                Class<?> subClass = Class.forName(className);
                Object subInstanceNoValue = subClass.newInstance();

                Integer titleLine = Integer.valueOf(split[1]);

                String[] valueLines = split[2].split(",");
                for (int i = 0; i < valueLines.length; i++) {
                    Object subInstance = getInstance(cell.getSheet(), titleLine - 1, Integer.valueOf(valueLines[i]) - 1, subInstanceNoValue);
                    rowNum++;
                    subResult.add(subInstance);
                }
                field.set(instance, subResult);
            } else if (cellValue.equals("null")) {
                field.set(instance, new ArrayList<>());
            } else {
                throw new RuntimeException("Error Cell Value.");
            }
        } else {
            logger.error("----------------------DATA TYPE<{" + type + "}> ERROR!!!----------------------");
        }
    }

    private Map<Integer, String> getTitle(HSSFSheet sheet, int rowNum) {
        Map<Integer, String> map = new HashMap<>();
        HSSFRow row = sheet.getRow(rowNum);
        for (int i = 0; i < row.getLastCellNum(); i++) {
            HSSFCell cell = row.getCell(i);
            cell.setCellType(CellType.STRING);
            String value = cell.getStringCellValue();
            if (StringUtils.isEmpty(value)) break;//避免行末无效空格
            map.put(i, value);
        }
        return map;
    }

    private Field getField(Class clazz, String fieldName) throws NoSuchFieldException {
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                //这里什么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }
        throw new NoSuchFieldException("No Field Named<{" + fieldName + "}");
    }

    private <T> T getInstance(HSSFSheet sheet, int titleLine, int valueLine, T t) throws IllegalAccessException, InstantiationException, NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        // 1. 获取title
        Map<Integer, String> titles = title.get(titleLine);
        if (titles == null) {
            rowNum++;
            titles = getTitle(sheet, titleLine);
            title.put(titleLine, titles);
        }

        // 2. 获取Instance
        T instance = (T) t.getClass().newInstance();

        // 3. 往instance设置值
        HSSFRow row = sheet.getRow(rowNum);
        for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
            HSSFCell cell = row.getCell(cellNum);
            if (cell == null) continue;
            setValue(instance, cell, titles.get(cellNum));
        }

        return instance;
    }
}
