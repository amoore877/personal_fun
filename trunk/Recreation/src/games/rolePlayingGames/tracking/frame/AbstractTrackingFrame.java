package games.rolePlayingGames.tracking.frame;

import games.rolePlayingGames.tracking.table.AbstractTrackingTable;

import java.awt.HeadlessException;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.TableModel;

/**
 * Abstract tracking frame. Holds table and all primary controls.
 * 
 * @param <T>
 *            type of tracking table to hold.
 * 
 * @author Andrew
 */
public abstract class AbstractTrackingFrame<T extends AbstractTrackingTable>
		extends JFrame implements WindowListener {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -2421460454174517274L;

	/**
	 * Tracking table.
	 */
	private final T myTable;

	/**
	 * Constructor.
	 * 
	 * @param iTitle
	 *            title of frame.
	 * @param iTable
	 *            table to use.
	 * @param iControlPanel
	 *            control panel to add. Any buttons, comboxes, etc.
	 * @throws HeadlessException
	 */
	public AbstractTrackingFrame(final String iTitle, final T iTable,
			final JPanel iControlPanel) throws HeadlessException {
		super(iTitle);
		myTable = iTable;
		// TODO sizing and options
		// TODO add table
		// TODO add control panel
	}

	/**
	 * @return the table model in use.
	 */
	public final TableModel getTableModel() {
		return myTable.getModel();
	}
}
