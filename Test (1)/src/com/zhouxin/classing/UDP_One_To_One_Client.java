package com.zhouxin.classing;

import com.zhouxin.training.annonation.Column;
import com.zhouxin.training.flection.model.Person;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDP_One_To_One_Client
{
    public static void main(String[] args)
    {
        try
        {
            DatagramSocket client = new DatagramSocket();
            while (true)
            {
                System.out.println("woswhi 客户端" +
                        "");
                byte[] buf = new byte[1024];
                Scanner input = new Scanner(System.in);
                String sendMessage = input.nextLine();
                buf = sendMessage.getBytes();
                DatagramPacket packet = new DatagramPacket(buf,
                        buf.length,InetAddress.getLocalHost(),
                        10086 );
                client.send(packet);
                if (sendMessage.equals("exit"))
                {
                    break;
                }
                buf = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
                client.receive(receivePacket);
                String message = new String(receivePacket.getData(),
                        0,receivePacket.getLength());
                System.out.println("服务端小杨说：" + message);
            }

            client.close();

        } catch (SocketException e)
        {
            e.printStackTrace();
        } catch (UnknownHostException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

















    class Teacher
    {
        @Column(value = "全限定名称")
        private Person person;
    }

    class Cat
    {
        @Column(value = "全限定名称")
        private Person person;
    }
}
