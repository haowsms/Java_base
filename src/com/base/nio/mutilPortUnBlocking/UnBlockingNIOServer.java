package com.base.nio.mutilPortUnBlocking;

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

	// 端口列表.
	private int[] port;

	/**
	 * 初始化ServerSocketChannel服务通道列表. 病注册到Selector中
	 * @throws IOException
	 */
	public void initServerSocketChannel() throws IOException {
		// 获得一个ServerSocket通道, 通常情况下,每个端口会单独使用一个ServerSocketChannel
		List<ServerSocketChannel> lstServerChannel = this.getServerSocketChannel(port);
		// 获得一个通道管理器, 可以管理多个ServerSocket通道
		this.selector = Selector.open();
		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
		// 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
		for (ServerSocketChannel serverChannel : lstServerChannel) {
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		}
	}
	
	/**
	 * ServerSocket通道列表
	 * @param port 端口列表
	 * @return
	 * @throws IOException
	 */
	private List<ServerSocketChannel> getServerSocketChannel(int[] port) throws IOException {
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
	public void listener() throws IOException {
		// 轮询访问selector
		while (true) {
			// 当注册的事件到达时，方法返回；否则,该方法会一直阻塞
			this.selector.select();
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
			while (keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
				if (key.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					SocketChannel channel = server.accept();	// 获得和客户端连接的通道
					channel.configureBlocking(false);// 设置成非阻塞
					channel.write(ByteBuffer.wrap(new String("向客户端发送了一条信息").getBytes()));// 在这里可以给客户端发送信息哦
					channel.register(this.selector, SelectionKey.OP_READ);// 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
				} else if (key.isConnectable()) {
					System.out.println("server");
				} else if (key.isWritable()) {
					System.out.println("isWritable");
				} else if (key.isReadable()) {
					new ReadThread((SocketChannel)key.channel());
				}
				keyIterator.remove();
			}
		}
	}
	
	/**
	 * 读线程
	 * @author haow
	 *
	 */
	class ReadThread implements Runnable{
		// Socket通道
		private SocketChannel channel;
		public ReadThread(SocketChannel channel) {
			this.channel = channel;
		}
		
		@Override
		public void run() {
			// 创建读取的缓冲区
			ByteBuffer bb = ByteBuffer.allocate(8);
			int len;
			try {
				len = this.channel.read(bb);
				bb.flip();
				if (bb.hasArray() && len > 0) {
					byte[] data = bb.array();
					String msg = new String(data).trim();
					System.out.println("服务端收到信息：" + msg);
					// //同时将SelectionKey标记为可读，以便读取。
					// int newInterestOps = key.interestOps();
					// newInterestOps |= SelectionKey.OP_WRITE;
					// key.interestOps(newInterestOps);
					// 模拟请求服务返回数据给客户端.
					ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
					this.channel.write(outBuffer);// 将消息回送给客户端
				} else if (len == -1) {// 在这里不能忘记关闭channel
					System.out.println("no data");
					this.channel.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				bb.clear();
			}
		}
		
		public void interestOps(){
			 int newInterestOps = channel.interestOps();
			 newInterestOps |= SelectionKey.OP_WRITE;
			 key.interestOps(newInterestOps);
		}
	}

	/**
	 * 启动服务端测试
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		UnBlockingNIOServer server = new UnBlockingNIOServer();
		server.initServerSocketChannel();
	}
}
