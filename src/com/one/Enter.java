package com.one;

import java.io.IOException;

public class Enter {
	public static void main(String[] args) {
		IpList blackIpList = null;
		IpList whiteIpList = null;
		try {
			blackIpList = new BlackIpList("d:/white_ip.txt");
			whiteIpList = new WhiteIpList("d:/black_ip.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("����ip����.");
		}
		
		if (null == blackIpList || null == whiteIpList) {
			return;
		}
		
	    // ��֤�ڰ�����
	    String ip = "192.1.1.2";
	    boolean isBlack = blackIpList.isInList(ip);
	    boolean isWhite = whiteIpList.isInList(ip);
	}
}
