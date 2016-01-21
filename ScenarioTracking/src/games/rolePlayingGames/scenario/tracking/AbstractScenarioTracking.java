package games.rolePlayingGames.scenario.tracking;

import games.rolePlayingGames.scenario.tracking.shadowrun.ShadowrunScenarioTracking;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultRowSorter;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;

/**
 * Abstract scenario tracking frame.
 * 
 * @author Andrew
 *
 */
public abstract class AbstractScenarioTracking extends JFrame implements
		ActionListener, WindowListener {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1585991091065566250L;

	/**
	 * Exit string.
	 */
	protected static final String EXIT_STRING = "Exit";
	/**
	 * Save string.
	 */
	protected static final String SAVE_STRING = "Save";
	/**
	 * Add memo string.
	 */
	protected static final String ADD_MEMO_STRING = "Add Memo";
	/**
	 * Roll string.
	 */
	protected static final String ROLL_STRING = "Roll";
	/**
	 * Cleanup string.
	 */
	protected static final String CLEANUP_STRING = "Cleanup";
	/**
	 * Remove string.
	 */
	protected static final String REMOVE_STRING = "Remove";
	/**
	 * Add string.
	 */
	protected static final String ADD_STRING = "Add";
	/**
	 * Resort string.
	 */
	protected static final String RESORT_STRING = "Resort";
	/**
	 * Log delimiter.
	 */
	protected static final String LOG_DELIMITER = "#";
	/**
	 * Table delimiter.
	 */
	protected static final String TABLE_DELIMITER = ",";
	/**
	 * Story note log string.
	 */
	protected static final String STORY_NOTE_STRING = "STORY";
	/**
	 * Combat not log string.
	 */
	protected static final String COMBAT_NOTE_STRING = "COMBAT";
	/**
	 * CSV extension.
	 */
	protected static final String CSV_EXTENSION_STRING = ".csv";
	/**
	 * Text extension.
	 */
	protected static final String TXT_EXTENSION_STRING = ".txt";

	/**
	 * Clear selection string.
	 */
	protected static final String CLEAR_SELECTION_STRING = "Clear Selection";

	/**
	 * Reset combat string.
	 */
	protected static final String RESET_COMBAT_STRING = "Reset Combat";

	/**
	 * Scenario name.
	 */
	private final String myScenarioName;

	/**
	 * Field to input one-line memos.
	 */
	private final JTextField myMemoField;

	/**
	 * Check box for whether memo is for combat or not.
	 */
	private final JCheckBox myCombatMemoCheckbox;

	/**
	 * Text area for all memos.
	 */
	private final JTextArea myMemoTextArea;

	/**
	 * Table for tracking data.
	 */
	private final JTable myTrackingTable;

	/**
	 * Constructor.
	 * 
	 * @param iScenarioName
	 *            name of scenario.
	 */
	public AbstractScenarioTracking(final String iScenarioName) {
		super();

		myTrackingTable = new JTable();

		myScenarioName = iScenarioName;

		myMemoField = new JTextField();

		myCombatMemoCheckbox = new JCheckBox("Combat Memo?");

		myMemoTextArea = new JTextArea();
		myMemoTextArea.setEditable(true);
	}

	/**
	 * Sort the table. Sorts by initiative, descending.
	 */
	protected final void resort() {
		final DefaultRowSorter<?, ?> sorter = (DefaultRowSorter<?, ?>) getTrackingTable()
				.getRowSorter();
		final List<SortKey> trackingTableSortKeyList = new ArrayList<SortKey>();
		trackingTableSortKeyList.add(new RowSorter.SortKey(getTableModel()
				.getInitiativeColIndex(), SortOrder.DESCENDING));
		sorter.setSortKeys(trackingTableSortKeyList);
		sorter.sort();

		// repaint
		getTrackingTable().repaint();
	}

	/**
	 * Removal of characters.
	 */
	protected final void removeCharacters() {
		final int[] selectedTableRowsInt = getTrackingTable().getSelectedRows();

		final int[] selectedModelRowsInt = new int[selectedTableRowsInt.length];

		// convert table rows to model rows
		int rowToConvert = 0;
		for (final int currRow : selectedTableRowsInt) {
			selectedModelRowsInt[rowToConvert++] = getTrackingTable()
					.convertRowIndexToModel(currRow);
		}

		final StringBuilder message = new StringBuilder(
				"Are you sure you wish to remove the following characters:");

		for (final int currRow : selectedModelRowsInt) {
			try {
				message.append("\n"
						+ getTableModel().getValueAt(currRow,
								getTableModel().getNameColumnIndex())
								.toString());

			} catch (final ArrayIndexOutOfBoundsException iException) {
				ShadowrunScenarioTracking.showError(iException);
			}
		}

		final int result = JOptionPane.showConfirmDialog(null,
				message.toString(), "Removal Confirmation",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			final Integer[] selectedRowsInteger = new Integer[selectedModelRowsInt.length];
			int selectedRowIndex = 0;
			for (final int currRow : selectedModelRowsInt) {
				selectedRowsInteger[selectedRowIndex++] = currRow;
			}

			getTableModel().removeCharacters(selectedRowsInteger);
		}
	}

	/**
	 * Roll dice.
	 */
	protected abstract void rollDice();

	/**
	 * Add character(s) to table.
	 */
	protected final void addCharacters() {
		// add character(s)
		final String name = JOptionPane.showInputDialog(this,
				"Name for new character(s)?", "Add Character(s)",
				JOptionPane.QUESTION_MESSAGE);
		if ((name != null) && !(name.isEmpty())) {
			final String numOfCharactersString = JOptionPane.showInputDialog(
					this, "Number of characters to add (1-10)?", 1);

			if ((numOfCharactersString != null)
					&& !(numOfCharactersString.isEmpty())) {
				try {
					final int numOfCharacters = Integer
							.parseInt(numOfCharactersString);

					if (numOfCharacters < 1) {
						showError(new Exception(
								"Bad input for number of new characters: ["
										+ numOfCharacters + "]. Too small."));
					} else if (numOfCharacters > 10) {
						showError(new Exception(
								"Bad input for number of new characters: ["
										+ numOfCharacters + "]. Too big."));
					} else {
						if (numOfCharacters > 1) {
							for (int currCharacter = 1; currCharacter <= numOfCharacters; currCharacter++) {
								getTableModel().addCharacter(
										name + " " + currCharacter);
							}
						} else {
							getTableModel().addCharacter(name);
						}
					}
				} catch (final NumberFormatException iException) {
					showError(iException);
				}
			} else {
				showError(new Exception(
						"Bad input for number of new characters"));
			}
		} else {
			showError(new Exception("Bad name input for new character"));
		}
	}

	/**
	 * Load log file.
	 * 
	 * @param logFile
	 *            log file to load.
	 */
	protected final void loadLogFile(final File logFile) {
		try {
			if (logFile.exists()) {
				// log file exists
				// offer to load or overwrite
				final int result = JOptionPane.showConfirmDialog(null,
						"Log file [" + logFile.getName() + "] found. Load?",
						"Load existing log file?",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {
					// load
					final BufferedReader logFileReader = new BufferedReader(
							new FileReader(logFile));
					String line = logFileReader.readLine();
					while (line != null) {
						getMemoTextArea().append(line + "\n");
						line = logFileReader.readLine();
					}

					logFileReader.close();
				} else {
					// delete and recreate
					logFile.delete();
					logFile.createNewFile();
				}
			}
		} catch (final IOException iException) {
			showError(iException);
		}
	}

	/**
	 * Load table file.
	 * 
	 * @param tableFile
	 *            table file to load.
	 */
	protected abstract void loadTableFile(final File tableFile);

	/**
	 * @return the table.
	 */
	protected final JTable getTrackingTable() {
		return myTrackingTable;
	}

	/**
	 * @return the table model.
	 */
	protected abstract AbstractScenarioTrackingTableModel getTableModel();

	/**
	 * @return memo text area.
	 */
	protected final JTextArea getMemoTextArea() {
		return myMemoTextArea;
	}

	/**
	 * @return combat memo check box.
	 */
	protected final JCheckBox getCombatMemoCheckBox() {
		return myCombatMemoCheckbox;
	}

	/**
	 * Add a memo.
	 */
	protected final void addMemo() {
		// add memo
		final String memoText = getMemoField().getText();

		final boolean isCombatMemo = myCombatMemoCheckbox.isSelected();

		appendMemo(memoText, isCombatMemo);

		// clear memo field
		getMemoField().setText("");
	}

	/**
	 * Append a memo.
	 * 
	 * @param iMemoText
	 *            text.
	 * @param iIsCombatMemo
	 *            whether or not the memo is related to combat.
	 */
	protected abstract void appendMemo(final String iMemoText,
			final boolean iIsCombatMemo);

	/**
	 * @return memo field.
	 */
	protected final JTextField getMemoField() {
		return myMemoField;
	}

	/**
	 * @return scenario name.
	 */
	protected final String getScenarioName() {
		return myScenarioName;
	}

	/**
	 * Save function.
	 */
	protected final void save() {
		// save
		// save log file
		final boolean logFileSaved = saveLogFile();

		// save table file
		final boolean tableFileSaved = saveTableFile();

		if (!logFileSaved || !tableFileSaved) {
			// something wasn't saved
			JOptionPane.showMessageDialog(null,
					"Saving unsuccessful. Log file saved: [" + logFileSaved
							+ "]. Table file saved: [" + tableFileSaved + "].",
					"Error saving", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Saved!", "Save successful",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Save log file.
	 * 
	 * @return true if successful.
	 */
	protected final boolean saveLogFile() {
		boolean logFileSaved = false;
		try {
			final File logFile = new File(getScenarioName()
					+ CSV_EXTENSION_STRING);
			// remake file
			logFile.delete();
			logFile.createNewFile();

			final BufferedWriter logFileWriter = new BufferedWriter(
					new FileWriter(logFile));

			logFileWriter.write(getMemoTextArea().getText());

			logFileWriter.flush();
			logFileWriter.close();

			logFileSaved = true;
		} catch (final IOException iException) {
			showError(iException);
		}
		return logFileSaved;
	}

	/**
	 * Save table file.
	 * 
	 * @return true if successful.
	 */
	protected final boolean saveTableFile() {
		boolean oTableFileSaved = false;
		try {
			final File tableFile = new File(getScenarioName()
					+ TXT_EXTENSION_STRING);
			// remake file
			tableFile.delete();
			tableFile.createNewFile();

			final BufferedWriter tableFileWriter = new BufferedWriter(
					new FileWriter(tableFile));

			// write table
			for (int currRow = 0; currRow < getTableModel().getRowCount(); currRow++) {
				final StringBuilder lineToWrite = new StringBuilder();
				for (int currCol = 0; currCol < getTableModel().getNumOfCols(); currCol++) {
					lineToWrite.append(getTableModel().getValueAt(currRow,
							currCol).toString());
					lineToWrite.append(TABLE_DELIMITER);
				}
				lineToWrite.append("\n");
				tableFileWriter.write(lineToWrite.toString());
			}

			tableFileWriter.flush();
			tableFileWriter.close();

			oTableFileSaved = true;
		} catch (final IOException iException) {
			showError(iException);
		} catch (final ArrayIndexOutOfBoundsException iException) {
			showError(iException);
		}
		return oTableFileSaved;
	}

	/**
	 * Show error message.
	 * 
	 * @param iException
	 *            exception that appeared.
	 */
	public static void showError(final Exception iException) {
		// show error message, any exception that is caught
		JOptionPane.showMessageDialog(null, iException.getMessage(), "Error!",
				JOptionPane.ERROR_MESSAGE);
		iException.printStackTrace();
	}

	/**
	 * Confirm exit.
	 */
	protected final void confirmExit() {
		final int reply = JOptionPane.showConfirmDialog(this,
				"Are you sure you wish to exit?", EXIT_STRING,
				JOptionPane.YES_NO_OPTION);

		if (reply == JOptionPane.YES_OPTION) {
			this.dispose();
			System.exit(0);
		}
	}

	@Override
	public final void windowOpened(final WindowEvent e) {
		// unimplemented
	}

	@Override
	public final void windowClosing(final WindowEvent e) {
		confirmExit();
	}

	/**
	 * Override dispose. Close buffers.
	 */
	@Override
	public final void dispose() {
		// window is being closed; save information
		save();
		super.dispose();
	}

	@Override
	public final void windowClosed(final WindowEvent e) {
		// unimplemented
	}

	@Override
	public final void windowIconified(final WindowEvent e) {
		// unimplemented
	}

	@Override
	public final void windowDeiconified(final WindowEvent e) {
		// unimplemented
	}

	@Override
	public final void windowActivated(final WindowEvent e) {
		// unimplemented
	}

	@Override
	public final void windowDeactivated(final WindowEvent e) {
		// unimplemented
	}
}
