/**
 * 
 */
package com.movie.dao;

import java.util.List;

import com.movie.bo.MovieInfoBO;

/**
 * @author cdacr
 * 
 */
public interface MovieInfoDAO {

	/**
	 * Method deletes the movie information from the data base.
	 * @param ids 
	 * @return TRUE|FALSE
	 */
	boolean deleteMovieInfos(List<Integer> ids);

	/**
	 * Method updates the movie information in the data base.
	 * @param movieInfoBOs 
	 * @return TRUE|FALSE
	 */
	boolean updateMovieInfos(List<MovieInfoBO> movieInfoBOs);

	/**
	 * Method inserts the movie information in the data base.
	 * @param movieInfoBOs 
	 * @return TRUE|FALSE
	 */
	boolean insertMovieInfos(List<MovieInfoBO> movieInfoBOs);

	/**
	 * Method fetches all movie information from the data base.
	 * @return List<MovieInfoBO>
	 */
	List<MovieInfoBO> getMovieInfos();

}
