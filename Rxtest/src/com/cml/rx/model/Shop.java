package com.cml.rx.model;

public class Shop {
	private String name;
	private int distance;

	public Shop() {
	}

	public Shop(String name, int distance) {
		super();
		this.name = name;
		this.distance = distance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

}
