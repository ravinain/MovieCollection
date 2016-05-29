package com.movie.util;

/**
 * 
 * @author cdacr
 *
 */
public final class StringUtils {

	/**
	 * 
	 */
	private StringUtils() {
	}

	/**
	 * 
	 * @param obj 
	 * @return object value in string format
	 */
	public static String convertNullToBlank(final Object obj) {
		if (obj != null && obj instanceof String) {
			return String.valueOf(obj);
		}
		return "";
	}

}
