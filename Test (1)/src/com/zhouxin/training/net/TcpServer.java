package com.zhouxin.training.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP协议的服务端
 *
 * @author 周鑫 create on 2018/07/14
 */
public class TcpServer
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket server = new ServerSocket(9933);

		// 阻塞状态，等待连接
		Socket socket = server.accept();


		InputStreamReader reader = new InputStreamReader(socket.getInputStream());
		BufferedReader buffer_reader = new BufferedReader(reader);
		PrintWriter writer = new PrintWriter(socket.getOutputStream());

		String request = buffer_reader.readLine();

		System.out.println("Client say: " + request);
		String message = "Hello, too!";
		writer.println(message);
		writer.flush();
		writer.close();

		buffer_reader.close();
		socket.close();
		server.close();
	}
}
