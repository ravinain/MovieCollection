/**
 * 
 */
package com.movie.dto;

/**
 * @author cdacr
 * 
 */
public final class MovieInfoInputDTO {
	/**
	 * 
	 */
	private String filePath;
	/**
	 * 
	 */
	private String orderBy;
	/**
	 * 
	 */
	private String loadDataOption;

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(final String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy
	 *            the orderBy to set
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return the loadDataOption
	 */
	public String getLoadDataOption() {
		return loadDataOption;
	}

	/**
	 * @param loadDataOption the loadDataOption to set
	 */
	public void setLoadDataOption(final String loadDataOption) {
		this.loadDataOption = loadDataOption;
	}
}
