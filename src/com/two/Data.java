package com.two;

/**
 * ID，groupid,指标1，指标2，指标3 ID、groupid, 不超出10位正整数 指标1，指标2，指标3，为浮点数
 *
 */
public class Data implements Comparable<Data>{

	private long id;
	private int groupId;
	private double indexOne;
	private double indexTwo;
	private double indexThree;

	public Data(long id, int groupId, double indexOne, double indexTwo, double indexThree) {
		// 数据校验，设值
		this.id = id;
		this.groupId = groupId;
		this.indexOne =indexOne;
		this.indexTwo = indexTwo;
		this.indexThree = indexThree;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public double getIndexOne() {
		return indexOne;
	}

	public void setIndexOne(double indexOne) {
		this.indexOne = indexOne;
	}

	public double getIndexTwo() {
		return indexTwo;
	}

	public void setIndexTwo(double indexTwo) {
		this.indexTwo = indexTwo;
	}

	public double getIndexThree() {
		return indexThree;
	}

	public void setIndexThree(double indexThree) {
		this.indexThree = indexThree;
	}

	@Override
	public int compareTo(Data o) {
		if (this.getIndexOne() > o.getIndexOne()){
			return 1;
		}else if (this.getIndexOne() < o.getIndexOne()){
			return -1;
		} else{
			return 0;
		}
	}
	
	@Override
	public String toString() {
		return this.getGroupId() +" "+ this.getId() +" "+ this.getIndexOne();
	}
}
