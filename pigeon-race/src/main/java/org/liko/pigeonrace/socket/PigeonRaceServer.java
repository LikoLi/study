package org.liko.pigeonrace.socket;

import org.liko.pigeonrace.thread.PigeonRaceThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: Liko
 * @Description:
 * @Date: Created at 17:23 2018/7/27
 */
public class PigeonRaceServer {

    private static final int PORT = 9898;
    private static boolean IS_CONTINUE = true;

    public static void init() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Socket bind at port: " + PORT);
        while (IS_CONTINUE) {
            Socket socket = serverSocket.accept();
            PigeonRaceThread thread = new PigeonRaceThread(socket);
            thread.start();
        }
    }

    public static void setIsContinue(boolean isContinue) {
        IS_CONTINUE = isContinue;
    }
}
