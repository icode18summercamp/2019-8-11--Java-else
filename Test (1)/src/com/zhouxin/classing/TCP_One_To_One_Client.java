package com.zhouxin.classing;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCP_One_To_One_Client
{
    public static void main(String[] args)
    {
        try
        {
            Socket socket = new Socket("localhost", 10086);
            InputStream receive = socket.getInputStream();
            OutputStream send = socket.getOutputStream();

           while (true)
           {
               byte[] temp = new byte[1024];
               Scanner input = new Scanner(System.in);
               String sendMessage = input.nextLine();
               temp = sendMessage.getBytes();
               send.write(temp);
               send.flush();
               if (sendMessage.equals("exit"))
               {
                   break;
               }

               temp = new byte[1024];
               receive.read(temp);
               String message = new String(temp);
               System.out.println("服务端说：" + message);
           }

            System.out.println("点点滴滴都是");
            receive.close();
            send.close();
            socket.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
