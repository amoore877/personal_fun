package games.rolePlayingGames.scenario.tracking.shadowrun;

import games.rolePlayingGames.scenario.tracking.AbstractScenarioTrackingTableModel;
import games.rolePlayingGames.scenario.tracking.CharacterStatus;

/**
 * Tracking table model for Shadowrun Scenario Tracking.
 * 
 * @author Andrew
 *
 */
public final class ShadowrunScenarioTrackingTableModel extends AbstractScenarioTrackingTableModel {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 3525845688890053147L;

	/**
	 * Table columns. 0 is acted on the pass, 1 is initiative, 2 is name, 3 is
	 * physical damage, 4 is stun damage, 5 is matrix damage, 6 is notes, 7 is
	 * initiative roll, 8 is status.
	 */
	private static final String[] TRACKING_COLUMN_NAMES = { "Acted", "Init", "Name", "Physical", "Stun", "Matrix",
			"Note", "InitRoll", "Status" };

	/**
	 * Column index of "acted" flag.
	 */
	private static final int ACTED_COL_INDEX = 0;

	/**
	 * Column index of initiative.
	 */
	private static final int INITIATIVE_COL_INDEX = 1;

	/**
	 * Column index of name.
	 */
	private static final int NAME_COL_INDEX = 2;

	/**
	 * Column index of physical damage.
	 */
	protected static final int PHYS_DAM_COL_INDEX = 3;

	/**
	 * Column index of stun damage.
	 */
	protected static final int STUN_DAM_COL_INDEX = 4;

	/**
	 * Column index of matrix damage.
	 */
	protected static final int MAT_DAM_COL_INDEX = 5;

	/**
	 * Column index of notes.
	 */
	private static final int NOTE_COL_INDEX = 6;

	/**
	 * Column index of initiative roll.
	 */
	private static final int INITIATIVE_ROLL_COL_INDEX = 7;

	/**
	 * Column index of status.
	 */
	private static final int STATUS_COL_INDEX = 8;

	/**
	 * Number of columns.
	 */
	private static final int NUM_OF_COLS = 9;

	/**
	 * Constructor.
	 */
	public ShadowrunScenarioTrackingTableModel() {
		super(ACTED_COL_INDEX, INITIATIVE_COL_INDEX, NAME_COL_INDEX, NOTE_COL_INDEX, STATUS_COL_INDEX,
				INITIATIVE_ROLL_COL_INDEX, NUM_OF_COLS);
		setColumnIdentifiers(TRACKING_COLUMN_NAMES);
	}

	/**
	 * Decrease all initiatives by 10.
	 */
	public void nextInitiativePass() {
		for (int currRow = 0; currRow < getRowCount(); currRow++) {
			final int currInitiative;

			final Object currInitiativeObject = getValueAt(currRow, INITIATIVE_COL_INDEX);
			if ((currInitiativeObject == null) || !(currInitiativeObject instanceof Integer)) {
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
		case INITIATIVE_ROLL_COL_INDEX:
			// initiative roll
			return String.class;
		case STATUS_COL_INDEX:
			// Status
			return CharacterStatus.class;
		default:
			return null;
		}
	}

	@Override
	public void addCharacter(final String iName, final String iRoll) {
		final Object[] colData = new Object[NUM_OF_COLS];

		colData[ACTED_COL_INDEX] = Boolean.FALSE;
		colData[INITIATIVE_COL_INDEX] = new Integer(0);
		colData[MAT_DAM_COL_INDEX] = "";
		colData[NAME_COL_INDEX] = iName;
		colData[NOTE_COL_INDEX] = "";
		colData[PHYS_DAM_COL_INDEX] = "";
		colData[STATUS_COL_INDEX] = CharacterStatus.OK;
		colData[INITIATIVE_ROLL_COL_INDEX] = iRoll;
		colData[STUN_DAM_COL_INDEX] = "";

		addRow(colData);
	}
}
