package com.movie.dataprovider;

import java.util.List;
import java.util.Map;

import com.movie.dto.MovieInfoDTO;
import com.movie.dto.MovieInfoInputDTO;
import com.movie.exception.MovieException;

/**
 * Contract of performing action on movie information.
 * @author cdacr
 *
 */
public interface MovieInfoDataProv {
	/**
	 * Method returns all movie information from database after 
	 * performing some action based on input DTO object.
	 * @param inputDTO 
	 * @return List<MovieInfoDTO>
	 * @throws MovieException 
	 */
	List<MovieInfoDTO> getMovieInfo(final MovieInfoInputDTO inputDTO)
			throws MovieException;

	/**
	 * Method deletes the files in DB and from the file location.
	 * @param filePaths 
	 * @return TRUE|FALSE
	 * @throws MovieException 
	 */
	boolean deleteFile(Map<Integer, String> filePaths) throws MovieException;

	/**
	 * Method renames the files in file path and DB.
	 * @param updDTOs 
	 * @return TRUE|FALSE
	 * @throws MovieException 
	 */
	boolean renameFile(List<MovieInfoDTO> updDTOs) throws MovieException;
}
