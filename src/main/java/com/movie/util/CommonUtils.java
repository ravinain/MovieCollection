/**
 * 
 */
package com.movie.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.movie.bo.MovieInfoBO;
import com.movie.constant.UITableMapping;
import com.movie.dto.MovieInfoDTO;

/**
 * @author cdacr
 * 
 */
public final class CommonUtils {

	/**
	 * 
	 * @param obj 
	 * @return float value
	 */
	public static Float convertObjToFloat(final Object obj) {
		Float f = 0f;

		if (!StringUtils.convertNullToBlank(obj).equals("")) {
			try {
				f = Float.valueOf(obj.toString());
			} catch (final NumberFormatException nfe) {
			}
		}
		return f;
	}

	/**
	 * 
	 * @param obj 
	 * @return integer value
	 */
	public static Integer convertObjToInteger(final Object obj) {
		Integer i = 0;

		if (obj != null) {
			try {
				i = Integer.valueOf(String.valueOf(obj));
			} catch (final Exception ex) {

			}
		}

		return i;
	}

	/**
	 * 
	 * @return array of String with header column values
	 */
	public String[] getJTableHeaderColumns() {
		final Map<Integer, String> colTextMap = new TreeMap<Integer, String>();
		for (final UITableMapping tableMap : UITableMapping.values()) {
			colTextMap.put(tableMap.getIndex(), tableMap.getText());
		}

		return colTextMap.values().toArray(new String[] {});
	}

	/**
	 * 
	 * @param firstList 
	 * @param secondList 
	 * @return firstList - secondList 
	 */
	public List<MovieInfoBO> getFirstMinusSecondData(
			final List<MovieInfoBO> firstList,
			final List<MovieInfoBO> secondList) {
		firstList.removeAll(secondList);
		return firstList;
	}

	/**
	 * 
	 * @param movieInfoDTOs 
	 * @return MovieInfoBO object of associated DTO.
	 */
	public List<MovieInfoBO> convertMovieDTOToBO(
			final List<MovieInfoDTO> movieInfoDTOs) {
		final List<MovieInfoBO> movieInfoBOs = new ArrayList<MovieInfoBO>();

		for (final MovieInfoDTO movieInfoDTO : movieInfoDTOs) {
			final MovieInfoBO movieInfoBO = new MovieInfoBO();
			movieInfoBO.setFileLocation(movieInfoDTO.getFileLocation());
			movieInfoBO.setFileName(movieInfoDTO.getFileName());
			movieInfoBO.setFileSize(movieInfoDTO.getFileSize());
			movieInfoBO.setFileType(movieInfoDTO.getFileType());
			movieInfoBO.setId(movieInfoDTO.getId());
			movieInfoBO.setLanguage(movieInfoDTO.getLanguage());
			movieInfoBO.setMovieGenre(movieInfoDTO.getMovieGenre());
			movieInfoBO.setMovieRating(movieInfoDTO.getMovieRating());
			movieInfoBO.setReleasedYear(movieInfoDTO.getReleasedYear());
			movieInfoBO.setWatched(movieInfoDTO.isWatched());

			movieInfoBOs.add(movieInfoBO);
		}

		return movieInfoBOs;
	}

	/**
	 * 
	 * @param movieInfoBOs 
	 * @return List of MovieInfoDTO object of associated BO.
	 */
	public List<MovieInfoDTO> convertMovieBOToDTO(
			final List<MovieInfoBO> movieInfoBOs) {
		final List<MovieInfoDTO> movieInfoDTOs = new ArrayList<MovieInfoDTO>();

		for (final MovieInfoBO movieInfoBO : movieInfoBOs) {
			final MovieInfoDTO movieInfoDTO = new MovieInfoDTO();
			movieInfoDTO.setFileLocation(movieInfoBO.getFileLocation());
			movieInfoDTO.setFileName(movieInfoBO.getFileName());
			movieInfoDTO.setFileSize(movieInfoBO.getFileSize());
			movieInfoDTO.setFileType(movieInfoBO.getFileType());
			movieInfoDTO.setId(movieInfoBO.getId());
			movieInfoDTO.setLanguage(movieInfoBO.getLanguage());
			movieInfoDTO.setMovieGenre(movieInfoBO.getMovieGenre());
			movieInfoDTO.setMovieRating(movieInfoBO.getMovieRating());
			movieInfoDTO.setReleasedYear(movieInfoBO.getReleasedYear());
			movieInfoDTO.setWatched(movieInfoBO.isWatched());

			movieInfoDTOs.add(movieInfoDTO);
		}

		return movieInfoDTOs;
	}

	/**
	 * 
	 * @param inBoolean 
	 * @return Y if TRUE else N
	 */
	public String booleanToString(final boolean inBoolean) {
		if (inBoolean) {
			return "Y";
		}
		return "N";
	}

	/**
	 * 
	 */
	private final List<String> mediaFiles = new ArrayList<>();

	/**
	 * 
	 * @return list of all media files extension
	 */
	public List<String> getMediaFilesExtn() {
		if (mediaFiles.isEmpty()) {
			mediaFiles.add("mkv");
			mediaFiles.add("mp4");
			mediaFiles.add("avi");
			mediaFiles.add("flv");
		}
		return mediaFiles;
	}
}
