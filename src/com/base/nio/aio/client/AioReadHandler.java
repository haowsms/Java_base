package com.base.nio.aio.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class AioReadHandler implements CompletionHandler {
	private AsynchronousSocketChannel socket;

	public AioReadHandler(AsynchronousSocketChannel socket) {
		this.socket = socket;
	}

	public void cancelled(ByteBuffer attachment) {
		System.out.println("cancelled");
	}

	private CharsetDecoder decoder = Charset.forName("GBK").newDecoder();

	@Override
	public void completed(Object result, Object attachment) {
		Integer i = null;
		if (result instanceof Integer) {
			i = (Integer)result;
		}
		
		ByteBuffer buf = null;
		if (attachment instanceof ByteBuffer) {
			buf = (ByteBuffer)attachment;
		}
		
		if (null == i || null == buf) {
			return;
		}

		if (i > 0) {
			buf.flip();
			try {
				System.out.println("收到" + socket.getRemoteAddress().toString() + "的消息:" + decoder.decode(buf));
				buf.compact();
			} catch (CharacterCodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			socket.read(buf, buf, this);
		} else if (i == -1) {
			try {
				System.out.println("对端断线:" + socket.getRemoteAddress().toString());
				buf = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void failed(Throwable exc, Object attachment) {
		System.out.println(exc);

	}
}