package model;

import java.io.Serializable;

public class Pallet implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int count;
	private int goodid;

	public Pallet() {
		super();
	}

	public Pallet(int id, int count, int goodid) {
		super();
		this.id = id;
		this.count = count;
		this.goodid = goodid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getGoodid() {
		return goodid;
	}

	public void setGoodid(int goodid) {
		this.goodid = goodid;
	}

	@Override
	public String toString() {
		return "Pallet [id=" + id + ", count=" + count + ", goodid=" + goodid + "]";
	}
}