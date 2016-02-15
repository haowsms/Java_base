package com.base.nio.aio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AioAcceptHandler implements CompletionHandler {
	public void cancelled(AsynchronousServerSocketChannel attachment) {
		System.out.println("cancelled");
	}

	public void startRead(AsynchronousSocketChannel socket) {
		ByteBuffer clientBuffer = ByteBuffer.allocate(1024);
		socket.read(clientBuffer, clientBuffer, new AioReadHandler(socket));
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void completed(Object result, Object attachment) {
		AsynchronousSocketChannel socket = null;
		if (result instanceof AsynchronousSocketChannel) {
			socket = (AsynchronousSocketChannel)result;
		}
		
		AsynchronousServerSocketChannel attach = null;
		if (attachment instanceof AsynchronousServerSocketChannel) {
			attach = (AsynchronousServerSocketChannel)attachment;
		}
		
		if (null == attach || null == socket) {
			return;
		}
		
		
		try {
			System.out.println("AioAcceptHandler.completed called");
			attach.accept(attach, this);
			System.out.println("有客户端连接:" + socket.getRemoteAddress().toString());
			startRead(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void failed(Throwable exc, Object attachment) {
		AsynchronousServerSocketChannel attach = null;
		if (attachment instanceof AsynchronousServerSocketChannel) {
			attach = (AsynchronousServerSocketChannel)attachment;
		}
		
		exc.printStackTrace();

	}
}