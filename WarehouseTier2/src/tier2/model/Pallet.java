package tier2.model;

import java.io.Serializable;

public class Pallet implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id;
	private int count;
	private Good good;
	
	private int pickStattioId = -1;

	public Pallet() {
	}

	public Pallet(int id, int count, Good good) {
		super();
		this.id = id;
		this.count = count;
		this.good = good;
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

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}
	
	public int getPickStattioId() {
		return pickStattioId;
	}

	public void setPickStattioId(int pickStattioId) {
		this.pickStattioId = pickStattioId;
	}

	@Override
	public String toString() {
		return "Pallet [id=" + id + ", count=" + count + ", good=" + good + "]";
	}
}