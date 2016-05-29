/**
 * 
 */
package com.movie.dto;

/**
 * @author cdacr
 * 
 */
public final class MovieInfoDTO {

	/**
	 * 
	 */
	private int id;
	/**
	 * 
	 */
	private String fileName;
	/**
	 * 
	 */
	private String fileLocation;
	/**
	 * 
	 */
	private String fileType;
	/**
	 * 
	 */
	private float movieRating;
	/**
	 * 
	 */
	private String movieGenre;
	/**
	 * 
	 */
	private String releasedYear;
	/**
	 * 
	 */
	private String fileSize;
	/**
	 * 
	 */
	private String language;
	/**
	 * 
	 */
	private boolean watched;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileLocation
	 */
	public String getFileLocation() {
		return fileLocation;
	}

	/**
	 * @param fileLocation
	 *            the fileLocation to set
	 */
	public void setFileLocation(final String fileLocation) {
		this.fileLocation = fileLocation;
	}

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType
	 *            the fileType to set
	 */
	public void setFileType(final String fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return the movieRating
	 */
	public float getMovieRating() {
		return movieRating;
	}

	/**
	 * @param movieRating
	 *            the movieRating to set
	 */
	public void setMovieRating(final float movieRating) {
		this.movieRating = movieRating;
	}

	/**
	 * @return the movieGenre
	 */
	public String getMovieGenre() {
		return movieGenre;
	}

	/**
	 * @param movieGenre
	 *            the movieGenre to set
	 */
	public void setMovieGenre(final String movieGenre) {
		this.movieGenre = movieGenre;
	}

	/**
	 * @return the releasedYear
	 */
	public String getReleasedYear() {
		return releasedYear;
	}

	/**
	 * @param releasedYear
	 *            the releasedYear to set
	 */
	public void setReleasedYear(final String releasedYear) {
		this.releasedYear = releasedYear;
	}

	/**
	 * @return the fileSize
	 */
	public String getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize
	 *            the fileSize to set
	 */
	public void setFileSize(final String fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(final String language) {
		this.language = language;
	}

	/**
	 * @return the watched
	 */
	public boolean isWatched() {
		return watched;
	}

	/**
	 * @param watched the watched to set
	 */
	public void setWatched(final boolean watched) {
		this.watched = watched;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.fileName + "\n" + this.fileLocation + "\n" + this.fileType
				+ "\n" + this.fileSize + "\n";
	}
}
