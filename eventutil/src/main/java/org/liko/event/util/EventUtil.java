package org.liko.event.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.liko.event.dao.java.EventfielddefMapper;
import org.liko.event.dao.java.EventgroupdefMapper;
import org.liko.event.dao.java.EventstructdefMapper;
import org.liko.event.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 事件定义工具
 */
@Service
public class EventUtil {

    private static final Logger logger = LoggerFactory.getLogger(EventUtil.class);

    @Autowired
    private EventstructdefMapper eventstructdefMapper;

    @Autowired
    private EventgroupdefMapper eventgroupdefMapper;

    @Autowired
    private EventfielddefMapper eventfielddefMapper;

    // 默认事件定义文件
    private static final String PATH = "Event.xls";

    /**
     * 根据Excel 插入/更新事件定义
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws NoSuchFieldException
     */
    @Transactional
    public void generator() throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException, NoSuchFieldException {

        // 1.创建ExcelUtil
        ExcelUtil excelUtil = new ExcelUtil(PATH);

        // 2.从excel中获取事件定义表数据, 并过滤无效数据
        List<EventstructdefExt> eventstructdefExtList = excelUtil.getInstanceBySheetNameAndType(EventstructdefExt.class.getSimpleName(), new EventstructdefExt())
                .stream()
                .filter(eventstructdefExt -> StringUtils.isNotEmpty(eventstructdefExt.getGroupName()))
                .collect(Collectors.toList());

        // 3.从excel中获取事件字段表数据
        List<EventfielddefExt> eventfielddefExtList = excelUtil.getInstanceBySheetNameAndType(EventfielddefExt.class.getSimpleName(), new EventfielddefExt())
                .stream()
                .filter(eventfielddefExt -> StringUtils.isNotEmpty(eventfielddefExt.getName()))
                .collect(Collectors.toList());

        // 4.获取外部事件定义表中的gourp name
        Set<String> groupName = new HashSet<>();
        // 5.获取外部事件定义表中的event name
        List<String> strucEventName = new ArrayList<>();
        eventstructdefExtList.stream().forEach(eventstructdefExt -> {
            groupName.add(eventstructdefExt.getGroupName());
            strucEventName.add(eventstructdefExt.getName());
        });

        // 6.根据group name 查询 group id
        List<Eventgroupdef> eventgroupdefs = eventgroupdefMapper.selectByNames(DBUtil.toSqlString(groupName.stream().collect(Collectors.toList())));

        // 7.根据event name 查询 event id
        List<Eventstructdef> eventstructdefs = eventstructdefMapper.selectByNames(DBUtil.toSqlString(strucEventName));

        // 8.将group name 和 group id 转成map
        Map<String, Integer> groupId = eventgroupdefs.stream().collect(Collectors.toMap(Eventgroupdef::getName, Eventgroupdef::getId));

        // 9.将event name 和 event id 转成map
        Map<String, Integer> eventNameIdMap = eventstructdefs.stream().collect(Collectors.toMap(Eventstructdef::getName, Eventstructdef::getId));

        // 10.获取外部字段表中的event name 和 element name
        Set<String> eventName = new HashSet<>();
        eventfielddefExtList.stream().forEach(eventfielddefExt -> {
            eventName.add(eventfielddefExt.getEventName());
            eventName.add(eventfielddefExt.getElementName());
        });

        // 11.查询所有的事件定义数据
        List<Eventstructdef> eventstructdefList = eventstructdefMapper.selectAll();

        // 12.将事件定义数据转成map
        Map<String, Integer> eventId = eventstructdefList
                .stream()
                .filter(eventstructdef -> eventName.contains(eventstructdef.getName()))
                .collect(Collectors.toMap(Eventstructdef::getName, Eventstructdef::getId));

        // 13.删除所有事件定义表中所有事件关联的字段
        List<String> ids = new ArrayList<>();
        eventNameIdMap.values().stream().forEach(id -> ids.add(id.toString()));
        eventfielddefMapper.deleteByIds(DBUtil.toSqlString(ids));

        // 14.保存/更新事件定义表
        Iterator<EventstructdefExt> eventstructdefExtIterator = eventstructdefExtList.iterator();
        while (eventstructdefExtIterator.hasNext()) {
            Eventstructdef eventstructdef = new Eventstructdef();
            EventstructdefExt eventstructdefExt = eventstructdefExtIterator.next();
            BeanUtils.copyProperties(eventstructdefExt, eventstructdef);
            eventstructdef.setGroupid(groupId.get(eventstructdefExt.getGroupName()));
            Integer id = eventId.get(eventstructdefExt.getName());
            logger.debug("Get eventId from map ==> id = " + id);
            eventstructdef.setId(id == null || id == 0 ? null : id);
            DBUtil.saveOrUpdate(eventstructdefMapper, eventstructdef);
        }

        // 15.更新event id
        eventstructdefList = eventstructdefMapper.selectAll();
        eventId = eventstructdefList.stream().filter(eventstructdef -> eventName.contains(eventstructdef.getName())).collect(Collectors.toMap(Eventstructdef::getName, Eventstructdef::getId));

        // 16.插入新的事件字段定义
        Iterator<EventfielddefExt> eventfielddefExtIterator = eventfielddefExtList.iterator();
        while (eventfielddefExtIterator.hasNext()) {
            EventfielddefExt eventfielddefExt = eventfielddefExtIterator.next();
            Eventfielddef eventfielddef = new Eventfielddef();
            BeanUtils.copyProperties(eventfielddefExt, eventfielddef);
            eventfielddef.setEventid(eventId.get(eventfielddefExt.getEventName()));
            eventfielddef.setElementid(eventId.get(eventfielddefExt.getElementName()));
            DBUtil.saveOrUpdate(eventfielddefMapper, eventfielddef);
        }
    }

