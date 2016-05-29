/**
 * 
 */
package com.movie.validator;

import java.util.ArrayList;
import java.util.List;

import com.movie.util.StringUtils;

/**
 * @author cdacr
 * 
 */
public class UIValidator {

	/**
	 * Method to validate data for refresh button action.
	 * @param path 
	 * @return List<String> which contains error messages
	 */
	public final List<String> refreshActionValidation(final String path) {
		final List<String> errorList = new ArrayList<String>();
		if ("".equals(StringUtils.convertNullToBlank(path))) {
			errorList.add("Location cannot left empty!");
		}

		return errorList;
	}

}
