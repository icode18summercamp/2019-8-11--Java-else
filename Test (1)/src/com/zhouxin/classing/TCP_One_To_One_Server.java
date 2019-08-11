package com.zhouxin.classing;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCP_One_To_One_Server
{
    public static void main(String[] args)
    {
        try
        {
            // 监听端口号
            ServerSocket server = new ServerSocket(10086);
            System.out.println("等待用户接入.......");
            // 等待用户接入
            Socket socket = server.accept();
            // 获取输入输出流
            InputStream receive = socket.getInputStream();
            OutputStream send = socket.getOutputStream();

            while (true)
            {
                byte[] messageByte = new byte[1024];
                // 吧接收到的信息放入messageByte的字节数组中
                int result = receive.read(messageByte);
                if (result < 0)
                {
                    System.out.println("客户端没有发送任何东西");
                }

                // 把messageByte转换成 字符串
                String message =new String(messageByte);
                System.out.println("客户端说：" + message);
                Scanner input = new Scanner(System.in);
                String sendMessage = input.nextLine();
                byte[] sendMessageByte = sendMessage.getBytes();
                send.write(sendMessageByte);
                if (sendMessage.equals("exit"))
                {
                    break;
                }
                send.flush();
            }
            System.out.println("dddddddd");
            receive.close();
            send.close();
            server.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
