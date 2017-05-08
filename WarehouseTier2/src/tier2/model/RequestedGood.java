package tier2.model;

import java.io.Serializable;

public class RequestedGood extends Good implements Serializable {
	private static final long serialVersionUID = 1L;
	private int count;
	private int stationId = -1;
	
	public RequestedGood() {}
	
	public RequestedGood(Good good, int count) {
		super(good.getGoodid(), good.getManufacturer(), good.getName());
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public int getStationId() {
		return stationId;
	}
	
	
	
	@Override
	public String toString() {
		return "RequestedGood [count=" + count + "]";
	}
}