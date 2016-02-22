package com.one;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 黑名单ip类
 *
 */
public class BlackIpList implements IpList {
	private Map<Long, Object> LST_IP = Collections.synchronizedMap(new HashMap<Long, Object>());

	@SuppressWarnings("resource")
	public BlackIpList(String path) throws IOException {
		BufferedReader br = null;
		// 文件中一行为一个ip地址
		br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		String data = null;
		while ((data = br.readLine()) != null) {
			Long ipNum = IpUtils.ipToIpNum(data);
			if (ipNum < 0) {
				return;
			}
			LST_IP.put(ipNum, null);
		}
	}

	@Override
	public boolean isInList(String ip) {
		Long ipNum = IpUtils.ipToIpNum(ip);
		if (ipNum < 0) {
			return false;
		}
		return LST_IP.containsKey(ipNum);
	}
}
