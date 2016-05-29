package com.movie.constant;

/**
 * 
 * @author cdacr
 *
 */
public enum UITableMapping {
	/**
	 * 
	 */
	ID(0, "ID"),
	/**
	 * 
	 */
	FILE_NAME(1, "File Name"),
	/**
	 * 
	 */
	LOCATION(2, "Location"),
	/**
	 * 
	 */
	TYPE(3, "Type"),
	/**
	 * 
	 */
	SIZE(4, "Size(MBs)"),
	/**
	 * 
	 */
	GENRE(5, "Genre"),
	/**
	 * 
	 */
	RATING(6, "Rating"),
	/**
	 * 
	 */
	YEAR(7, "Year"),
	/**
	 * 
	 */
	LANGUAGE(8, "Language"),
	/**
	 * 
	 */
	WATCHED(9, "Watched"),
	/**
	 * 
	 */
	UPDATE(10, "Update"),
	/**
	 * 
	 */
	DELETE(11, "Delete");

	/**
	 * 
	 */
	private int index;
	/**
	 * 
	 */
	private String text;

	/**
	 * 
	 * @param index 
	 * @param text 
	 */
	UITableMapping(final int index, final String text) {
		this.index = index;
		this.text = text;
	}

	/**
	 * 
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * 
	 * @return text
	 */
	public String getText() {
		return text;
	}
}
