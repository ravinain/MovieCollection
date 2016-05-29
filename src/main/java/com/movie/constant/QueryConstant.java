/**
 * 
 */
package com.movie.constant;

/**
 * @author cdacr
 *
 */
public final class QueryConstant {

	/**
	 * 
	 */
	private QueryConstant() {
	}

	/**
	 * 
	 */
	public static final String UPD_MOVIE_INFO_QUERY = "UPDATE MOVIE.MOVIEINFO "
			+ "SET FILE_NAME = ?, FILE_LOCATION = ?, FILE_TYPE = ?, "
			+ "MOVIE_RATING = ?, MOVIE_GENRE = ?, RELEASED_YEAR = ?, "
			+ "FILE_SIZE = ?, LANGUAGE = ?, IS_WATCHED = ? WHERE ID = ?";
	/**
	 * 
	 */
	public static final String INS_MOVIE_INFO_QUERY = ""
			+ "INSERT INTO MOVIE.MOVIEINFO( "
			+ "FILE_NAME, FILE_LOCATION, FILE_TYPE, "
			+ "MOVIE_RATING, MOVIE_GENRE, RELEASED_YEAR, "
			+ "FILE_SIZE, LANGUAGE, IS_WATCHED)" + " VALUES(?,?,?,?,?,?,?,?,?)";
	/**
	 * 
	 */
	public static final String DEL_MOVIE_INFO_QUERY = ""
			+ "DELETE FROM MOVIE.MOVIEINFO WHERE ID = ?";
	/**
	 * 
	 */
	public static final String SEL_MOVIE_INFO_QUERY = ""
			+ "SELECT * FROM MOVIE.MOVIEINFO";

}
