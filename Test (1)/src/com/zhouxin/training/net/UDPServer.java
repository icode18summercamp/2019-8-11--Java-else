package com.zhouxin.training.net;

import java.net.*;
import java.io.*;

/**
 * UDP协议的一次性通信
 * 
 * @author Zhouxin create on 2018/07/17
 */
public class UDPServer
{
	public static void main(String[] args)
	{
		try
		{
			InetAddress ip = InetAddress.getLocalHost();
			int port = 8888;
			// 创建接收方的套接字，并指定端口号和IP地址
			DatagramSocket getSocket = new DatagramSocket(port, ip);
			// 确定数据报接收的数据的数组大小
			byte[] buf = new byte[1024]; // 创建接收类型的数据报，数据将存储在buf中
			DatagramPacket getPacket = new DatagramPacket(buf, buf.length); // 通过套接字接收数据
			getSocket.receive(getPacket);
			String getMes = new String(buf, 0, getPacket.getLength());
			System.out.println("对方发送的消息是：" + getMes);

			InetAddress sendIP = getPacket.getAddress();
			int sendPort = getPacket.getPort();
			System.out.println("对方的地址是：" + sendIP.getHostAddress() + ": "
					+ sendPort);

			// 通过数据报得到的发送方的套接字地址
			SocketAddress sendIp = getPacket.getSocketAddress();
			String feedback = "接收方说： 我收到了";
			byte[] backBuf = feedback.getBytes();

			DatagramPacket sendPacket = new DatagramPacket(backBuf,
					backBuf.length, sendIp);
			getSocket.send(sendPacket);
			getSocket.close();
		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e)
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
