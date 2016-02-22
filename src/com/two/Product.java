package com.two;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 消费者.
 */
public class Product extends Thread {
	private Service service;
	private List<File> aList;
	private boolean isFinish;
	public Product(Service service, int i, List<File> aList) {
		this.service = service;
		this.setName(String.valueOf(i));
		this.aList = aList;
	}
	
	@Override
	public void run() {
		super.run();
		for (File file : aList) {
			addData(file);
		}
		this.isFinish = true;
	}
	
	public boolean isFinish() {
		return isFinish;
	}
	
	/**
	 * 添加数据
	 * @param file
	 */
	public void addData(File file) {
		if (file == null) {
			return;
		}
		
		BufferedReader br = null;
		// 文件中一行为一个ip地址
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String recode = null;
			while ((recode = br.readLine()) != null) {
				String[] rec = recode.split(",");
				if (rec.length !=5) {
					continue;
				}
				// 校验数据rec
				// todo
				
				// 添加数据
				service.set(new Data(Long.parseLong(rec[0]),Integer.parseInt(rec[1]),
						Double.parseDouble(rec[2]),Double.parseDouble(rec[3]),Double.parseDouble(rec[4])));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
