package com.cml;

import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {

		System.out.println(Pattern.compile(".*index_challenge\\.html#detail-native/\\d{3}\\?from=challengeList$")
				.matcher("file:///android_asset/index_challenge.html#detail-native/157?from=challengeList").matches());
	}
}
