/**
 * 
 */
package com.movie.util;

import java.util.Comparator;

import com.movie.dto.MovieInfoDTO;

/**
 * @author cdacr
 * 
 */
public final class MovieComparator implements Comparator<MovieInfoDTO> {

	/**
	 * 
	 */
	private String orderByCol;

	@Override
	public int compare(final MovieInfoDTO o1, final MovieInfoDTO o2) {
		if (orderByCol.equals(OrderByUtils.OrderList.ASC_NAME.getDesc())) {
			final int res = String.CASE_INSENSITIVE_ORDER.compare(
					o1.getFileName(), o2.getFileName());
			if (res != 0) {
				return res;
			}
			return o1.getFileName().compareTo(o2.getFileName());
		} else if (orderByCol
				.equals(OrderByUtils.OrderList.DESC_NAME.getDesc())) {
			final int res = String.CASE_INSENSITIVE_ORDER.compare(
					o2.getFileName(), o1.getFileName());
			if (res != 0) {
				return res;
			}
			return o2.getFileName().compareTo(o1.getFileName());
		} else if (orderByCol
				.equals(OrderByUtils.OrderList.ASC_GENRE.getDesc())) {
			System.out.println(o1.getMovieGenre() + " : " + o2.getMovieGenre());
			final int res = String.CASE_INSENSITIVE_ORDER.compare(
					StringUtils.convertNullToBlank(o1.getMovieGenre()),
					StringUtils.convertNullToBlank(o2.getMovieGenre()));
			if (res != 0) {
				return res;
			}
			return StringUtils.convertNullToBlank(o1.getMovieGenre())
					.compareTo(
							StringUtils.convertNullToBlank(o2.getMovieGenre()));
		} else if (orderByCol.equals(OrderByUtils.OrderList.DESC_GENRE
				.getDesc())) {
			final int res = String.CASE_INSENSITIVE_ORDER.compare(
					StringUtils.convertNullToBlank(o2.getMovieGenre()),
					StringUtils.convertNullToBlank(o1.getMovieGenre()));
			if (res != 0) {
				return res;
			}
			return StringUtils.convertNullToBlank(o2.getMovieGenre())
					.compareTo(
							StringUtils.convertNullToBlank(o1.getMovieGenre()));
		} else if (orderByCol.equals(OrderByUtils.OrderList.ASC_YEAR.getDesc())) {
			final int res = String.CASE_INSENSITIVE_ORDER.compare(
					StringUtils.convertNullToBlank(o1.getReleasedYear()),
					StringUtils.convertNullToBlank(o2.getReleasedYear()));
			if (res != 0) {
				return res;
			}
			return StringUtils
					.convertNullToBlank(o1.getReleasedYear())
					.compareTo(
							StringUtils.convertNullToBlank(o2.getReleasedYear()));
		} else if (orderByCol
				.equals(OrderByUtils.OrderList.DESC_YEAR.getDesc())) {
			final int res = String.CASE_INSENSITIVE_ORDER.compare(
					StringUtils.convertNullToBlank(o2.getReleasedYear()),
					StringUtils.convertNullToBlank(o1.getReleasedYear()));
			if (res != 0) {
				return res;
			}
			return StringUtils
					.convertNullToBlank(o2.getReleasedYear())
					.compareTo(
							StringUtils.convertNullToBlank(o1.getReleasedYear()));
		} else if (orderByCol.equals(OrderByUtils.OrderList.ASC_LANGUAGE
				.getDesc())) {
			final int res = String.CASE_INSENSITIVE_ORDER.compare(
					StringUtils.convertNullToBlank(o1.getLanguage()),
					StringUtils.convertNullToBlank(o2.getLanguage()));
			if (res != 0) {
				return res;
			}
			return StringUtils.convertNullToBlank(o1.getLanguage()).compareTo(
					StringUtils.convertNullToBlank(o2.getLanguage()));
		} else if (orderByCol.equals(OrderByUtils.OrderList.DESC_LANGUAGE
				.getDesc())) {
			final int res = String.CASE_INSENSITIVE_ORDER.compare(
					StringUtils.convertNullToBlank(o2.getLanguage()),
					StringUtils.convertNullToBlank(o1.getLanguage()));
			if (res != 0) {
				return res;
			}
			return StringUtils.convertNullToBlank(o2.getLanguage()).compareTo(
					StringUtils.convertNullToBlank(o1.getLanguage()));
		} else if (orderByCol.equals(OrderByUtils.OrderList.ASC_TYPE.getDesc())) {
			final int res = String.CASE_INSENSITIVE_ORDER.compare(
					o1.getFileType(), o2.getFileType());
			if (res != 0) {
				return res;
			}
			return o1.getFileType().compareTo(o2.getFileType());
		} else if (orderByCol
				.equals(OrderByUtils.OrderList.DESC_TYPE.getDesc())) {
			final int res = String.CASE_INSENSITIVE_ORDER.compare(
					o2.getFileType(), o1.getFileType());
			if (res != 0) {
				return res;
			}
			return o2.getFileType().compareTo(o1.getFileType());
		} else if (orderByCol.equals(OrderByUtils.OrderList.ASC_SIZE.getDesc())) {
			return (int) (Double.parseDouble(o1.getFileSize()) - Double
					.parseDouble(o2.getFileSize()));
		} else if (orderByCol
				.equals(OrderByUtils.OrderList.DESC_SIZE.getDesc())) {
			return (int) (Double.parseDouble(o2.getFileSize()) - Double
					.parseDouble(o1.getFileSize()));
		} else if (orderByCol.equals(OrderByUtils.OrderList.ASC_RATING
				.getDesc())) {
			if (o1.getMovieRating() > o2.getMovieRating()) {
				return 1;
			}
			if (o1.getMovieRating() < o2.getMovieRating()) {
				return -1;
			}
			return 0;
		} else if (orderByCol.equals(OrderByUtils.OrderList.DESC_RATING
				.getDesc())) {
			if (o1.getMovieRating() < o2.getMovieRating()) {
				return 1;
			}
			if (o1.getMovieRating() > o2.getMovieRating()) {
				return -1;
			}
			return 0;
		}
		return 0;
	}

	/**
	 * @return the orderByCol
	 */
	public String getOrderByCol() {
		return orderByCol;
	}

	/**
	 * @param orderByCol
	 *            the orderByCol to set
	 */
	public void setOrderByCol(final String orderByCol) {
		this.orderByCol = orderByCol;
	}
}
