package games.rolePlayingGames.scenario.tracking.dnd;

import games.rolePlayingGames.scenario.tracking.AbstractScenarioTrackingTableCellRenderer;

import java.awt.Color;

/**
 * Table cell renderer for DND. Colors backgrounds based on row data.
 * 
 * @author Andrew
 *
 */
public final class DNDScenarioTrackingTableCellRenderer extends
		AbstractScenarioTrackingTableCellRenderer {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 4091722322100028017L;

	/**
	 * Selected row color.
	 */
	private static final Color SELECTED_COLOR = new Color(130, 220, 255);

	/**
	 * Constructor.
	 * 
	 * @param statusColIndex
	 *            index of status column.
	 */
	public DNDScenarioTrackingTableCellRenderer(final int statusColIndex) {
		super(SELECTED_COLOR, statusColIndex);
	}
}
