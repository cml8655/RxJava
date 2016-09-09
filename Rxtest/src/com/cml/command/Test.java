package com.cml.command;

public class Test {
	public static void main(String[] args) {
		char a = (char) Integer.parseInt("33", 16);
		System.out.println("a=" + a + "," + Character.toString((char) Integer.parseInt("33", 16)));
		char b = 51;
		System.out.println(Integer.toHexString((int) b));
	}
}
