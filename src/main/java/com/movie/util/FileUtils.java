package com.movie.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.movie.constant.UIConstant;
import com.movie.dto.MovieInfoDTO;
import com.movie.dto.MovieInfoInputDTO;
import com.movie.exception.MovieException;
import com.movie.rest.client.ImdbClient;

/**
 * 
 * @author cdacr
 *
 */
public final class FileUtils {

	/**
	 * 
	 */
	private static final ImdbClient IMDB_CLIENT = new ImdbClient();

	/**
	 * 
	 */
	private static final CommonUtils COMM_UTILS = new CommonUtils();

	/**
	 * 
	 */
	private static final int BYTES = 1024;

	/**
	 * 
	 * @param inputDTO 
	 * @return list of MovieInfoDTO
	 * @throws MovieException 
	 */
	public List<MovieInfoDTO> getMovieInfo(final MovieInfoInputDTO inputDTO)
			throws MovieException {
		final List<MovieInfoDTO> movieList = new ArrayList<MovieInfoDTO>();
		final String[] filePaths = inputDTO.getFilePath().split(";");
		for (final String filePath : filePaths) {
			readFileInfo(movieList, filePath);
		}

		return movieList;
	}

	/**
	 * 
	 * @param movieList 
	 * @param movieFolder 
	 * @throws MovieException 
	 */
	private void readFileInfo(final List<MovieInfoDTO> movieList,
			final String movieFolder) throws MovieException {
		File file = null;
		final List<String> mediaList = COMM_UTILS.getMediaFilesExtn();
		try {
			file = new File(movieFolder);
			if (file.exists()) {
				if (file.isFile()) {
					final MovieInfoDTO movieDTO = new MovieInfoDTO();
					final String fileName = file.getName();
					final int lastDotIndex = fileName.lastIndexOf('.');
					movieDTO.setFileName(fileName);
					if (lastDotIndex != -1) {
						movieDTO.setFileType(fileName
								.substring(lastDotIndex + 1));
					}
					movieDTO.setFileLocation(file.getAbsolutePath());
					movieDTO.setFileSize(String.valueOf(file.length()
							/ (BYTES * BYTES)));
					if (fileName != null && fileName.indexOf('.') != -1
							&& mediaList.contains(movieDTO.getFileType())) {
						final JSONObject imdbObj = IMDB_CLIENT
								.getImdbInfo(fileName.substring(0,
										fileName.lastIndexOf('.')));
						movieDTO.setMovieRating(CommonUtils
								.convertObjToFloat(imdbObj
										.get(UIConstant.IMDB_RATING.getText())));
						movieDTO.setMovieGenre(StringUtils
								.convertNullToBlank(imdbObj
										.get(UIConstant.GENRE.getText())));
						movieDTO.setReleasedYear(StringUtils
								.convertNullToBlank(imdbObj.get(UIConstant.YEAR
										.getText())));
						movieDTO.setLanguage(StringUtils
								.convertNullToBlank(imdbObj
										.get(UIConstant.LANGUAGE.getText())));
					}
					if (!movieDTO.getFileType().equals("srt")) {
						movieList.add(movieDTO);
					}
				} else {
					final File[] dirFiles = file.listFiles();
					for (final File cFile : dirFiles) {
						readFileInfo(movieList, cFile.getAbsolutePath());
					}
				}
			} else {
				throw new MovieException("File/Folder does not exists: "
						+ file.getAbsolutePath());
			}
		} catch (final Exception ex) {

		}
	}

	/**
	 * 
	 * @param filePath 
	 * @return TRUE|FALSE
	 * @throws MovieException 
	 */
	public boolean deleteFile(final String filePath) throws MovieException {
		boolean delFlag = false;
		File file = null;
		try {
			file = new File(filePath);
			if (!file.exists() && !file.isFile()) {
				throw new MovieException(
						"Either file does not exists or it is a directory!"
								+ filePath);
			} else {
				delFlag = file.delete();
			}
		} catch (final Exception ex) {

		}
		return delFlag;
	}

	/**
	 * 
	 * @param filePath 
	 * @param newFileName 
	 * @return TRUE|FALSE
	 * @throws MovieException 
	 */
	public boolean renameFile(final String filePath, final String newFileName)
			throws MovieException {
		boolean renameFlag = false;
		File file = null;
		try {
			file = new File(filePath);
			if (!file.exists() && !file.isFile()) {
				throw new MovieException(
						"Either file does not exists or it is a directory!"
								+ filePath);
			} else {
				final int folderPathIndex = filePath.lastIndexOf('\\');
				final File newFile = new File(filePath.substring(0,
						folderPathIndex) + "\\" + newFileName);
				renameFlag = file.renameTo(newFile);
			}
		} catch (final Exception ex) {

		}
		return renameFlag;
	}
}
