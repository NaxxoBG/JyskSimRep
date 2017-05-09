package tier2.model;

import java.io.Serializable;

public class Good implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String manufacturer;
	private String name;
	private int goodid = -1;

	public Good() {}

	public Good(String manufacturer, String name) {
		this.manufacturer = manufacturer;
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public int getGoodid() {
		return goodid;
	}

	public void setGoodid(int goodid) {
		this.goodid = goodid;
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
		return "Good [" +  "manufacturer=" + manufacturer + ", name=" + name + "]";
	}
}