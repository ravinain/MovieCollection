/**
 * 
 */
package com.movie.ui;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.json.simple.JSONObject;

import com.movie.constant.UIConstant;
import com.movie.constant.UITableMapping;
import com.movie.dto.MovieInfoDTO;
import com.movie.rest.client.ImdbClient;
import com.movie.util.CommonUtils;
import com.movie.util.StringUtils;

/**
 * @author cdacr
 * 
 */
public final class MovieTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final boolean DEBUG = false;
	/**
	 * 
	 */
	private static final ImdbClient IMDB_CLIENT = new ImdbClient();
	/**
	 * 
	 */
	private static final CommonUtils COMMON_UTILS = new CommonUtils();

	/**
	 * 
	 */
	private static final long serialVersionUID = 7687052518598886209L;
	/**
	 * 
	 */
	private final String[] columnNames = COMMON_UTILS.getJTableHeaderColumns();
	/**
	 * 
	 */
	private final List<Object[]> data;
	/**
	 * 
	 */
	private final List<MovieInfoDTO> updSelData = new ArrayList<MovieInfoDTO>();
	/**
	 * 
	 */
	private final Map<Integer, String> delSelData = new HashMap<>();

	/**
	 * 
	 */
	public MovieTableModel() {
		data = new ArrayList<Object[]>();
	}

	/**
	 * 
	 */
	public void clearData() {
		data.clear();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public String getColumnName(final int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(final int row, final int col) {
		Object obj = new Object();
		if (data != null && data.size() > row) {
			obj = data.get(row)[col];
		}
		return obj;
	}

	/*
	 * JTable uses this method to determine the default renderer/ editor for
	 * each cell. If we didn't implement this method, then the last column would
	 * contain text ("true"/"false"), rather than a check box.
	 */

	@Override
	public Class<? extends Object> getColumnClass(final int c) {
		final Class<? extends Object> obj = getValueAt(0, c).getClass();
		return obj;
	}

	@Override
	public boolean isCellEditable(final int row, final int col) {
		return col == UITableMapping.FILE_NAME.getIndex()
				|| col == UITableMapping.UPDATE.getIndex()
				|| col == UITableMapping.DELETE.getIndex()
				|| col == UITableMapping.WATCHED.getIndex();
	}

	@Override
	public void setValueAt(final Object value, final int row, final int col) {
		if (DEBUG) {
			System.out.println("Setting value at " + row + "," + col + " to "
					+ value + " (an instance of " + value.getClass() + ")");
		}

		data.get(row)[col] = value;

		if (col == UITableMapping.WATCHED.getIndex()) {
			data.get(row)[UITableMapping.UPDATE.getIndex()] = true;
		} else if (col == UITableMapping.FILE_NAME.getIndex()
				|| col == UITableMapping.UPDATE.getIndex()) {
			// If File name cell value has changed then set update checkbox to
			// true
			data.get(row)[UITableMapping.UPDATE.getIndex()] = true;
			final String fileName = StringUtils.convertNullToBlank(data
					.get(row)[UITableMapping.FILE_NAME.getIndex()]);
			if (fileName != null
					&& fileName.indexOf('.') != -1
					&& (data.get(row)[UITableMapping.TYPE.getIndex()]
							.equals("mkv")
							|| data.get(row)[UITableMapping.TYPE.getIndex()]
									.equals("mp4")
							|| data.get(row)[UITableMapping.TYPE.getIndex()]
									.equals("avi") || data.get(row)[UITableMapping.TYPE
							.getIndex()].equals("flv"))) {
				try {
					JSONObject imdbObj = null;
					imdbObj = IMDB_CLIENT.getImdbInfo(fileName.substring(0,
							fileName.lastIndexOf('.')));
					data.get(row)[UITableMapping.RATING.getIndex()] = CommonUtils
							.convertObjToFloat(imdbObj
									.get(UIConstant.IMDB_RATING.getText()));
					data.get(row)[UITableMapping.GENRE.getIndex()] = StringUtils
							.convertNullToBlank(imdbObj.get(UIConstant.GENRE
									.getText()));
					data.get(row)[UITableMapping.YEAR.getIndex()] = StringUtils
							.convertNullToBlank(imdbObj.get(UIConstant.YEAR
									.getText()));
					data.get(row)[UITableMapping.LANGUAGE.getIndex()] = StringUtils
							.convertNullToBlank(imdbObj.get(UIConstant.LANGUAGE
									.getText()));
				} catch (final SocketTimeoutException ste) {
					ste.printStackTrace();
				}
			}
		}
		fireTableCellUpdated(row, col);
		setSelectedRecord();
		if (DEBUG) {
			System.out.println("New value of data:");
			printDebugData();
		}
	}

	/**
	 * 
	 * @param movieInfoDTOs 
	 */
	public void insertData(final List<MovieInfoDTO> movieInfoDTOs) {
		final int rowCount = getRowCount();

		if (movieInfoDTOs != null) {
			final int movieLen = movieInfoDTOs.size();
			for (int index = 0; index < movieLen; index++) {
				final MovieInfoDTO movieDTO = movieInfoDTOs.get(index);
				final Object[] colData = new Object[columnNames.length];
				colData[UITableMapping.ID.getIndex()] = movieDTO.getId();
				colData[UITableMapping.FILE_NAME.getIndex()] = StringUtils
						.convertNullToBlank(movieDTO.getFileName());
				colData[UITableMapping.LOCATION.getIndex()] = StringUtils
						.convertNullToBlank(movieDTO.getFileLocation());
				colData[UITableMapping.TYPE.getIndex()] = StringUtils
						.convertNullToBlank(movieDTO.getFileType());
				colData[UITableMapping.SIZE.getIndex()] = StringUtils
						.convertNullToBlank(movieDTO.getFileSize());
				colData[UITableMapping.GENRE.getIndex()] = StringUtils
						.convertNullToBlank(movieDTO.getMovieGenre());
				colData[UITableMapping.RATING.getIndex()] = movieDTO
						.getMovieRating();
				colData[UITableMapping.YEAR.getIndex()] = StringUtils
						.convertNullToBlank(movieDTO.getReleasedYear());
				colData[UITableMapping.LANGUAGE.getIndex()] = StringUtils
						.convertNullToBlank(movieDTO.getLanguage());
				colData[UITableMapping.WATCHED.getIndex()] = movieDTO
						.isWatched();
				colData[UITableMapping.UPDATE.getIndex()] = Boolean.FALSE;
				colData[UITableMapping.DELETE.getIndex()] = Boolean.FALSE;
				data.add(colData);
			}
			System.out.println("Rowcount: " + rowCount + "Total len: "
					+ movieLen);
			fireTableRowsInserted(rowCount, rowCount + movieLen);
		}
	}

	/**
	 * 
	 */
	private void printDebugData() {
		final int numRows = getRowCount();
		final int numCols = getColumnCount();

		for (int i = 0; i < numRows; i++) {
			System.out.print("    row " + i + ":");
			for (int j = 0; j < numCols; j++) {
				System.out.print("  " + data.get(i)[j]);
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}

	/**
	 * 
	 * @return list of MovieInfoDTO which have been modified.
	 */
	public List<MovieInfoDTO> getUpdatedSelectedRecord() {
		return updSelData;
	}

	/**
	 * 
	 * @return Map of selected deleted records.
	 */
	public Map<Integer, String> getDeletedSelectedRecord() {
		return delSelData;
	}

	/**
	 * 
	 */
	private void setSelectedRecord() {
		final int numRows = getRowCount();
		updSelData.clear();
		delSelData.clear();

		for (int i = 0; i < numRows; i++) {
			if ((Boolean) data.get(i)[UITableMapping.UPDATE.getIndex()]) {
				final MovieInfoDTO updDTO = new MovieInfoDTO();
				updDTO.setFileSize(String.valueOf(data.get(i)[UITableMapping.SIZE
						.getIndex()]));
				updDTO.setFileType(String.valueOf(data.get(i)[UITableMapping.TYPE
						.getIndex()]));
				updDTO.setId(Integer.valueOf(String.valueOf(data.get(i)[UITableMapping.ID
						.getIndex()])));
				updDTO.setLanguage(String.valueOf(data.get(i)[UITableMapping.LANGUAGE
						.getIndex()]));
				updDTO.setMovieGenre(String.valueOf(data.get(i)[UITableMapping.GENRE
						.getIndex()]));
				updDTO.setMovieRating(Float.valueOf(String.valueOf(data.get(i)[UITableMapping.RATING
						.getIndex()])));
				updDTO.setReleasedYear(String.valueOf(data.get(i)[UITableMapping.YEAR
						.getIndex()]));
				updDTO.setWatched(Boolean.valueOf(String.valueOf(data.get(i)[UITableMapping.WATCHED
						.getIndex()])));
				updDTO.setFileLocation(String.valueOf(data.get(i)[UITableMapping.LOCATION
						.getIndex()]));
				updDTO.setFileName(String.valueOf(data.get(i)[UITableMapping.FILE_NAME
						.getIndex()]));
				updSelData.add(updDTO);
			}
			if ((Boolean) data.get(i)[UITableMapping.DELETE.getIndex()]) {
				delSelData
						.put(CommonUtils.convertObjToInteger(data.get(i)[UITableMapping.ID
								.getIndex()]),
								String.valueOf(data.get(i)[UITableMapping.LOCATION
										.getIndex()]));
			}
		}
	}
}
