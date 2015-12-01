package com.filesearch;

public class StartPoint {

	public static void main(String[] args) {
		new TimeOutManager().fileSearchBasedOnTimeOut("*.png", "/home/username/", 100);
	}
}
