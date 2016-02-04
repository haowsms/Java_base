package com.base.io.eg;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;

public class NoramlIO {

	/**
	 * 字节流
	 * 
	 * @throws IOException
	 */
	public void byteStream() throws IOException {
		String fileName = "D:" + File.separator + "hello.txt";
		File f = new File(fileName);
		InputStream in = new FileInputStream(f);
		byte[] b = new byte[1024];
		int count = 0;
		int temp = 0;
		while ((temp = in.read()) != (-1)) {
			b[count++] = (byte) temp;
		}
		in.close();
		System.out.println(new String(b));
	}

	/**
	 * 字符流
	 * 
	 * @throws IOException
	 */
	public void charStream() throws IOException {
		String fileName = "D:" + File.separator + "hello.txt";
		File f = new File(fileName);
		char[] ch = new char[100];
		Reader read = new BufferedReader(new FileReader(f));
		int temp = 0;
		int count = 0;
		while ((temp = read.read()) != (-1)) {
			ch[count++] = (char) temp;
		}
		read.close();
		System.out.println("内容为:" + new String(ch, 0, count));
	}

	/**
	 * 内存流
	 * 
	 * @throws IOException
	 */
	public void memoryStream() throws IOException {
		String str = "Hello,jiyiqin";
		ByteArrayInputStream bInput = new ByteArrayInputStream(str.getBytes());
		System.out.println("Convertingcharacters to Upper case  ");
		for (int y = 0; y < 1; y++) {
			int c = -1;
			while ((c = bInput.read()) != -1) {
				System.out.println(Character.toUpperCase((char) c));
			}
			bInput.reset();
		}
	}

	/**
	 * 管道流
	 * @author haow
	 *
	 */
	class PipedInputStreamTest {
		
		public void init() {
			PipedInputStream input = new PipedInputStream();
			PipedOutputStream output = new PipedOutputStream();
			try {
				output.connect(input);
				new Thread(new OutputstreamRunnable(output)).start();
				new Thread(new InputstreamRunnable(input)).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 输出管道流线程
		class OutputstreamRunnable implements Runnable {
			private OutputStream out;

			public OutputstreamRunnable(OutputStream output) {
				this.out = output;
			}

			@Override
			public void run() {
				String str = "hello pipe";
				try {
					out.write(str.getBytes());
					out.flush();
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// 输入管道流线程
		class InputstreamRunnable implements Runnable {
			private InputStream in;

			public InputstreamRunnable(InputStream in) {
				this.in = in;
			}

			@Override
			public void run() {
				byte[] bs = new byte[1024];
				int len = -1;
				try {
					if ((len = in.read(bs)) != -1) {
						System.out.println(new String(bs, 0, len));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}