package com.zhouxin.training.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDP_OneToOne_TXClient
{
    public static void main(String[] args)
    {
        final int PORT = 10086;
        DatagramSocket socket = null;
        DatagramPacket packet = null;
        byte[] buf = new byte[1024];

        try
        {
            socket = new DatagramSocket(PORT);
            packet = new DatagramPacket(buf, 1024);
            System.out.println("客户端等待接收信息中............");
            socket.receive(packet);
            String receiveMessage = new String(packet.getData(), 0, packet.getLength());
            System.out.println("客户端：" + receiveMessage);

            socket.close();
        } catch (SocketException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
