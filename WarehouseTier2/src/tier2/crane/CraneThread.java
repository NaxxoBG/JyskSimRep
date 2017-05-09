package tier2.crane;

public class CraneThread implements Runnable {
	private CraneMonitor monitor;
	
	public CraneThread(CraneMonitor monitor) {
		this.monitor = monitor;
	}

	@Override
	public void run() {
		while (true) {
			System.err.println("THREAD IS RUNNING");
			monitor.putPalletOnShelf();
			monitor.getGoodForOrder();
		}
	}
}