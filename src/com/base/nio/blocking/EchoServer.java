package com.base.nio.blocking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author wanghao
 *
 */
public class EchoServer {
	private int port = 8003;
	private ServerSocketChannel serverSocketChannel = null;
	private ExecutorService executorService; // 线程池
	private static final int POOL_MULTIPLE = 4; // 线程池的工作线程数量

	public EchoServer() throws IOException {
		// 创建一个线程池
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_MULTIPLE);
		// 创建一个ServerSocketChannel对象
		serverSocketChannel = ServerSocketChannel.open();
		// 使得在同一个主机上关闭了服务器程序，紧接着再启动该服务器程序时，可以顺利绑定相同的端口.
		serverSocketChannel.socket().setReuseAddress(true);
		// 绑定本地端口
		serverSocketChannel.socket().bind(new InetSocketAddress(port));
		System.out.println("服务器启动");
	}

	public void service() {
		while (true) {
			SocketChannel socketChannel = null;
			try {
				socketChannel = serverSocketChannel.accept();
				executorService.execute(new Handler(socketChannel)); // 处理客户链接
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) throws IOException {
		new EchoServer().service();
	}
}

/**
 * 处理单个客户请求
 * @author wanghao
 *
 */
class Handler implements Runnable {
	private SocketChannel socketChannel;

	public Handler(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	public void run() {
		handle(socketChannel);
	}

	public void handle(SocketChannel socketChannel) {
		try {
			Socket socket = socketChannel.socket(); // 获得与socketChannel关联的socket对象.
			System.out.println("接收到客户连接，来自: " + socket.getInetAddress() + ":" + socket.getPort());
			BufferedReader br = getReader(socket);
			PrintWriter pw = getWriter(socket);

			String msg = null;
			while ((msg = br.readLine()) != null) {
				System.out.println(msg);
				pw.println(echo(msg));
				if (msg.equals("bye"))
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socketChannel != null)
					socketChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private PrintWriter getWriter(Socket socket) throws IOException {
		OutputStream socketOut = socket.getOutputStream();
		return new PrintWriter(socketOut, true);
	}

	private BufferedReader getReader(Socket socket) throws IOException {
		InputStream socketIn = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(socketIn));
	}

	public String echo(String msg) {
		return "echo:" + msg;
	}
}