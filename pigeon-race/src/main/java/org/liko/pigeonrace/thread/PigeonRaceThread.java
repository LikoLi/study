package org.liko.pigeonrace.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Author: Liko
 * @Description:
 * @Date: Created at 17:27 2018/7/27
 */
public class PigeonRaceThread extends Thread {

    private Socket socket;

    public PigeonRaceThread(Socket socker) {
        this.socket = socker;
    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            // TODO: 2018/7/27 logger
            e.printStackTrace();
        }
    }
}
