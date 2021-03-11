package com.iquantex.com;

import com.documents4j.api.DocumentType;
import com.documents4j.job.ConverterAdapter;
import com.documents4j.job.RemoteConverter;
import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.documents4j.remote.Documents4jRemoteServices;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Execute Hook ...");
            }
        }));

//        File file = new File("test.pdf");
//
//
//        WordprocessingMLPackage wmlp = WordprocessingMLPackage.load(new File("量化小F-涵德投资-基金份额追加申购申请表 - 产品.docx"));
//
//
//        Docx4J.toPDF(wmlp, new FileOutputStream(file));



        Docx4jProperties.setProperty("docx4j.convert.out.documents4j.remote.Uri", "http://192.168.31.98:9998");
        Documents4jRemoteServices exporter = new Documents4jRemoteServices();
        File docxDir = new File("docx");
        Arrays.stream(docxDir.listFiles()).forEach(file -> {
            try {
                File output = new File("pdf" + File.separator + file.getName() + ".pdf");
                FileOutputStream fos = new FileOutputStream(output);
                exporter.export(file , fos, DocumentType.MS_WORD);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        });

        System.exit(0);

    }
}
