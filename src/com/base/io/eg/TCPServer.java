package com.base.io.eg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String[] args) {
		TCPServer sv = new TCPServer();
		sv.server();
	}

//	http://www.2cto.com/kf/201505/402803.html
	private void server() {
		try {
			ServerSocket ss = new ServerSocket(10000);
			System.out.println("server start...");
			while (true) {
				Socket s = ss.accept();
				new LogicThread(s);// 开一个线程来处理请求，这里面调用InputStream.read()读取请求信息
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class LogicThread implements Runnable {
		Socket s;

		public LogicThread(Socket s) {
			this.s = s;
		}

		@Override
		public void run() {
			InputStream in = null;
			try {
				in = s.getInputStream();
				InputStreamReader reader = new InputStreamReader(in);
				BufferedReader buffer = new BufferedReader(reader);
				String str = null;
				StringBuilder strBuilder = new StringBuilder();
				while ((str = buffer.readLine()) != null) { // 阻塞
					strBuilder.append(str);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
