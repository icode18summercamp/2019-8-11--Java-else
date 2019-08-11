package com.zhouxin.training.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * TCP单对单通信的客户端
 * 建立客户端的步骤：
 * 第一步：通过new Socket(String host, int port)来创建一个套接字，用来监听指定ip地址的指定端口号
 * 第二步：建立输入输出流
 * 第三步：进行数据通信
 * 第四步：关闭输入输出流以及套接字
 *
 * @author ZhouXin create on 2018/07/29
 */
public class TCP_OneToOne_TXClient
{
    public static void main(String[] args) throws IOException
    {
        final int PORT = 10086;
        final String IP = "127.0.0.1";
        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        Scanner input = new Scanner(System.in);

        try
        {
            socket = new Socket(IP, PORT);

            while (true)
            {
                reader = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream());

                String message = reader.readLine();
                System.out.println("服务端：" + message);
                String sendMessage = input.nextLine();
                System.out.println("客户端：" + sendMessage);
                writer.println(sendMessage);

                if (sendMessage.equals("exit"))
                {
                    socket.close();
                    break;
                }
                writer.flush();
                // writer.close();
                // reader.close();
            }
        } catch (IOException e)
        {
            System.out.println("客户端断开连接");
            // e.printStackTrace();
        } finally
        {
            if (writer != null)
            {
                writer.close();
            }

            if (reader != null)
            {
                reader.close();
            }

            if (socket != null)
            {
                socket.close();
            }

            input.close();
        }
    }
}
