package com.base.nio.eg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.nio.channels.SocketChannel;

/**
 * @author
 * @Description 服务器端线程池控制的线程类
 */
public class ThreadSocketChannel implements Runnable {
	private SocketChannel socketChannel;

	public ThreadSocketChannel(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	public PrintWriter getWriter(Socket socket) throws Exception {
		return new PrintWriter(socket.getOutputStream(), true);
	}

	public BufferedReader getReader(Socket socket) throws Exception {
		InputStreamReader in = new InputStreamReader(socket.getInputStream());
		return new BufferedReader(in);
	}

	@Override
	public void run() {
		Socket socket = socketChannel.socket();
		try {
			BufferedReader reader = getReader(socket); // 用来接收客户端消息的输入流
			PrintWriter writer = getWriter(socket); // 用来向客户端发送消息的输出流
			BufferedReader send = null;
			String send_msg = ""; // 返回给客户端的消息
			String recive_msg = ""; // 接受客户端的消息

			while ((recive_msg = reader.readLine()) != null) {
				System.out.println(socketChannel.socket().getInetAddress() + ":" + socketChannel.socket().getLocalPort()
						+ "发送的消息是:" + recive_msg);
				if (recive_msg.equals("bye")) {
					break;
				}
				send = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("请输入返回给客户端的消息：");
				send_msg = send.readLine();
				writer.println(send_msg);
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (socketChannel != null) {
					socketChannel.close(); // 关闭socketChannel通道流
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
