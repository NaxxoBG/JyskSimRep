package tier2.model;

import java.io.Serializable;

public class RequestedGood extends Good implements Serializable {
	private static final long serialVersionUID = 1L;
	private int count;
	private int stationId = -1;
	
	private boolean finished;
	
	public RequestedGood() {}
	
	public RequestedGood(Good good, int count, int stationId) {
		super(good.getManufacturer(), good.getName());
		this.count = count;
		this.stationId = stationId;
		finished = false;
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
	
	public boolean isFinished(){
		return finished;
	}
	
	public void setFinished(boolean value){
		this.finished = value;
	}
	
	@Override
	public String toString() {
		return "RequestedGood [Name= " + getName() + ", manufacturer= " + getManufacturer() + ", count= " + getCount() + "]";
	}
}