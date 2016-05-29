/**
 * 
 */
package com.movie.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.movie.constant.UIBoundsConstant;
import com.movie.constant.UIConstant;
import com.movie.constant.UITableMapping;
import com.movie.dataprovider.MovieInfoDataProv;
import com.movie.dataprovider.impl.MovieInfoDataProvImpl;
import com.movie.dto.MovieInfoDTO;
import com.movie.dto.MovieInfoInputDTO;
import com.movie.exception.MovieException;
import com.movie.util.CommonUtils;
import com.movie.util.OrderByUtils;
import com.movie.validator.UIValidator;
import com.sun.istack.internal.logging.Logger;

/**
 * @author cdacr
 * 
 */
public final class MovieUI {

	/**
	 * 
	 */
	private JFrame mainFrame;
	/**
	 * 
	 */
	private JTable movieInfoTable;
	/**
	 * 
	 */
	private JScrollPane movieInfoPane;
	/**
	 * 
	 */
	private MovieTableModel movieTableModel;
	/**
	 * 
	 */
	private final MovieInfoDataProv movieDataProv = new MovieInfoDataProvImpl();
	/**
	 * 
	 */
	private JButton renameBtn, delBtn, refreshBtn;
	/**
	 * 
	 */
	private JLabel updLabel, pathLabel, orderByLabel, loadingLbl;
	/**
	 * 
	 */
	private JTextField pathTxt;
	/**
	 * 
	 */
	private JComboBox<String> orderByCb;
	/**
	 * 
	 */
	private JDialog loadingDialog;
	/**
	 * 
	 */
	private final ImageIcon icon = createImageIcon("/Loading.gif", "Loading");
	/**
	 * 
	 */
	private JRadioButton syncPathRadioBtn, loadFromDBRadioBtn;
	/**
	 * 
	 */
	private ButtonGroup loadBtnGrp;

	/**
	 * 
	 */
	private static final UIValidator VALIDATOR = new UIValidator();

	/**
	 * 
	 */
	private static final int MIN_FILE_SIZE = 30;

	/**
	 * 
	 */
	private static final int MAX_FILE_SIZE = 1000;

	/**
	 * 
	 */
	private static final int ROW_HEIGHT = 5;

	/**
	 * 
	 */
	private static final Logger LOGGER = Logger.getLogger(MovieUI.class);

