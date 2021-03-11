package org.apache.poi.xwpf.converter.core.styles.run;

import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.convert.out.pdf.viaXSLFO.Conversion;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.*;

/**
 * OtherTest
 *
 * @author liko
 * @date 2021/3/10
 */
public class OtherTest {

    public static void main(String[] args) throws FileNotFoundException, Docx4JException {

        InputStream is = new FileInputStream(new File(
                "赎回东方汇智--6期.docx"));
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                .load(is);


        PdfSettings pdfSettings = new PdfSettings();


        OutputStream out = new FileOutputStream("ceshi3.pdf");
        PdfConversion converter = new Conversion(wordMLPackage);
        converter.output(out, pdfSettings);
    }
}
