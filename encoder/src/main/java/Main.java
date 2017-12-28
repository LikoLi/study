import encoder.Encoder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===> Welcome to encode file util!");
        System.out.println("===> Please input int key:");
        int key = scanner.nextInt();
        URL resource = Main.class.getClassLoader().getResource("");
        File dir = new File(resource.getPath());
        try {
            Encoder.encodeAll(dir, key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

}
