package com.cml.rx;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class WebviewTest {
	public static void main(String[] args) throws Exception {
		System.out.println("----");
		InputStream rawIn = WebviewTest.class.getResourceAsStream("Web Data");
		ObjectInputStream objIn=new ObjectInputStream(rawIn);
		System.out.println(objIn.readObject());
	}
}
