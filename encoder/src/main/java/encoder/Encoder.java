package encoder;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.UUID;

public class Encoder {
    private static int dataOfFile = 0;

    private static void encodeFile(File srcFile, int key) throws IOException {
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("Source file not exist!");
        }
        File tempFile = File.createTempFile("encodeFile", UUID.randomUUID().toString());
        InputStream fis = new FileInputStream(srcFile);
        OutputStream fos = new FileOutputStream(tempFile);
        while ((dataOfFile = fis.read()) > -1) {
            fos.write(dataOfFile^key);
        }
        fis.close();
        fos.flush();
        fos.close();
        FileUtils.copyFile(tempFile, srcFile);
    }

    public static void encodeAll(File file, int key) throws IOException {
        if (file.isFile()) {
            encodeFile(file, key);
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file_ : files) {
                encodeAll(file_, key);
            }
        }
    }

}
