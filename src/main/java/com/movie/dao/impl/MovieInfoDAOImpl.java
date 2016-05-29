/**
 * 
 */
package com.movie.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.movie.bo.MovieInfoBO;
import com.movie.constant.QueryConstant;
import com.movie.dao.MovieInfoDAO;
import com.movie.util.CommonUtils;
import com.movie.util.DBUtils;
import com.movie.util.StringUtils;
import com.sun.istack.internal.logging.Logger;

/**
 * @author cdacr
 * 
 */
public final class MovieInfoDAOImpl implements MovieInfoDAO {

	/**
	 * DB Utility instance, this can be used to get connection 
	 * and closed resources activity.
	 */
	private static final DBUtils DB_UTILS = new DBUtils();

	/**
	 * 
	 */
	private static final CommonUtils COMM_UTILS = new CommonUtils();

	/**
	 * 
	 */
	private final int startIndex = 1;

	/**
	 * Logger object to log information in the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(MovieInfoDAO.class);

	/**
	 * Method deletes the movie information in DB based on input movie IDs.
	 * @param ids 
	 * @return TRUE|FALSE
	 */
	@Override
	public boolean deleteMovieInfos(final List<Integer> ids) {
		boolean deleteFlag = false;
		Connection conn = DB_UTILS.getConnection();
		final String deleteQuery = QueryConstant.DEL_MOVIE_INFO_QUERY;
		PreparedStatement ps = null;
		final int batchSize = 50;
		int count = 0;
		try {
			ps = conn.prepareStatement(deleteQuery);

			for (final Integer id : ids) {
				ps.setInt(1, id);

				ps.addBatch();

				if (++count % batchSize == 0) {
					ps.executeBatch();
				}
			}
			ps.executeBatch();
			deleteFlag = true;
		} catch (final SQLException e) {
			LOGGER.severe(e.getMessage());
		} finally {
			DB_UTILS.closeResources(null, ps, null, conn);
			ps = null;
			conn = null;
		}
		return deleteFlag;
	}

	/**
	 * Method update MOVIE.MOVIEINFO table data based on input parameter.
	 * @param movieInfoBOs 
	 * @return TRUE|FALSE
	 */
	@Override
	public boolean updateMovieInfos(final List<MovieInfoBO> movieInfoBOs) {
		boolean updateFlag = false;
		Connection conn = DB_UTILS.getConnection();
		final String updateQuery = QueryConstant.UPD_MOVIE_INFO_QUERY;
		PreparedStatement ps = null;
		final int batchSize = 50;
		int count = 0;
		try {
			ps = conn.prepareStatement(updateQuery);

			for (final MovieInfoBO movieInfoBO : movieInfoBOs) {
				int index = startIndex;
				ps.setString(index, movieInfoBO.getFileName());
				ps.setString(++index, movieInfoBO.getFileLocation());
				ps.setString(++index, movieInfoBO.getFileType());
				ps.setDouble(++index, movieInfoBO.getMovieRating());
				ps.setString(++index, movieInfoBO.getMovieGenre());
				ps.setString(++index, movieInfoBO.getReleasedYear());
				ps.setString(++index, movieInfoBO.getFileSize());
				ps.setString(++index, movieInfoBO.getLanguage());
				ps.setString(++index,
						COMM_UTILS.booleanToString(movieInfoBO.isWatched()));

				ps.setInt(++index, movieInfoBO.getId()); // where
				// clause
				// key

				ps.addBatch();

				if (++count % batchSize == 0) {
					ps.executeBatch();
				}
			}
			ps.executeBatch();
			updateFlag = true;
		} catch (final SQLException e) {
			LOGGER.severe(e.getMessage());
			e.printStackTrace();
		} finally {
			DB_UTILS.closeResources(null, ps, null, conn);
			ps = null;
			conn = null;
		}

		return updateFlag;
	}

	/**
	 * Method inserts the list of {@link MovieInfoBO} in DB.
	 * @param movieInfoBOs 
	 * @return TRUE|FALSE
	 */
	@Override
	public boolean insertMovieInfos(final List<MovieInfoBO> movieInfoBOs) {
		boolean insertFlag = false;
		Connection conn = DB_UTILS.getConnection();
		final String insertQuery = QueryConstant.INS_MOVIE_INFO_QUERY;
		PreparedStatement ps = null;
		final int batchSize = 50;
		int count = 0;
		try {
			ps = conn.prepareStatement(insertQuery);

			for (final MovieInfoBO movieInfoBO : movieInfoBOs) {
				int index = startIndex;
				ps.setString(index, movieInfoBO.getFileName());
				ps.setString(++index, movieInfoBO.getFileLocation());
				ps.setString(++index, movieInfoBO.getFileType());
				ps.setDouble(++index, movieInfoBO.getMovieRating());
				ps.setString(++index, movieInfoBO.getMovieGenre());
				ps.setString(++index, movieInfoBO.getReleasedYear());
				ps.setString(++index, movieInfoBO.getFileSize());
				ps.setString(++index, movieInfoBO.getLanguage());
				ps.setString(++index,
						COMM_UTILS.booleanToString(movieInfoBO.isWatched()));

				ps.addBatch();

				if (++count % batchSize == 0) {
					ps.executeBatch();
				}
			}
			ps.executeBatch();
			insertFlag = true;
		} catch (final SQLException e) {
			LOGGER.severe(e.getMessage());
		} finally {
			DB_UTILS.closeResources(null, ps, null, conn);
			ps = null;
			conn = null;
		}
		return insertFlag;
	}

	/**
	 * Method fetch all movie information and return as a List<MovieInfoBO>.
	 * @return movieInfoBOs
	 */
	@Override
	public List<MovieInfoBO> getMovieInfos() {
		final Connection conn = DB_UTILS.getConnection();
		final String selectQuery = QueryConstant.SEL_MOVIE_INFO_QUERY;
		Statement stmt = null;
		ResultSet rs = null;
		final List<MovieInfoBO> movieInfoBOs = new ArrayList<MovieInfoBO>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);
			setResultSetData(movieInfoBOs, rs);
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		return movieInfoBOs;
	}

	/**
	 * Method fills the result set data in input List<MovieInfoBOs>.
	 * @param movieInfoBOs 
	 * @param rs 
	 * @throws SQLException 
	 */
	private void setResultSetData(final List<MovieInfoBO> movieInfoBOs,
			final ResultSet rs) throws SQLException {
		while (rs.next()) {
			final MovieInfoBO movieInfoBO = new MovieInfoBO();
			movieInfoBO.setFileLocation(rs.getString("FILE_LOCATION"));
			movieInfoBO.setFileName(rs.getString("FILE_NAME"));
			movieInfoBO.setFileSize(rs.getString("FILE_SIZE"));
			movieInfoBO.setFileType(rs.getString("FILE_TYPE"));
			movieInfoBO.setId(rs.getInt("ID"));
			movieInfoBO.setLanguage(rs.getString("LANGUAGE"));
			movieInfoBO.setMovieGenre(rs.getString("MOVIE_GENRE"));
			movieInfoBO.setMovieRating(CommonUtils.convertObjToFloat(rs
					.getString("MOVIE_RATING")));
			movieInfoBO.setReleasedYear(rs.getString("RELEASED_YEAR"));
			movieInfoBO.setWatched(StringUtils.convertNullToBlank(
					rs.getString("IS_WATCHED")).equals("Y"));

			movieInfoBOs.add(movieInfoBO);
		}
	}
}
