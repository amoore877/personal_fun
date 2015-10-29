package games.rolePlayingGames.scenario.tracking.shadowrun;

import javax.swing.table.DefaultTableModel;

/**
 * Tracking table model for Shadowrun Scenario Tracking.
 * 
 * @author Andrew
 *
 */
public class ShadowrunScenarioTrackingTableModel extends DefaultTableModel {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 3525845688890053147L;

	/**
	 * Table columns. 0 is acted on the pass, 1 is initiative, 2 is name, 3 is
	 * physical damage, 4 is stun damage, 5 is matrix damage, 6 is notes, 7 is
	 * status.
	 */
	private static final String[] TRACKING_COLUMN_NAMES = { "Acted",
			"Initiative", "Name", "Physical", "Stun", "Matrix", "Note",
			"Status" };

	/**
	 * Column index of "acted" flag.
	 */
	public static final int ACTED_COL_INDEX = 0;

	/**
	 * Column index of initiative.
	 */
	public static final int INITIATIVE_COL_INDEX = 1;

	/**
	 * Column index of name.
	 */
	public static final int NAME_COL_INDEX = 2;

	/**
	 * Column index of physical damage.
	 */
	public static final int PHYS_DAM_COL_INDEX = 3;

	/**
	 * Column index of stun damage.
	 */
	public static final int STUN_DAM_COL_INDEX = 4;

	/**
	 * Column index of matrix damage.
	 */
	public static final int MAT_DAM_COL_INDEX = 5;

	/**
	 * Column index of notes.
	 */
	public static final int NOTE_COL_INDEX = 6;

	/**
	 * Column index of status.
	 */
	public static final int STATUS_COL_INDEX = 7;

	/**
	 * Number of columns.
	 */
	public static final int NUM_OF_COLS = 8;

	/**
	 * Constructor.
	 */
	public ShadowrunScenarioTrackingTableModel() {
		super();
		setColumnIdentifiers(TRACKING_COLUMN_NAMES);
	}

	/**
	 * Set the initiative column values all to 0.
	 */
	public void resetInitiative() {
		for (int currRow = 0; currRow < getRowCount(); currRow++) {
			setValueAt(0, currRow, INITIATIVE_COL_INDEX);
		}
	}

	/**
	 * Decrease all initiatives by 10.
	 */
	public void nextInitiativePass() {
		for (int currRow = 0; currRow < getRowCount(); currRow++) {
			final int currInitiative;

			final Object currInitiativeObject = getValueAt(currRow,
					INITIATIVE_COL_INDEX);
			if ((currInitiativeObject == null)
					|| !(currInitiativeObject instanceof Integer)) {
				currInitiative = 0;
			} else {
				currInitiative = (int) currInitiativeObject;
			}

			setValueAt(currInitiative - 10, currRow, INITIATIVE_COL_INDEX);
		}
	}

	@Override
	public Class<?> getColumnClass(final int columnIndex) {
		switch (columnIndex) {
		case ACTED_COL_INDEX:
			// Acted
			return Boolean.class;
		case INITIATIVE_COL_INDEX:
			// Initiative
			return Integer.class;
		case NAME_COL_INDEX:
			// Name
			return String.class;
		case PHYS_DAM_COL_INDEX:
			// Physical damage
			return String.class;
		case STUN_DAM_COL_INDEX:
			// Stun damage
			return String.class;
		case MAT_DAM_COL_INDEX:
			// Matrix damage
			return String.class;
		case NOTE_COL_INDEX:
			// Note
			return String.class;
		case STATUS_COL_INDEX:
			// Status
			return ShadowrunCharacterStatus.class;
		default:
			return null;
		}
	}

	/**
	 * Add a character with the given name.
	 * 
	 * @param iName
	 *            name of character to add.
	 */
	protected void addCharacter(final String iName) {
		final Object[] colData = new Object[NUM_OF_COLS];

		colData[ACTED_COL_INDEX] = Boolean.FALSE;
		colData[INITIATIVE_COL_INDEX] = new Integer(0);
		colData[MAT_DAM_COL_INDEX] = "";
		colData[NAME_COL_INDEX] = iName;
		colData[NOTE_COL_INDEX] = "";
		colData[PHYS_DAM_COL_INDEX] = "";
		colData[STATUS_COL_INDEX] = ShadowrunCharacterStatus.OK;
		colData[STUN_DAM_COL_INDEX] = "";

		addRow(colData);
	}
}
