package games.rolePlayingGames.scenario.tracking.dnd;

import games.rolePlayingGames.scenario.tracking.AbstractScenarioTrackingTableModel;
import games.rolePlayingGames.scenario.tracking.CharacterStatus;

/**
 * Tracking table model for DND Scenario Tracking.
 * 
 * @author Andrew
 *
 */
public final class DNDScenarioTrackingTableModel extends
		AbstractScenarioTrackingTableModel {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 4462173721517626168L;

	/**
	 * Table columns. 0 is acted on the pass, 1 is initiative, 2 is name, 3 is
	 * physical damage, 4 is subdual damage, 5 is notes, 6 is status.
	 */
	private static final String[] TRACKING_COLUMN_NAMES = { "Acted",
			"Initiative", "Name", "Physical", "Subdual", "Note", "Status" };

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
	 * Column index of subdual damage.
	 */
	protected static final int SUBDUAL_DAM_COL_INDEX = 4;

	/**
	 * Column index of notes.
	 */
	private static final int NOTE_COL_INDEX = 5;

	/**
	 * Column index of status.
	 */
	private static final int STATUS_COL_INDEX = 6;

	/**
	 * Number of columns.
	 */
	private static final int NUM_OF_COLS = 7;

	/**
	 * Constructor.
	 */
	public DNDScenarioTrackingTableModel() {
		super(ACTED_COL_INDEX, INITIATIVE_COL_INDEX, NAME_COL_INDEX,
				NOTE_COL_INDEX, STATUS_COL_INDEX, NUM_OF_COLS);
		setColumnIdentifiers(TRACKING_COLUMN_NAMES);
	}

	/**
	 * Sets all initiatives to 0.
	 */
	public void nextRound() {
		for (int currRow = 0; currRow < getRowCount(); currRow++) {
			setValueAt(0, currRow, INITIATIVE_COL_INDEX);
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
			return Integer.class;
		case SUBDUAL_DAM_COL_INDEX:
			// Subdual damage
			return Integer.class;
		case NOTE_COL_INDEX:
			// Note
			return String.class;
		case STATUS_COL_INDEX:
			// Status
			return CharacterStatus.class;
		default:
			return null;
		}
	}

	@Override
	public void addCharacter(final String iName) {
		final Object[] colData = new Object[NUM_OF_COLS];

		colData[ACTED_COL_INDEX] = Boolean.FALSE;
		colData[INITIATIVE_COL_INDEX] = new Integer(0);
		colData[NAME_COL_INDEX] = iName;
		colData[NOTE_COL_INDEX] = "";
		colData[PHYS_DAM_COL_INDEX] = new Integer(0);
		colData[STATUS_COL_INDEX] = CharacterStatus.OK;
		colData[SUBDUAL_DAM_COL_INDEX] = new Integer(0);

		addRow(colData);
	}
}
