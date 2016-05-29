/**
 * 
 */
package com.movie.dataprovider.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.movie.bo.MovieInfoBO;
import com.movie.constant.UIConstant;
import com.movie.dao.MovieInfoDAO;
import com.movie.dao.impl.MovieInfoDAOImpl;
import com.movie.dataprovider.MovieInfoDataProv;
import com.movie.dto.MovieInfoDTO;
import com.movie.dto.MovieInfoInputDTO;
import com.movie.exception.MovieException;
import com.movie.util.CommonUtils;
import com.movie.util.FileUtils;
import com.movie.util.MovieComparator;
import com.movie.util.StringUtils;

/**
 * @author cdacr
 * 
 */
public final class MovieInfoDataProvImpl implements MovieInfoDataProv {

	/**
	 * 
	 */
	private static final FileUtils UTILS = new FileUtils();
	/**
	 * 
	 */
	private static final MovieInfoDAO INFO_DAO = new MovieInfoDAOImpl();
	/**
	 * 
	 */
	private static final CommonUtils COMMON_UTILS = new CommonUtils();

	/**
	 * Method returns all movie information from database after 
	 * performing some action based on input DTO object.
	 * @param inputDTO 
	 * @return List<MovieInfoDTO>
	 * @throws MovieException 
	 */
	public List<MovieInfoDTO> getMovieInfo(final MovieInfoInputDTO inputDTO)
			throws MovieException {
		List<MovieInfoDTO> movieInfoDTOs = null;
		// Load data from given path, sync that data in DB and return it
		if (inputDTO.getLoadDataOption().equals(
				UIConstant.SYNC_AND_READ_FILES_PATH.getText())) {
			List<MovieInfoBO> fileMovieBOs = COMMON_UTILS
					.convertMovieDTOToBO(UTILS.getMovieInfo(inputDTO));
			final List<MovieInfoBO> dbMovieBOs = INFO_DAO.getMovieInfos();
			fileMovieBOs = COMMON_UTILS.getFirstMinusSecondData(fileMovieBOs,
					dbMovieBOs);
			if (INFO_DAO.insertMovieInfos(fileMovieBOs)) {
				movieInfoDTOs = COMMON_UTILS.convertMovieBOToDTO(INFO_DAO
						.getMovieInfos());
			}
		} else {
			movieInfoDTOs = COMMON_UTILS.convertMovieBOToDTO(INFO_DAO
					.getMovieInfos());
		}

		if (!StringUtils.convertNullToBlank(inputDTO.getOrderBy()).equals("")) {
			final MovieComparator comparator = new MovieComparator();
			comparator.setOrderByCol(inputDTO.getOrderBy());
			Collections.sort(movieInfoDTOs, comparator);
		}

		return movieInfoDTOs;
	}

	/**
	 * Method deletes the files in DB and from the file location.
	 * @param fileInfos 
	 * @return TRUE|FALSE
	 * @throws MovieException 
	 */
	public boolean deleteFile(final Map<Integer, String> fileInfos)
			throws MovieException {
		boolean delFlag = false;
		final Collection<String> filePaths = fileInfos.values();
		for (final String filePath : filePaths) {
			delFlag = UTILS.deleteFile(filePath);
		}

		final Set<Integer> keys = fileInfos.keySet();
		final List<Integer> ids = new ArrayList<Integer>();
		ids.addAll(keys);
		delFlag = INFO_DAO.deleteMovieInfos(ids);
		return delFlag;
	}

	/**
	 * Method renames the files in file path and DB.
	 * @param updDTOs 
	 * @return TRUE|FALSE
	 * @throws MovieException 
	 */
	public boolean renameFile(final List<MovieInfoDTO> updDTOs)
			throws MovieException {
		boolean updFlag = false;
		for (final MovieInfoDTO updDTO : updDTOs) {
			String location = updDTO.getFileLocation();
			updFlag = UTILS.renameFile(location, updDTO.getFileName());
			if (updFlag) {
				location = location.substring(0, location.lastIndexOf('\\'))
						+ "\\" + updDTO.getFileName();
				updDTO.setFileLocation(location);
			}
		}
		final List<MovieInfoBO> movieInfoBOs = COMMON_UTILS
				.convertMovieDTOToBO(updDTOs);
		updFlag = INFO_DAO.updateMovieInfos(movieInfoBOs);
		return updFlag;
	}
}