    /**
     * 根据事件id生成excel
     * @param eventId
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IOException
     */
    public void getEvent(int eventId) throws InvocationTargetException, IllegalAccessException, IOException {
        Eventstructdef eventstructdef = eventstructdefMapper.selectByPrimaryKey(eventId);
        Eventgroupdef eventgroupdef = eventgroupdefMapper.selectByPrimaryKey(eventstructdef.getGroupid());
        List<Eventfielddef> eventfielddefs = eventfielddefMapper.selectBy(Eventfielddef.f_eventid, eventstructdef.getId());

        List<Eventstructdef> allEventstructdef = eventstructdefMapper.selectAll();
        Map<Integer, String> eventIdNameMapping = allEventstructdef.stream().collect(Collectors.toMap(Eventstructdef::getId, Eventstructdef::getName));

        EventstructdefExt eventstructdefExt = new EventstructdefExt();
        BeanUtils.copyProperties(eventstructdef, eventstructdefExt);
        eventstructdefExt.setGroupName(eventgroupdef.getName());

        List<EventfielddefExt> eventfielddefExts = new ArrayList<>();
        eventfielddefs.stream().forEach(eventfielddef -> {
            EventfielddefExt eventfielddefExt = new EventfielddefExt();
            BeanUtils.copyProperties(eventfielddef, eventfielddefExt);
            eventfielddefExt.setEventName(eventIdNameMapping.get(eventfielddef.getEventid()));
            eventfielddefExt.setElementName(eventIdNameMapping.get(eventfielddef.getElementid()));
            eventfielddefExts.add(eventfielddefExt);
        });

        List<List<String>> eventstructdefExtValue = obj2List(eventstructdefExt);
        List<List<String>> eventfielddefExtValue = obj2List(eventfielddefExts);

        HSSFWorkbook workbook = new HSSFWorkbook();
        writeEventstructdefExt2Excel(workbook, eventstructdefExtValue);
        writeEventfielddefExt2Excel(workbook, eventfielddefExtValue);

        URL resource = EventUtil.class.getClassLoader().getResource("");

        File file = new File(resource.getFile() + "/" + eventstructdef.getName() + ".xls");
        logger.info("Generate file <{}>", file.getAbsolutePath());
        workbook.write(file);
    }

    private void writeEventfielddefExt2Excel(HSSFWorkbook workbook, List<List<String>> eventfielddefExtValue) {
        HSSFSheet sheet = workbook.createSheet(EventfielddefExt.class.getSimpleName());
        writeList2Sheet(sheet, eventfielddefExtValue);
    }

    private void writeEventstructdefExt2Excel(HSSFWorkbook workbook, List<List<String>> eventstructdefExtValue) {
        HSSFSheet sheet = workbook.createSheet(EventstructdefExt.class.getSimpleName());
        writeList2Sheet(sheet, eventstructdefExtValue);
    }

    private void writeList2Sheet(HSSFSheet sheet, List<List<String>> values) {
        for (int i = 0; i < values.size(); i++) {
            HSSFRow row = sheet.createRow(i);
            List<String> value = values.get(i);
            for (int j = 0; j < value.size(); j++) {
                row.createCell(j).setCellValue(value.get(j));
            }
        }
    }

    private List<List<String>> obj2List(Object obj) throws InvocationTargetException, IllegalAccessException {
        List<List<String>> result = new ArrayList<>();
        if (obj instanceof EventstructdefExt) {
            obj = (EventstructdefExt)obj;
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            List<String> title = new ArrayList<>();
            List<String> values = new ArrayList<>();
            for (Field field : declaredFields) {
                title.add(field.getName());
                Method getterMethod = getGetterMethod(obj, field);
                String value = getterMethod.invoke(obj).toString();
                values.add(value);
            }
            result.add(title);
            result.add(values);
        } else if (obj instanceof List) {
            obj = (List<EventfielddefExt>) obj;
            List<Field> fields = new ArrayList<>();
            List<String> title = new ArrayList<>();
            Arrays.stream(EventfielddefExt.class.getDeclaredFields()).forEach(field -> {
                fields.add(field);
                title.add(field.getName());
            });
            result.add(title);

            ((List) obj).stream().forEach(eventfielddefExt -> {
                List<String> values = new ArrayList<>();
                fields.stream().forEach(field -> {
                    Method getterMethod = getGetterMethod(eventfielddefExt, field);
                    try {
                        Object value = getterMethod.invoke(eventfielddefExt);
                        values.add(value == null ? "" : value.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                result.add(values);
            });
        }
        return result;
    }

    private Method getGetterMethod(Object obj, Field field) {
        Method result = null;
        Method[] declaredMethods = obj.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            String methodName = method.getName().toLowerCase();
            String fieldName = field.getName().toLowerCase();
            if (methodName.equals("get"+ fieldName)) {
                result = method;
            } else if (methodName.equals("is"+ fieldName)) {
                result = method;
            }
        }
        return result;
    }

}
