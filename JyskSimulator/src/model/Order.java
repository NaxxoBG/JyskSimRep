package model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int Id;
	private List<Good> goods;

	public Order(int id, List<Good> goods) {
		this.Id = id;
		this.goods = goods;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public List<Good> getGoods() {
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return "Order [Id=" + Id + "]";
	}
}