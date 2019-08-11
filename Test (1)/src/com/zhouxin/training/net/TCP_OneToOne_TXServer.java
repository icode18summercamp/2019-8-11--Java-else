package com.zhouxin.training.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * TCP单对单的通信服务器
 * create on 2018/07/21
 * <p>
 * <p>
 * 服务器的通信流程
 * 第一步：创建监听端口的ServerSocket对象
 * 第二步：通过ServerSocket.accept()方法来进行堵塞等待客户端的连接
 * 第三步：根据accept()所获取到的客户端的socket信息来获取对应的输入输出流
 * 第四步：在通过对IO流的操作进行数据通信
 *
 * @author 周鑫
 */
public class TCP_OneToOne_TXServer {
    public static void main(String[] args) throws IOException {
        final int PORT = 10086;
        Scanner scanner = new Scanner(System.in);
        ServerSocket server = null;
        Socket socket = null;
        BufferedReader buffer_reader = null;
        PrintWriter writer = null;

        try {
//           监听客户端10086端口，并创建了一个ServerSocket
            server = new ServerSocket(PORT);
            System.out.println("服务已经启动，等待用户的接入.......");
            //调用ServerSocket类中的accept方法，会将线程一直阻塞在这里，知道有一个客户端连接
            socket = server.accept();
            System.out.println(socket.getLocalAddress() + ":" + socket.getPort() + " 接入服务器......");

            while (true) {
                buffer_reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream());

                String sendMessage = scanner.nextLine();
                System.out.println("服务端：" + sendMessage);
                writer.println(sendMessage);
                writer.flush();
                String receiveMessage = buffer_reader.readLine();
                if (receiveMessage.equals("exit")) {
                    break;
                }
                System.out.println("客户端：" + receiveMessage);
//                writer.close();
//                buffer_reader.close();
            }
//            socket.close();
//            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }

            if (buffer_reader != null) {
                buffer_reader.close();
            }

            if (socket != null) {
                socket.close();
            }

            if (server != null) {
                server.close();
            }
            scanner.close();
        }
    }
}
