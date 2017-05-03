package model;

import java.io.Serializable;

public class Good implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int palletId;
	private String manufacturer;
	private String name;

	public Good() {
		super();
	}

	public Good(int palletId, String manufacturer, String name) {
		super();
		this.palletId = palletId;
		this.manufacturer = manufacturer;
		this.name = name;
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