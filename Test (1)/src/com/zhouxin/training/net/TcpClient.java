package com.zhouxin.training.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 * TCP协议的客户端
 *
 * @author 周鑫 create on 2018/07/14
 */
public class TcpClient {
    public static void main(String[] args) throws UnknownHostException,
            IOException {
        Socket socket = new Socket("127.0.0.1", 9933);

        InputStreamReader reader = new InputStreamReader(
                socket.getInputStream());
        BufferedReader buffer_reader = new BufferedReader(reader);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        String message = "Hello, client!";
        writer.println(message);
        writer.flush();

        String accept = buffer_reader.readLine();
        System.out.println("Server say: " + accept);

        writer.close();
        buffer_reader.close();
        socket.close();
    }
}
