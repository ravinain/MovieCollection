/**
 * 
 */
package com.movie.ui;

import java.awt.Rectangle;

import com.movie.constant.UIBoundsConstant;

/**
 * @author cdacr
 *
 */
public final class MovieRectangle extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2914691806968328519L;

	/**
	 * 
	 */
	private UIBoundsConstant boundsConstant;

	/**
	 * 
	 * @param boundsConstant 
	 */
	public MovieRectangle(final UIBoundsConstant boundsConstant) {
		if (UIBoundsConstant.LD_DIA.equals(boundsConstant)
				|| UIBoundsConstant.MN_FRM.equals(boundsConstant)) {
			this.setSize(boundsConstant.getWd(), boundsConstant.getHt());
		} else {
			this.setBounds(boundsConstant.getX(), boundsConstant.getY(),
					boundsConstant.getWd(), boundsConstant.getHt());
		}
	}

	/**
	 * @return the boundsConstant
	 */
	public UIBoundsConstant getBoundsConstant() {
		return boundsConstant;
	}

	/**
	 * @param boundsConstant the boundsConstant to set
	 */
	public void setBoundsConstant(final UIBoundsConstant boundsConstant) {
		this.boundsConstant = boundsConstant;
	}

}
