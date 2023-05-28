package com.project.ably.common;

public class CommonUtil {
	public static String firstWordToLowerCase(String word) {
		return Character.toLowerCase(word.charAt(0)) + word.substring(1);
	}
}
