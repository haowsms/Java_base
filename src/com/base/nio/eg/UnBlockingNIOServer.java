package com.base.nio.eg;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class UnBlockingNIOServer {
	// 通道管理器
	private Selector selector;

	/**
	 * 获得一个ServerSocket通道，并对该通道做一些初始化的工作
	 * 
	 * @param port
	 *            绑定的端口号
	 * @throws IOException
	 */
	public void initServer(int[] port) throws IOException {
		// 获得一个ServerSocket通道, 通常情况下,每个端口会单独使用一个ServerSocketChannel
		List<ServerSocketChannel> lstServerChannel = this.getServerSocketChannel(port);
		// 获得一个通道管理器, 可以管理多个ServerSocket通道
		this.selector = Selector.open();
		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
		// 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
		for (ServerSocketChannel serverChannel : lstServerChannel) {
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		}
		this.listen();
	}

	/**
	 * ServerSocket通道列表
	 * 
	 * @param port
	 *            端口列表
	 * @return
	 * @throws IOException
	 */
	public List<ServerSocketChannel> getServerSocketChannel(int[] port) throws IOException {
		List<ServerSocketChannel> lstServerSocketChannel = new ArrayList<ServerSocketChannel>(port.length);
		for (int pt : port) {
			// 获得一个ServerSocket通道
			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			// 设置通道为非阻塞
			serverChannel.configureBlocking(false);
			// 将该通道对应的ServerSocket绑定到port端口
			serverChannel.socket().bind(new InetSocketAddress(pt));
			lstServerSocketChannel.add(serverChannel);
		}
		return lstServerSocketChannel;
	}

	/**
	 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
	 * 
	 * @throws IOException
	 */
	public void listen() throws IOException {
		System.out.println("服务端启动成功！");
		// 轮询访问selector
		while (true) {
			// 当注册的事件到达时，方法返回；否则,该方法会一直阻塞
			selector.select();
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
			while (keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
				if (key.isAcceptable()) {// a connection was accepted by a
											// ServerSocketChannel.
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					java.net.SocketAddress s = server.getLocalAddress();
					System.out.println(s.toString());
					// 获得和客户端连接的通道
					SocketChannel channel = server.accept();
					// 设置成非阻塞
					channel.configureBlocking(false);
					// 在这里可以给客户端发送信息哦
					channel.write(ByteBuffer.wrap(new String("向客户端发送了一条信息").getBytes()));
					// 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
					channel.register(this.selector, SelectionKey.OP_READ);
				} else if (key.isConnectable()) {
					// a connection was established with a remote server.
					System.out.println("server");
				} else if (key.isWritable()) {
					// a connection was established with a remote server.
					System.out.println("isWritable");
				} else if (key.isReadable()) {
					// a channel is ready for reading
					read(key);
				}
				keyIterator.remove();
			}
		}
	}

	/**
	 * 处理读取客户端发来的信息 的事件
	 * 
	 * @param key
	 * @throws IOException
	 */
	public void read(SelectionKey key) throws IOException {
		// 服务器可读取消息:得到事件发生的Socket通道
		SocketChannel channel = (SocketChannel) key.channel();
		// 创建读取的缓冲区
		ByteBuffer bb = ByteBuffer.allocate(8);
		int len = channel.read(bb);
		bb.flip();
		if (bb.hasArray() && len > 0) {
			System.out.println("from client " + ":" + new String(bb.array(), 0, len));

			byte[] data = bb.array();
			String msg = new String(data).trim();
			System.out.println("服务端收到信息：" + msg);

//			//同时将SelectionKey标记为可读，以便读取。
//			int newInterestOps = key.interestOps();
//			newInterestOps |= SelectionKey.OP_WRITE;
//			key.interestOps(newInterestOps);

			ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
			channel.write(outBuffer);// 将消息回送给客户端
		} else if (len == -1) {
			System.out.println("no data");// 在这里不能忘记关闭channel
			channel.close();
		}

		bb.clear();
	}

	/**
	 * 启动服务端测试
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		UnBlockingNIOServer server = new UnBlockingNIOServer();
		server.initServer(new int[] { 8000, 8001 });
	}
}
