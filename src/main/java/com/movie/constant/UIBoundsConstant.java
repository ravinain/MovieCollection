/**
 * 
 */
package com.movie.constant;

/**
 * @author cdacr
 *
 */
public enum UIBoundsConstant {
	/**
	 * 
	 */
	RN_BTN(50, 10, 100, 35),
	/**
	 * 
	 */
	DEL_BTN(170, 10, 100, 35),
	/**
	 * 
	 */
	REF_BTN(290, 10, 100, 35),
	/**
	 * 
	 */
	PTH_LBL(50, 60, 100, 35),
	/**
	 * 
	 */
	PTH_TXT(160, 60, 500, 35),
	/**
	 * 
	 */
	ORD_LBL(50, 105, 100, 35),
	/**
	 * 
	 */
	ORD_CB(160, 105, 300, 35),
	/**
	 * 
	 */
	SYNC_RB(50, 150, 250, 35),
	/**
	 * 
	 */
	LD_RB(310, 150, 150, 35),
	/**
	 * 
	 */
	MOV_PANE(30, 215, 1300, 500),
	/**
	 * 
	 */
	UPD_LBL(30, 730, 1100, 300),
	/**
	 * 
	 */
	LD_DIA(200, 110),
	/**
	 * 
	 */
	MN_FRM(1400, 900);
	/**
	 * 
	 */
	private int x;
	/**
	 * 
	 */
	private int y;
	/**
	 * 
	 */
	private int wd;
	/**
	 * 
	 */
	private int ht;

	/**
	 * 
	 * @param x 
	 * @param y 
	 * @param wd 
	 * @param ht 
	 */
	UIBoundsConstant(final int x, final int y, final int wd, final int ht) {
		this.x = x;
		this.y = y;
		this.wd = wd;
		this.ht = ht;
	}

	/**
	 * 
	 * @param wd 
	 * @param ht 
	 */
	UIBoundsConstant(final int wd, final int ht) {
		this.wd = wd;
		this.ht = ht;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(final int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(final int y) {
		this.y = y;
	}

	/**
	 * @return the wd
	 */
	public int getWd() {
		return wd;
	}

	/**
	 * @param wd the wd to set
	 */
	public void setWd(final int wd) {
		this.wd = wd;
	}

	/**
	 * @return the ht
	 */
	public int getHt() {
		return ht;
	}

	/**
	 * @param ht the ht to set
	 */
	public void setHt(final int ht) {
		this.ht = ht;
	}
}
