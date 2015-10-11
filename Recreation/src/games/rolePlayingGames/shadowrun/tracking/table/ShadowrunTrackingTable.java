package games.rolePlayingGames.shadowrun.tracking.table;

import games.rolePlayingGames.tracking.table.AbstractTrackingTable;

import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Shadowrun tracking table.
 * 
 * @author Andrew
 */
public final class ShadowrunTrackingTable extends AbstractTrackingTable {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -5031564804093326219L;

	public ShadowrunTrackingTable(final TableModel dm,
			final TableColumnModel cm, final ListSelectionModel sm) {
		super(dm, cm, sm);
	}

}
