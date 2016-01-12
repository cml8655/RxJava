package com.cml.rx.model;

import java.util.ArrayList;
import java.util.List;

public class ShopFactory {
	public static List<Shop> createShops(int size) {
		List<Shop> shops = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			shops.add(new Shop("shop:" + i, i));
		}

		return shops;
	}
}
