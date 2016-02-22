package com.two;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * ������
 *
 */
public class Consumer extends Thread {
	private Service service;
	private Map<Integer, TreeSet<Data>> mapGroupTopData = Collections.synchronizedMap(new HashMap<Integer, TreeSet<Data>>());
	private boolean isFinish;
	public Consumer(Service service, int i) {
		this.service = service;
		this.setName(String.valueOf(i));
	}
	
	public void setFinish(boolean isFinish) {
		this.isFinish = isFinish;
	}
	
	@Override
	public void run() {
		super.run();
		while(true){
			if (isFinish ){
				break;
			}
			Data data = service.get();
			TreeSet<Data> td = mapGroupTopData.get(data.getGroupId());
			if (null == td) {
				td = new TreeSet<Data>();
				mapGroupTopData.put(data.getGroupId(), td);
				td.add(data);
			}
		}
		
		// ������ ÿ�����鰴�յ�һ��ָ��1������������TOP1ָ���ID
		for (Integer groupId : mapGroupTopData.keySet()) {
			TreeSet<Data> tsData = mapGroupTopData.get(groupId);
			System.out.println("����id " + groupId);
			int tmp = 0;
			for (Data data : tsData) {
				System.out.println(data.toString());
				if (tmp == 5) {
					break;
				}
				
			}
		}
	}
}
