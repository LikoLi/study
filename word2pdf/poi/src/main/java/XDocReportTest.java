import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * XDocReportTest
 *
 * @author liko
 * @date 2021/3/10
 */
public class XDocReportTest {
    public static void main(String[] args) throws IOException {
        XWPFDocument doc = new XWPFDocument(new FileInputStream("赎回东方汇智--6期.docx"));
        PdfOptions options = PdfOptions.create();
        PdfConverter.getInstance().convert(doc, new FileOutputStream("template.pdf"), options);
    }
}
