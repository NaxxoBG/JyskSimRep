package tier2.crane;

public class Crane implements Runnable {
	private CraneControl monitor;
	
	public Crane(CraneControl monitor) {
		this.monitor = monitor;
	}

	@Override
	public void run() {
		while (true) {
			monitor.putPalletOnShelf();
			monitor.getGoodForOrder();
		}
	}
}