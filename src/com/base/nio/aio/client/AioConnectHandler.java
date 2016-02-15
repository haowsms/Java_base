package com.base.nio.aio.client;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

public class AioConnectHandler implements CompletionHandler {
	private Integer content = 0;

	public AioConnectHandler(Integer value) {
		this.content = value;
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
		Void attach = null;
		if (result instanceof Void) {
			attach = (Void)result;
		}
		
		AsynchronousSocketChannel connector = null;
		if (attachment instanceof AsynchronousSocketChannel) {
			connector = (AsynchronousSocketChannel)attachment;
		}
		
		if (null == connector) {
			return;
		}
		
		try {
			connector.write(ByteBuffer.wrap(String.valueOf(content).getBytes())).get();
			startRead(connector);
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException ep) {
			ep.printStackTrace();
		}

	}

	@Override
	public void failed(Throwable exc, Object attachment) {
		exc.printStackTrace();

	}
}