	/**
	 * 
	 */
	MovieUI() {
		mainFrame = new JFrame(UIConstant.MOVIE_COLLECTION.getText());

		renameBtn = new JButton(UIConstant.UPDATE.getText());
		renameBtn.setBounds(new MovieRectangle(UIBoundsConstant.RN_BTN));
		renameBtn.addActionListener(new ButtonListener());
		mainFrame.add(renameBtn);

		delBtn = new JButton(UIConstant.DELETE.getText());
		delBtn.setBounds(new MovieRectangle(UIBoundsConstant.DEL_BTN));
		delBtn.addActionListener(new ButtonListener());
		mainFrame.add(delBtn);

		refreshBtn = new JButton(UIConstant.REFRESH.getText());
		refreshBtn.setBounds(new MovieRectangle(UIBoundsConstant.REF_BTN));
		refreshBtn.addActionListener(new ButtonListener());
		mainFrame.add(refreshBtn);

		pathLabel = new JLabel(UIConstant.LOCATION.getText());
		pathLabel.setBounds(new MovieRectangle(UIBoundsConstant.PTH_LBL));
		mainFrame.add(pathLabel);

		pathTxt = new JTextField();
		pathTxt.setBounds(new MovieRectangle(UIBoundsConstant.PTH_TXT));
		mainFrame.add(pathTxt);

		orderByLabel = new JLabel(UIConstant.ORDER_BY.getText());
		orderByLabel.setBounds(new MovieRectangle(UIBoundsConstant.ORD_LBL));
		mainFrame.add(orderByLabel);

		orderByCb = new JComboBox<String>(OrderByUtils.getOrderListValues());
		orderByCb.setBounds(new MovieRectangle(UIBoundsConstant.ORD_CB));
		mainFrame.add(orderByCb);

		syncPathRadioBtn = new JRadioButton(
				UIConstant.SYNC_AND_READ_FILES_PATH.getText());
		syncPathRadioBtn
				.setBounds(new MovieRectangle(UIBoundsConstant.SYNC_RB));

		loadFromDBRadioBtn = new JRadioButton(
				UIConstant.LOAD_FROM_DB.getText(), true);
		loadFromDBRadioBtn
				.setBounds(new MovieRectangle(UIBoundsConstant.LD_RB));

		loadBtnGrp = new ButtonGroup();
		loadBtnGrp.add(syncPathRadioBtn);
		loadBtnGrp.add(loadFromDBRadioBtn);

		mainFrame.add(syncPathRadioBtn);
		mainFrame.add(loadFromDBRadioBtn);

		movieTableModel = new MovieTableModel();
		movieInfoTable = new JTable(movieTableModel) {
			private static final long serialVersionUID = 3988713092791431759L;

			// Implement table cell tool tips.
			@Override
			public String getToolTipText(final MouseEvent e) {
				String tip = null;
				final java.awt.Point p = e.getPoint();
				final int rowIndex = rowAtPoint(p);
				final int colIndex = columnAtPoint(p);
				final int realColumnIndex = convertColumnIndexToModel(colIndex);

				if (realColumnIndex == 0) { // Veggie column
					final TableModel model = getModel();
					tip = (String) model.getValueAt(rowIndex, 0);
				}
				return tip;
			}

			@Override
			public Component prepareRenderer(final TableCellRenderer renderer,
					final int row, final int col) {
				final Component comp = super
						.prepareRenderer(renderer, row, col);
				if (col == UITableMapping.SIZE.getIndex()) {
					final Float value = CommonUtils
							.convertObjToFloat(getModel().getValueAt(row, col));
					if (value < MIN_FILE_SIZE) {
						comp.setBackground(Color.cyan);
						comp.setForeground(Color.BLACK);
					} else if (value > MAX_FILE_SIZE) {
						comp.setBackground(Color.red);
						comp.setForeground(Color.white);
					}
				} else if (!super.isRowSelected(row)) {
					comp.setBackground(super.getBackground());
					comp.setForeground(super.getForeground());
				}
				return comp;
			}
		};
		movieInfoTable.addMouseListener(new MouseListener());
		movieInfoTable.setRowHeight(movieInfoTable.getRowHeight() + ROW_HEIGHT);

		movieInfoPane = new JScrollPane(movieInfoTable);
		movieInfoPane.setBounds(new MovieRectangle(UIBoundsConstant.MOV_PANE));

		mainFrame.add(movieInfoPane, BorderLayout.SOUTH);

		updLabel = new JLabel();
		updLabel.setBounds(new MovieRectangle(UIBoundsConstant.UPD_LBL));

		mainFrame.add(updLabel);

		loadingDialog = new JDialog(mainFrame, true);
		loadingDialog.setBounds(new MovieRectangle(UIBoundsConstant.LD_DIA));
		loadingDialog.setLocationRelativeTo(null);

		final Container loadingContainer = loadingDialog.getContentPane();
		loadingLbl = new JLabel(icon, JLabel.CENTER);
		loadingContainer.add(loadingLbl, BorderLayout.NORTH);

		mainFrame.setLayout(new BorderLayout());
		mainFrame.setBounds(new MovieRectangle(UIBoundsConstant.MN_FRM));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * Method find the selected radio button in the input btnGrp and 
	 * return it's text.
	 * @param btnGrp 
	 * @return selected radio button's text
	 */
	public String getSelectedRadioButton(final ButtonGroup btnGrp) {
		String selected = "";
		final Enumeration<AbstractButton> radioBtns = btnGrp.getElements();
		while (radioBtns.hasMoreElements()) {
			final AbstractButton radioBtn = radioBtns.nextElement();
			if (radioBtn.isSelected()) {
				selected = radioBtn.getActionCommand();
				break;
			}
		}
		return selected;
	}

	/**
	 * Method finds the movie information based on input and 
	 * load that data in JTable.
	 * @throws MovieException 
	 */
	public void loadTableData() throws MovieException {
		final MovieInfoInputDTO inputDTO = new MovieInfoInputDTO();

		final String selected = getSelectedRadioButton(loadBtnGrp);

		final List<String> errorList = VALIDATOR
				.refreshActionValidation(pathTxt.getText());

		if (errorList.size() > 0
				&& selected.equals(UIConstant.SYNC_AND_READ_FILES_PATH
						.getText())) {
			updLabel.setText("");
			for (final String error : errorList) {
				updLabel.setText(updLabel.getText() + error + "\n");
			}
		} else {
			inputDTO.setFilePath(pathTxt.getText());
			inputDTO.setOrderBy(orderByCb.getSelectedItem().toString());

			inputDTO.setLoadDataOption(selected);

			final List<MovieInfoDTO> movieInfoDTOs = movieDataProv
					.getMovieInfo(inputDTO);

			movieTableModel.clearData();
			movieInfoTable.repaint();
			movieTableModel.insertData(movieInfoDTOs);
		}
	}

	/**
	 * Entry point of the program.
	 * @param args 
	 */
	public static void main(final String[] args) {
		final MovieUI ui = new MovieUI();
		try {
			ui.loadTableData();
			// ui.movieInfoTable.setAutoCreateRowSorter(true);
		} catch (final MovieException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * It defines the action which are going to perform 
	 * when button is clicked and that button has attached 
	 * the event handler of this class.
	 * @author cdacr
	 *
	 */
	private class ButtonListener implements ActionListener {
		/**
		 * 
		 */
		ButtonListener() {
		}

		/**
		 * Method perform  the action when user clicks on any button 
		 * on which {@link ButtonListener} has been attached.
		 * @param e 
		 */
		public void actionPerformed(final ActionEvent e) {
			final String actionCommand = e.getActionCommand();
			if (actionCommand.equals(UIConstant.DELETE.getText())) {
				performDeleteAction();
			} else if (actionCommand.equals(UIConstant.UPDATE.getText())) {
				performUpdateAction();
			} else if (actionCommand.equals(UIConstant.REFRESH.getText())) {
				performRefreshAction();
			}
		}

		/**
		 * Method performs delete action, when user clicks on Delete button.
		 */
		void performDeleteAction() {
			final Map<Integer, String> delRec = movieTableModel
					.getDeletedSelectedRecord();
			try {
				movieDataProv.deleteFile(delRec);
				loadTableData();
			} catch (final MovieException e1) {
				e1.printStackTrace();
				updLabel.setText("Error occured while renaming files");
			}
		}

		/**
		 * Method performs update action, when user clicks on Update button.
		 */
		void performUpdateAction() {
			final List<MovieInfoDTO> data = movieTableModel
					.getUpdatedSelectedRecord();
			try {
				movieDataProv.renameFile(data);
				loadTableData();
			} catch (final MovieException e1) {
				e1.printStackTrace();
				updLabel.setText("Error occured while renaming files");
			}
		}

		/**
		 * Method performs refresh action, when user clicks on Refresh button.
		 */
		void performRefreshAction() {
			try {
				loadingDialog.setTitle("Loading Dialog");
				loadTableData();

			} catch (final MovieException e1) {
				e1.printStackTrace();
			}

		}
	}

	/**
	 * It defines the action which will perform when any mouse event occurs.
	 * @author cdacr
	 *
	 */
	private class MouseListener extends MouseAdapter {
		@Override
		public void mousePressed(final MouseEvent me) {
			final JTable table = (JTable) me.getSource();
			final Point p = me.getPoint();
			final int row = table.rowAtPoint(p);
			if (me.getClickCount() == 2) {
				try {
					Desktop.getDesktop().open(
							new File(String.valueOf(movieTableModel.getValueAt(
									row, UITableMapping.LOCATION.getIndex()))));
				} catch (final IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Returns an ImageIcon, or null if the path was invalid.
	 * @param path 
	 * @param description 
	 * @return {@link ImageIcon}
	 */
	private static ImageIcon createImageIcon(final String path,
			final String description) {
		final java.net.URL imgURL = MovieUI.class.getResource(path);
		if (imgURL != null) {
			System.out.println(imgURL);
			return new ImageIcon(imgURL, description);
		} else {
			LOGGER.severe("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * @return the mainFrame
	 */
	public JFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * @param mainFrame the mainFrame to set
	 */
	public void setMainFrame(final JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	/**
	 * @return the movieInfoTable
	 */
	public JTable getMovieInfoTable() {
		return movieInfoTable;
	}

	/**
	 * @param movieInfoTable the movieInfoTable to set
	 */
	public void setMovieInfoTable(final JTable movieInfoTable) {
		this.movieInfoTable = movieInfoTable;
	}

	/**
	 * @return the movieInfoPane
	 */
	public JScrollPane getMovieInfoPane() {
		return movieInfoPane;
	}

	/**
	 * @param movieInfoPane the movieInfoPane to set
	 */
	public void setMovieInfoPane(final JScrollPane movieInfoPane) {
		this.movieInfoPane = movieInfoPane;
	}

	/**
	 * @return the movieTableModel
	 */
	public MovieTableModel getMovieTableModel() {
		return movieTableModel;
	}

	/**
	 * @param movieTableModel the movieTableModel to set
	 */
	public void setMovieTableModel(final MovieTableModel movieTableModel) {
		this.movieTableModel = movieTableModel;
	}

	/**
	 * @return the renameBtn
	 */
	public JButton getRenameBtn() {
		return renameBtn;
	}

	/**
	 * @param renameBtn the renameBtn to set
	 */
	public void setRenameBtn(final JButton renameBtn) {
		this.renameBtn = renameBtn;
	}

	/**
	 * @return the delBtn
	 */
	public JButton getDelBtn() {
		return delBtn;
	}

	/**
	 * @param delBtn the delBtn to set
	 */
	public void setDelBtn(final JButton delBtn) {
		this.delBtn = delBtn;
	}

	/**
	 * @return the refreshBtn
	 */
	public JButton getRefreshBtn() {
		return refreshBtn;
	}

	/**
	 * @param refreshBtn the refreshBtn to set
	 */
	public void setRefreshBtn(final JButton refreshBtn) {
		this.refreshBtn = refreshBtn;
	}

	/**
	 * @return the updLabel
	 */
	public JLabel getUpdLabel() {
		return updLabel;
	}

	/**
	 * @param updLabel the updLabel to set
	 */
	public void setUpdLabel(final JLabel updLabel) {
		this.updLabel = updLabel;
	}

	/**
	 * @return the pathLabel
	 */
	public JLabel getPathLabel() {
		return pathLabel;
	}

	/**
	 * @param pathLabel the pathLabel to set
	 */
	public void setPathLabel(final JLabel pathLabel) {
		this.pathLabel = pathLabel;
	}

	/**
	 * @return the orderByLabel
	 */
	public JLabel getOrderByLabel() {
		return orderByLabel;
	}

	/**
	 * @param orderByLabel the orderByLabel to set
	 */
	public void setOrderByLabel(final JLabel orderByLabel) {
		this.orderByLabel = orderByLabel;
	}

	/**
	 * @return the loadingLbl
	 */
	public JLabel getLoadingLbl() {
		return loadingLbl;
	}

	/**
	 * @param loadingLbl the loadingLbl to set
	 */
	public void setLoadingLbl(final JLabel loadingLbl) {
		this.loadingLbl = loadingLbl;
	}

	/**
	 * @return the pathTxt
	 */
	public JTextField getPathTxt() {
		return pathTxt;
	}

	/**
	 * @param pathTxt the pathTxt to set
	 */
	public void setPathTxt(final JTextField pathTxt) {
		this.pathTxt = pathTxt;
	}

	/**
	 * @return the orderByCb
	 */
	public JComboBox<String> getOrderByCb() {
		return orderByCb;
	}

	/**
	 * @param orderByCb the orderByCb to set
	 */
	public void setOrderByCb(final JComboBox<String> orderByCb) {
		this.orderByCb = orderByCb;
	}

	/**
	 * @return the loadingDialog
	 */
	public JDialog getLoadingDialog() {
		return loadingDialog;
	}

	/**
	 * @param loadingDialog the loadingDialog to set
	 */
	public void setLoadingDialog(final JDialog loadingDialog) {
		this.loadingDialog = loadingDialog;
	}

	/**
	 * @return the syncPathRadioBtn
	 */
	public JRadioButton getSyncPathRadioBtn() {
		return syncPathRadioBtn;
	}

	/**
	 * @param syncPathRadioBtn the syncPathRadioBtn to set
	 */
	public void setSyncPathRadioBtn(final JRadioButton syncPathRadioBtn) {
		this.syncPathRadioBtn = syncPathRadioBtn;
	}

	/**
	 * @return the loadFromDBRadioBtn
	 */
	public JRadioButton getLoadFromDBRadioBtn() {
		return loadFromDBRadioBtn;
	}

	/**
	 * @param loadFromDBRadioBtn the loadFromDBRadioBtn to set
	 */
	public void setLoadFromDBRadioBtn(final JRadioButton loadFromDBRadioBtn) {
		this.loadFromDBRadioBtn = loadFromDBRadioBtn;
	}

	/**
	 * @return the loadBtnGrp
	 */
	public ButtonGroup getLoadBtnGrp() {
		return loadBtnGrp;
	}

	/**
	 * @param loadBtnGrp the loadBtnGrp to set
	 */
	public void setLoadBtnGrp(final ButtonGroup loadBtnGrp) {
		this.loadBtnGrp = loadBtnGrp;
	}

	/**
	 * @return the movieDataProvider
	 */
	public MovieInfoDataProv getMovieDataProv() {
		return movieDataProv;
	}

	/**
	 * @return the icon
	 */
	public ImageIcon getIcon() {
		return icon;
	}

	/**
	 * @return the validator
	 */
	public static UIValidator getValidator() {
		return VALIDATOR;
	}

}
