package com.movie.util;

import java.util.Vector;

/**
 * 
 * @author cdacr
 *
 */
public final class OrderByUtils {

	/**
	 * 
	 */
	private OrderByUtils() {
	}

	/**
	 * 
	 * @author cdacr
	 *
	 */
	enum OrderList {
		/**
		 * 
		 */
		ASC_NAME("Ascending By Name"),
		/**
		 * 
		 */
		DESC_NAME("Descending By Name"),
		/**
		 * 
		 */
		ASC_SIZE("Ascending By Size"),
		/**
		 * 
		 */
		DESC_SIZE("Descending By Size"),
		/**
		 * 
		 */
		ASC_TYPE("Ascending By Type"),
		/**
		 * 
		 */
		DESC_TYPE("Descending By Type"),
		/**
		 * 
		 */
		ASC_GENRE("Ascending By Genre"),
		/**
		 * 
		 */
		DESC_GENRE("Descending By Genre"),
		/**
		 * 
		 */
		ASC_RATING("Ascending By Rating"),
		/**
		 * 
		 */
		DESC_RATING("Descending By Rating"),
		/**
		 * 
		 */
		ASC_YEAR("Ascending By Year"),
		/**
		 * 
		 */
		DESC_YEAR("Descending By Year"),
		/**
		 * 
		 */
		ASC_LANGUAGE("Ascending By Language"),
		/**
		 * 
		 */
		DESC_LANGUAGE("Descending By Language");

		/**
		 * 
		 */
		private final String desc;

		/**
		 * 
		 * @param desc 
		 */
		OrderList(final String desc) {
			this.desc = desc;
		}

		/**
		 * 
		 * @return desc
		 */
		public String getDesc() {
			return desc;
		}

	}

	/**
	 * 
	 * @return order list in vector form
	 */
	public static Vector<String> getOrderListValues() {
		final Vector<String> orderList = new Vector<String>();

		for (final OrderList oList : OrderList.values()) {
			orderList.add(oList.getDesc());
		}
		return orderList;
	}

}
