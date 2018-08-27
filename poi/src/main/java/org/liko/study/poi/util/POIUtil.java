package org.liko.study.poi.util;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.hssf.usermodel.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Liko
 * @Description:
 * @Date: Created at 11:26 2018/6/12
 */
public class POIUtil {

    public static String convertExceltoHtml(String path) throws IOException,ParserConfigurationException, TransformerException,InvalidFormatException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
        StringBuffer content = new StringBuffer();
        HSSFWorkbook workbook = new HSSFWorkbook(bis);
        for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
            HSSFSheet aSheet = workbook.getSheetAt(numSheets);// 获得一个sheet
            content.append("/n");
            if (null == aSheet) {
                continue;
            }
            for (int rowNum = 0; rowNum <= aSheet.getLastRowNum(); rowNum++) {
                content.append("/n");
                HSSFRow aRow = aSheet.getRow(rowNum);
                if (null == aRow) {
                    continue;
                }
                for (short cellNum = 0; cellNum <= aRow.getLastCellNum(); cellNum++) {
                    HSSFCell aCell = aRow.getCell(cellNum);
                    if (null == aCell) {
                        continue;
                    }
                    if (aCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        content.append(aCell.getRichStringCellValue()
                                .getString());
                    } else if (aCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                        boolean b = HSSFDateUtil.isCellDateFormatted(aCell);
                        if (b) {
                            Date date = aCell.getDateCellValue();
                            SimpleDateFormat df = new SimpleDateFormat(
                                    "yyyy-MM-dd");
                            content.append(df.format(date));
                        }
                    }
                }
            }
        }

        return content.toString();
    }
}
