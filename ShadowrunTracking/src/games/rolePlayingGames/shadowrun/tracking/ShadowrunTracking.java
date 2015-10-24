package games.rolePlayingGames.shadowrun.tracking;

import games.rolePlayingGames.shadowrun.tracking.control.ShadowrunTrackingControl;
import games.rolePlayingGames.shadowrun.tracking.frame.ShadowrunTrackingFrame;
import games.rolePlayingGames.shadowrun.tracking.table.ShadowrunTrackingTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * Shadowrun tracking.
 * 
 * @author Andrew
 *
 */
public final class ShadowrunTracking implements ActionListener {

	/**
	 * Table.
	 */
	private final ShadowrunTrackingTable myTable;

	/**
	 * Tracking control.
	 */
	private final ShadowrunTrackingControl myTrackingControl;

	/**
	 * Tracking frame.
	 */
	private final ShadowrunTrackingFrame myTrackingFrame;

	/**
	 * Control panel components.
	 */

	/**
	 * End control panel components.
	 */

	/**
	 * Constructor.
	 */
	public ShadowrunTracking() {
		// construct table
		// TODO parameters
		myTable = new ShadowrunTrackingTable(null, null, null);
		// TODO selection listener

		// construct tracking control
		myTrackingControl = new ShadowrunTrackingControl();

		// construct control panel, with this as the listener
		// TODO layout
		final JPanel controlPanel = new JPanel(null);
		// TODO add components to control panel, with listening

		// construct tracking frame
		myTrackingFrame = new ShadowrunTrackingFrame("Shadowrun Tracking",
				myTable, controlPanel);
	}

	/**
	 * Run the program.
	 */
	private void start() {
		// pack and view frame
		myTrackingFrame.pack();
		myTrackingFrame.setVisible(true);
	}

	/**
	 * Main.
	 * 
	 * @param args
	 *            arguments.
	 */
	public static void main(final String[] args) {
		new ShadowrunTracking().start();
	}

	@Override
	public void actionPerformed(final ActionEvent iEvent) {
		// TODO Auto-generated method stub

	}
}