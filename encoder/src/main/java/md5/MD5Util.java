package md5;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String md5Encode(String in) {
        StringBuffer out = new StringBuffer();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = in.getBytes("UTF-8");
            byte[] md5Bytes = md5.digest(bytes);
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    out.append("0");
                }
                out.append(Integer.toHexString(val));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) throws IOException {
//        String test = "liko";
        String path = "C:\\Users\\liko\\Desktop\\IBBondTransfer.java";
        File file = new File(path);
        String test = IOUtils.toString(new FileReader(file));
        System.out.println("in : " + test);
        System.out.println("out : " + md5Encode(test));
    }
}
