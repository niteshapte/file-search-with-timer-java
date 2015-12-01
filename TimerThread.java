package com.filesearch;

public class TimerThread implements Runnable {

	FileSearchManager fileSearchManager;

	public TimerThread(String inputString, String directoryName) {
		fileSearchManager = new FileSearchManager(inputString, directoryName);
	}

	@Override
	public void run() {		
		fileSearchManager.searchFile();
	}
}
