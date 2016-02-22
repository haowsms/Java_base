package com.one;

public class IpUtils {
	
	/**
	 * 将ip转为数字地址.
	 * 1. 127 * 256^3
	 * 2. 0 * 256^2
	 * 3. 0 * 256^1
	 * 4. 1 * 256^0
	 * @param ipAddress
	 * @return -1表示ip不正确.
	 */
	public static long ipToIpNum(String ip) {
		if (null == ip || ip.trim().length() <= 0) {
			return -1;
		}
		
		String[] ipInArray = ip.split("\\.");
		long result = 0;
		for (int i = 0; i < ipInArray.length; i++) {
			int power = 3 - i;
			int ips = Integer.parseInt(ipInArray[i]);
			result += ips * Math.pow(256, power);
		}
		return result;
	}

	/**
	 * 将数字地址转为IP.
	 * @param ip
	 * @return 数字地址.
	 */
	public static String ipNumToIp(long ip) {
		StringBuilder sb = new StringBuilder(15);
		for (int i = 0; i < 4; i++) {
			// process 1 ,0,0,127
			sb.insert(0, Long.toString(ip & 0xff));
			if (i < 3) {
				sb.insert(0, '.');
			}
			// process> 127.0.0.1,127.0.0,127.0,127
			ip = ip >> 8;
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		IpUtils obj = new IpUtils();
		System.out.println("iptoLong2 : " + obj.ipToIpNum("127.0.0.1"));
		System.out.println("longToIp  : " + obj.ipNumToIp(2130706433L));
	}
}