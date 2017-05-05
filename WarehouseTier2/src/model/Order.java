package model;

import java.io.Serializable;
import java.util.Arrays;

public class Order implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int Id;
	private Good[] goods;

	public Order(int id, Good[] goods) {
		this.Id = id;
		this.goods = goods;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Good[] getGoods() {
		return goods;
	}

	public void setGoods(Good[] goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return "Order [Id=" + Id +  ", " + Arrays.toString(goods)+ "]";
	}
}