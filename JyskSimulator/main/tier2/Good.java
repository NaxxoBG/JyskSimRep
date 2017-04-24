package tier2;

public class Good {
	private String manufacturer;
	private String name;
	private int palletId;

	public Good(String manufacturer, String name, int palletId) {
		this.manufacturer = manufacturer;
		this.name = name;
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

	public int getPalletId() {
		return palletId;
	}

	public void setPalletId(int palletId) {
		this.palletId = palletId;
	}

	@Override
	public String toString() {
		return "Good [manufacturer=" + manufacturer + ", name=" + name + ", palletId=" + palletId + "]";
	}
}