package com.base.io.eg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 网络阻塞IO服务端
 * 
 * @author haow
 *
 */
public class NetBlockingIO {
	public class BlockingIOServer {
		
		public BlockingIOServer() {
			this.init();
		}

		private void init() {
			ServerSocket ss = null;
			try {
				ss = new ServerSocket(10000);
				System.out.println("server start...");
				while (true) {
					Socket s = ss.accept();
					(new LogicThread(s)).run();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (null != ss) {
					try {
						ss.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		public class LogicThread implements Runnable {
			private Socket s;

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
						System.out.println(strBuilder.toString());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		/**
		 * 网络阻塞IO客户端
		 * 
		 * @author haow
		 *
		 */
		public class NetBlockingIOClient {

			public NetBlockingIOClient() {
				this.init();
			}

			public void init() {
				try {
					Socket client = new Socket("localhost", 10000);
					client.getInputStream();
					client.getOutputStream();
					Writer writer = new OutputStreamWriter(client.getOutputStream());
					writer.write("Hello Server.");
					writer.flush();
					writer.close();
					client.close();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}