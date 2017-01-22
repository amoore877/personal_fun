package games.rolePlayingGames.scenario.tracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.DefaultTableModel;

/**
 * Abstract scenario tracking table model.
 * 
 * @author Andrew
 */
public abstract class AbstractScenarioTrackingTableModel extends DefaultTableModel {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1577169827016053551L;

	/**
	 * Index of acted column.
	 */
	private final int myActedColIndex;

	/**
	 * Index of initiative column.
	 */
	private final int myInitiativeColIndex;

	/**
	 * Index of name column.
	 */
	private final int myNameColumnIndex;

	/**
	 * Index of note column.
	 */
	private final int myNoteColumnIndex;

	/**
	 * Index of initiative roll column.
	 */
	private final int myInitiativeRollColumnIndex;

	/**
	 * Index of status column.
	 */
	private final int myStatusColumnIndex;

	/**
	 * Number of columns.
	 */
	private final int myNumOfCols;

	/**
	 * Constructor.
	 * 
	 * @param actedColIndex
	 *            index of "acted" column.
	 * @param initiativeColIndex
	 *            index of initiative column.
	 * @param nameColIndex
	 *            index of name column.
	 * @param noteColIndex
	 *            index of note column.
	 * @param statusColIndex
	 *            index of status column.
	 * @param initiativeRollColIndex
	 *            index of initiative roll column.
	 * @param numOfCols
	 *            number of columns.
	 */
	public AbstractScenarioTrackingTableModel(final int actedColIndex, final int initiativeColIndex,
			final int nameColIndex, final int noteColIndex, final int statusColIndex, final int initiativeRollColIndex,
			final int numOfCols) {
		super();

		myActedColIndex = actedColIndex;
		myInitiativeColIndex = initiativeColIndex;
		myNameColumnIndex = nameColIndex;
		myNoteColumnIndex = noteColIndex;
		myStatusColumnIndex = statusColIndex;
		myInitiativeRollColumnIndex = initiativeRollColIndex;
		myNumOfCols = numOfCols;
	}

	/**
	 * @return column index of acted flag.
	 */
	public final int getActedColIndex() {
		return myActedColIndex;
	}

	/**
	 * @return column index of initiative.
	 */
	public final int getInitiativeColIndex() {
		return myInitiativeColIndex;
	}

	/**
	 * @return column index of name.
	 */
	public final int getNameColumnIndex() {
		return myNameColumnIndex;
	}

	/**
	 * @return column index of note.
	 */
	public final int getNoteColumnIndex() {
		return myNoteColumnIndex;
	}

	/**
	 * @return column index of status.
	 */
	public final int getStatusColumnIndex() {
		return myStatusColumnIndex;
	}

	/**
	 * @return number of columns.
	 */
	public final int getNumOfCols() {
		return myNumOfCols;
	}

	/**
	 * @return column index of initiative roll.
	 */
	public int getInitiativeRollColumnIndex() {
		return myInitiativeRollColumnIndex;
	}

	/**
	 * Set the initiative column values all to 0.
	 */
	public final void resetInitiative() {
		for (int currRow = 0; currRow < getRowCount(); currRow++) {
			setValueAt(0, currRow, getInitiativeColIndex());
		}
	}

	@Override
	public abstract Class<?> getColumnClass(final int columnIndex);

	/**
	 * Add a character with the given name and initiative roll.
	 * 
	 * @param iName
	 *            name of character to add.
	 * @param iRoll
	 *            initiative roll.
	 */
	public abstract void addCharacter(final String iName, final String iRoll);

	/**
	 * Reset all acted flags to false.
	 */
	public final void resetActedFlags() {
		// reset all acted flags
		final int rowCount = getRowCount();

		for (int currRow = 0; currRow < rowCount; currRow++) {
			try {
				setValueAt(Boolean.FALSE, currRow, getActedColIndex());
			} catch (final ArrayIndexOutOfBoundsException iException) {
				AbstractScenarioTracking.showError(iException);
			}
		}
	}

	/**
	 * Remove the characters at the given (model) row indices.
	 * 
	 * @param iSelectedRows
	 *            selected rows to remove.
	 */
	public final void removeCharacters(final Integer[] iSelectedRows) {
		// sort highest index to lowest (to avoid issues in removing rows)
		Arrays.sort(iSelectedRows, new Comparator<Integer>() {

			@Override
			public int compare(final Integer o1, final Integer o2) {
				return o2 - o1;
			}
		});

		if (iSelectedRows.length != 0) {

			for (final int currRow : iSelectedRows) {
				try {
					removeRow(currRow);
				} catch (final ArrayIndexOutOfBoundsException iException) {
					AbstractScenarioTracking.showError(iException);
				}
			}
		} else {
			System.out.println("No selected rows");
		}
	}

	/**
	 * Remove all dead characters.
	 */
	public final void removeDeadCharacters() {
		// cleanup dead characters
		final List<Integer> deadCharacterRows = new ArrayList<Integer>();

		final int rowCount = getRowCount();

		for (int currRow = 0; currRow < rowCount; currRow++) {
			try {
				final Object statusObject = getValueAt(currRow, getStatusColumnIndex());

				if ((statusObject != null) && (statusObject instanceof CharacterStatus)) {
					final CharacterStatus status = (CharacterStatus) statusObject;

					if (status.equals(CharacterStatus.DEAD)) {
						deadCharacterRows.add(currRow);
					}
				} else {
					AbstractScenarioTracking.showError(new Exception("Object at [" + currRow + "]["
							+ getStatusColumnIndex() + "] is not a non-null Status object."));
				}
			} catch (final ArrayIndexOutOfBoundsException iException) {
				AbstractScenarioTracking.showError(iException);
			}
		}

		final Integer[] deadCharacterRowArray = new Integer[deadCharacterRows.size()];
		for (int i = 0; i < deadCharacterRows.size(); i++) {
			deadCharacterRowArray[i] = deadCharacterRows.get(i);
		}

		removeCharacters(deadCharacterRowArray);
	}
}
