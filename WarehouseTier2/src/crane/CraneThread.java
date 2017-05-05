package crane;

import java.util.concurrent.ThreadLocalRandom;

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
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}