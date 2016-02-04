package com.base.io.eg;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetUnBlockingIO {
	public void init() {
		try {
			Socket	client = new Socket("localhost", 10000);
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

	public static void main(String[] args) {
		NetUnBlockingIO bioc = new NetUnBlockingIO();
		bioc.init();
	}
}
