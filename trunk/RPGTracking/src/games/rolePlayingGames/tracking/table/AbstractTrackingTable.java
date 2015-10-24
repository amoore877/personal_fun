package games.rolePlayingGames.tracking.table;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Tracking table extension of JTable.
 * 
 * @author Andrew
 */
public abstract class AbstractTrackingTable extends JTable {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -1275494875898672099L;

	/**
	 * Constructor.
	 * 
	 * @param dm
	 *            table model.
	 * @param cm
	 *            column model.
	 * @param sm
	 *            selection model.
	 */
	public AbstractTrackingTable(final TableModel dm,
			final TableColumnModel cm, final ListSelectionModel sm) {
		super(dm, cm, sm);
	}
}
