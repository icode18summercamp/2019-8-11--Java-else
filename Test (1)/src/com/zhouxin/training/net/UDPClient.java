package com.zhouxin.training.net;

import java.net.*;
import java.io.*;

public class UDPClient
{
    public static void main(String[] args)
    {
        try
        {
            // 创建发送方的套接字，IP默认为本地，端口号随机
            DatagramSocket sendSocket = new DatagramSocket();
            // 确定要发送的消息
            String message = "你好，接收方!";
            // 由于数据报中传输的数据是以字节数组的形式存储的，所以要把字符串转换为字节数组
            byte[] buf = message.getBytes();
            // 确定发送方的IP地址及端口号，地址为本地机器地址
            int port = 8888;
            InetAddress ip = InetAddress.getLocalHost();
            // 创建发送类型的数据报
            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, ip,
                    port);
            // 通过套接字发送数据
            sendSocket.send(sendPacket);
            // 确定接收反馈数据的缓冲存储器，即存储数据的字节数组
            byte[] getBuf = new byte[1024];
            // 创建接收类型的数据报
            DatagramPacket getPacket = new DatagramPacket(getBuf, getBuf.length);
            // 通过套接字接收数据
            sendSocket.receive(getPacket);
            // 解析反馈消息，并打印
            String backMes = new String(getBuf, 0, getPacket.getLength());
            System.out.println("接收方返回的消息：" + backMes);

            // 关闭套接字
            sendSocket.close();
        } catch (SocketException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnknownHostException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
