package com.astrology.main;

import java.util.Iterator;

public class TestMain {

	public static void main(String[] args) {
		String testString = "-";
		String[] values = testString.split(",");
		for (String string : values) {
			System.out.println("-- "+string);
		}
		System.out.println("Print Result "+values.toString());
	}

}
