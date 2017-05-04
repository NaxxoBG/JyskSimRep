package tier2.model;

import java.io.Serializable;

public class Pallet implements Serializable
{

	private static final long serialVersionUID = 1L;
	private int id;
	private int count;
	private int goodId;

	public Pallet() {}

	public Pallet(int id, int count, int goodId) {
		super();
		this.id = id;
		this.count = count;
		this.goodId = goodId;
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

	public int getGoodId() {
		return goodId;
	}

	public void setGoodId(int goodid) {
		this.goodId = goodid;
	}

	@Override
	public String toString() {
		return "Pallet [id=" + id + ", count=" + count + ", goodid=" + goodId + "]";
	}
}