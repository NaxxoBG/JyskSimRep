package model;

import java.io.Serializable;

public class Good implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int palletId;
	private int goodId;
	private String manufacturer;
	private String name;

	public Good() {}

	public Good(int palletId, int goodId, String manufacturer, String name) {
		this.palletId = palletId;
		this.goodId = goodId;
		this.manufacturer = manufacturer;
		this.name = name;
	}

	public int getGoodId() {
		return goodId;
	}

	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}

	public int getPalletId() {
		return palletId;
	}

	public void setPalletId(int palletId) {
		this.palletId = palletId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Good [palletId=" + palletId + ", manufacturer=" + manufacturer + ", name=" + name + "]";
	}
}