package games.rolePlayingGames.scenario.tracking.shadowrun;

import games.rolePlayingGames.scenario.tracking.AbstractScenarioTrackingTableCellRenderer;

import java.awt.Color;

/**
 * Table cell renderer for Shadowrun. Colors backgrounds based on row data.
 * 
 * @author Andrew
 *
 */
public final class ShadowrunScenarioTrackingTableCellRenderer extends
		AbstractScenarioTrackingTableCellRenderer {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 5746258843575162555L;

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
	public ShadowrunScenarioTrackingTableCellRenderer(final int statusColIndex) {
		super(SELECTED_COLOR, statusColIndex);
	}
}
