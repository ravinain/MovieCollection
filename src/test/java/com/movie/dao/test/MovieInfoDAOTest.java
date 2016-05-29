/**
 * 
 */
package com.movie.dao.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.movie.bo.MovieInfoBO;
import com.movie.dao.MovieInfoDAO;
import com.movie.dao.impl.MovieInfoDAOImpl;

/**
 * @author cdacr
 * 
 */
public final class MovieInfoDAOTest {

	@Test
	@Ignore
	public void testInsertMovieInfos() {
		final MovieInfoDAO movieDao = new MovieInfoDAOImpl();

		final List<MovieInfoBO> movieInfoBOs = new ArrayList<MovieInfoBO>();

		final MovieInfoBO movieInfoBO = new MovieInfoBO();

		movieInfoBO.setFileLocation("C:\\Movies\\Test1.mkv");
		movieInfoBO.setFileName("Test1.mkv");
		movieInfoBO.setFileSize("400");
		movieInfoBO.setFileType("mkv");
		movieInfoBO.setLanguage("English");
		movieInfoBO.setMovieGenre("Drama");
		movieInfoBO.setMovieRating(8.8f);
		movieInfoBO.setReleasedYear("2001");
		movieInfoBO.setWatched(true);

		movieInfoBOs.add(movieInfoBO);

		final MovieInfoBO movieInfoBO1 = new MovieInfoBO();

		movieInfoBO1.setFileLocation("C:\\Movies\\Test2.mkv");
		movieInfoBO1.setFileName("Test2.mkv");
		movieInfoBO1.setFileSize("400");
		movieInfoBO1.setFileType("mkv");
		movieInfoBO1.setLanguage("English");
		movieInfoBO1.setMovieGenre("Drama");
		movieInfoBO1.setMovieRating(8.8f);
		movieInfoBO1.setReleasedYear("2001");
		movieInfoBO1.setWatched(true);

		movieInfoBOs.add(movieInfoBO1);

		Assert.assertTrue(movieDao.insertMovieInfos(movieInfoBOs));
	}

	@Test
	public void testUpdateMovieInfos() {

		final MovieInfoDAO movieDao = new MovieInfoDAOImpl();

		final List<MovieInfoBO> movieInfoBOs = new ArrayList<MovieInfoBO>();

		final MovieInfoBO movieInfoBO = new MovieInfoBO();

		movieInfoBO.setFileLocation("C:\\Movies\\Test3.mkv");
		movieInfoBO.setFileName("Test3.mkv");
		movieInfoBO.setFileSize("600");
		movieInfoBO.setFileType("mkv");
		movieInfoBO.setLanguage("English");
		movieInfoBO.setMovieGenre("Drama");
		movieInfoBO.setMovieRating(8.8f);
		movieInfoBO.setReleasedYear("2001");
		movieInfoBO.setWatched(true);
		movieInfoBO.setId(1);

		movieInfoBOs.add(movieInfoBO);

		final MovieInfoBO movieInfoBO1 = new MovieInfoBO();

		movieInfoBO1.setFileLocation("C:\\Movies\\Test4.mkv");
		movieInfoBO1.setFileName("Test4.mkv");
		movieInfoBO1.setFileSize("400");
		movieInfoBO1.setFileType("mkv");
		movieInfoBO1.setLanguage("English");
		movieInfoBO1.setMovieGenre("Drama");
		movieInfoBO1.setMovieRating(8.8f);
		movieInfoBO1.setReleasedYear("2001");
		movieInfoBO1.setWatched(true);
		movieInfoBO1.setId(2);

		movieInfoBOs.add(movieInfoBO1);

		Assert.assertTrue(movieDao.updateMovieInfos(movieInfoBOs));

	}

	/**
	 * 
	 */
	@Test
	public void testDeleteMovieInfos() {
		final MovieInfoDAO movieInfoDAO = new MovieInfoDAOImpl();
		final List<Integer> ids = new ArrayList<Integer>();

		ids.add(3);
		ids.add(4);

		Assert.assertTrue(movieInfoDAO.deleteMovieInfos(ids));

	}

}
