package org.liko.pigeonrace;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.liko.pigeonrace.socket.PigeonRaceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PigeonRaceApplicationTests {

    @Test
    public void contextLoads() throws IOException {
        Socket socket = new Socket("localhost", 9898);
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

        bufferedWriter.write("line one");
        bufferedWriter.newLine();
        bufferedWriter.write("line two");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        socket.close();
    }

}
