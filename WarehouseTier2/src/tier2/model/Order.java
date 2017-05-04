package tier2.model;

import java.io.Serializable;

public class Order implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id;
	private Good[] goods;

	public Order(){}
	
	public Order(int id, Good[] goods) {
		this.id = id;
		this.goods = goods;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Good[] getGoods() {
		return goods;
	}

	public void setGoods(Good[] goods) {
		this.goods = goods;
	}

}