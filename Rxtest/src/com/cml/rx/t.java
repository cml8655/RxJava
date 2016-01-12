package com.cml.rx;

public class t {
	public static void main(String[] args) {
	A a=	new B();
	}
}

class A {
	public A() {
		System.out.println("this is class1");
	}
}

class B extends A {
	public B() {
		System.out.println("this is class 2	");
	}

	public B(int a) {
		System.out.println("dddd");
	}
}
