package com.can.logservice;

import java.util.Comparator;

public class LogFileComparator implements Comparator<String> {
	public int compare(String s1, String s2) {
		int val = 0;
		try {
			Long t1 = Long.parseLong(s1.substring(3));
			Long t2 = Long.parseLong(s2.substring(3));
			val = (int)(t1 - t2);
		} catch (NumberFormatException e) {
			
		}
		return val;
	}
}

