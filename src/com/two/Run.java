package com.two;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Run {
	public static void main(String[] args) {
		Service service = new Service();
		Product[] threadA = new Product[10];
		Consumer[] threadB = new Consumer[10];

		// ����ϵͳĳĿ¼�µ��ļ��б�.
		// todo
		File dataFile = new File("D:/data");
		if (!(dataFile.exists() && dataFile.isDirectory())) {
			System.err.println("����Ŀ¼����ȷ");
			return;
		}
		
		List<File> fileList = new ArrayList<File>();
		for (File f : dataFile.listFiles()) {
			fileList.add(f);
		}
		
		List<File> aList = new ArrayList<File>();
		List<File> bList = new ArrayList<File>();
		List<File> cList = new ArrayList<File>();
		int index = 0;
		for (File file : fileList) {
			if (index > 3) {
				index = 0;
			}
			switch (index) {
			case 0:
				aList.add(file);
				break;
			case 1:
				bList.add(file);
				break;
			case 2:
				cList.add(file);
				break;
			default:
				break;
			}
			index++;
		}
		// ������������������.
		for (int i = 0; i < 3; i++) {
			threadA[i] = new Product(service, i, aList);
			threadA[i].start();
		}

		// ��������������
		for (int i = 0; i < 5; i++) {
			threadB[i] = new Consumer(service, i);
			threadB[i].start();
		}
		
		// �ȴ��߳̽���.
		while(true){
			boolean isFinishProduct = false;
			for (int i = 0; i < 3; i++) {
				isFinishProduct = threadA[i].isFinish();
				if (!isFinishProduct) {
					break;
				}
			}
			
			// ���������߳�
			if (isFinishProduct && service.getSize()<=0) {
				for (int i = 0; i < 5; i++) {
					threadB[i].setFinish(true);
					threadB[i].interrupt();
				}
			}
		}
	}
}
