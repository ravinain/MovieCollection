package com.movie.constant;

/**
 * Enum for labels used on UI.
 * @author cdacr
 *
 */
public enum UIConstant {
	/**
	 * 
	 */
	MOVIE_COLLECTION("Movie Collection"),
	/**
	 * 
	 */
	UPDATE("Update"),
	/**
	 * 
	 */
	DELETE("Delete"),
	/**
	 * 
	 */
	REFRESH("Load"),
	/**
	 * 
	 */
	LOCATION("Location"),
	/**
	 * 
	 */
	ORDER_BY("Order By"),
	/**
	 * 
	 */
	IMDB_RATING("imdbRating"),
	/**
	 * 
	 */
	GENRE("Genre"),
	/**
	 * 
	 */
	YEAR("Year"),
	/**
	 * 
	 */
	LANGUAGE("Language"),
	/**
	 * 
	 */
	SYNC_AND_READ_FILES_PATH("Sync DB Data Read Files From Path"),
	/**
	 * 
	 */
	LOAD_FROM_DB("Load Data From DB");

	/**
	 * 
	 */
	private String text;

	/**
	 * 
	 * @param text 
	 */
	UIConstant(final String text) {
		this.text = text;
	}

	/**
	 * 
	 * @return returns the text variable value
	 */
	public String getText() {
		return this.text;
	}
}
