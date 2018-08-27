package org.liko.study.poi.controller;

import org.liko.study.poi.util.POIUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URL;

/**
 * @Author: Liko
 * @Description:
 * @Date: Created at 11:24 2018/6/12
 */
@RestController
public class ExcelToHtml {

    @RequestMapping("excel2html")
    public String excelToHtml() throws ParserConfigurationException, TransformerException, IOException {
        URL resource = this.getClass().getResource("/file/test.xlsx");
        return POIUtil.convertExceltoHtml(resource.getPath());
    }
}
