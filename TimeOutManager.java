package com.filesearch;

public class TimeOutManager {

	public void fileSearchBasedOnTimeOut(String inputString, String directoryName, long timeOut) {
		Thread taskThread = new Thread(new TimerThread(inputString, directoryName));

		taskThread.start();

		try {
			Thread.sleep(timeOut);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (taskThread.isAlive()) {			
			taskThread.interrupt();
			System.out.println("Search timeout");
			System.exit(1);
		}
	}	
}
