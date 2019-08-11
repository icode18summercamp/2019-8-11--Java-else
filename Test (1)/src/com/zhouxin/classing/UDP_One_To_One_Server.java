package com.zhouxin.classing;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDP_One_To_One_Server
{
    public static void main(String[] args)
    {
        try
        {
            DatagramSocket server = new DatagramSocket(10086,
                    InetAddress.getLocalHost());
            while (true)
            {
                byte[] buf = new byte[1024];

                DatagramPacket getPacket = new DatagramPacket(buf, buf.length);
                server.receive(getPacket);
                String message = new String(buf);
                System.out.println("客户端小刘说：" + message);

                Scanner input = new Scanner(System.in);
                String sendMessage = input.nextLine();
                byte[] backBuf = sendMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(backBuf,
                        backBuf.length, getPacket.getSocketAddress());
                server.send(sendPacket);
                if (sendMessage.equals("exit"))
                {
                    break;
                }
            }
            server.close();
        } catch (SocketException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
