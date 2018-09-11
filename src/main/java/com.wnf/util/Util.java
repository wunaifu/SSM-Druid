package com.wnf.util;


public class Util {
	private String APP_SECRET_KEY="sv1ey5gp6NeUEyC2GlovUw==";

	public static boolean getJSONType(String str) {
		boolean result = false;
		if (!str.isEmpty()) {
			str = str.trim();
			if (str.startsWith("{") && str.endsWith("}")) {
				result = true;
			} else if (str.startsWith("[") && str.endsWith("]")) {
				result = true;
			}
		}
		return result;
	}

	public static boolean getStringType(String str) {
		boolean result = false;
		if (!str.isEmpty()) {
			str = str.trim();
			if (str.startsWith("{") && str.endsWith("}")) {
				result = true;
			} else if (str.startsWith("[") && str.endsWith("]")) {
				result = true;
			}
		}
		return result;
	}

}
