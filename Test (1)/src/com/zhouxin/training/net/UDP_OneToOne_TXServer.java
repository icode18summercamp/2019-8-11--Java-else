package com.zhouxin.training.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_OneToOne_TXServer
{
    public static void main(String[] args) throws IOException
    {
        final int PORT = 10086;
        DatagramSocket serverSocket = null;
        DatagramPacket packet = null;
        serverSocket = new DatagramSocket(8888);
        String str = "来自服务器的消息";
        byte[] toSend = str.getBytes();
        packet = new DatagramPacket(toSend, toSend.length, InetAddress.getByName("localhost"), PORT);
        System.out.println("服务端正在发送信息.......");
        serverSocket.send(packet);
        System.out.println("发送完毕........");
        serverSocket.close();
    }
}
