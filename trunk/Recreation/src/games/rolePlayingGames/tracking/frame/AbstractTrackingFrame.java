package games.rolePlayingGames.tracking.frame;

import games.rolePlayingGames.tracking.table.AbstractTrackingTable;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	 * Control panel height.
	 */
	public static final int CONTROL_PANEL_HEIGHT = 190;

	/**
	 * Control panel width.
	 */
	public static final int CONTROL_PANEL_WIDTH = 775;

	/**
	 * Table panel height.
	 */
	private static final int TABLE_PANEL_HEIGHT = 400;

	/**
	 * Table panel width.
	 */
	private static final int TABLE_PANEL_WIDTH = 775;

	/**
	 * Height of frame.
	 */
	private static final int FRAME_HEIGHT = 650;

	/**
	 * Width of frame.
	 */
	private static final int FRAME_WIDTH = 800;

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

		addWindowListener(this);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setMaximumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

		setResizable(false);

		getContentPane().setMaximumSize(
				new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		getContentPane().setMinimumSize(
				new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		getContentPane().setPreferredSize(
				new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		getContentPane().setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		getContentPane().setLayout(null);

		final JPanel tablePanel = new JPanel();
		tablePanel.setPreferredSize(new Dimension(TABLE_PANEL_WIDTH,
				TABLE_PANEL_HEIGHT));
		tablePanel
				.setSize(new Dimension(TABLE_PANEL_WIDTH, TABLE_PANEL_HEIGHT));
		tablePanel.setMaximumSize(new Dimension(TABLE_PANEL_WIDTH,
				TABLE_PANEL_HEIGHT));
		tablePanel.setMinimumSize(new Dimension(TABLE_PANEL_WIDTH,
				TABLE_PANEL_HEIGHT));
		tablePanel.setBounds(10, 10, TABLE_PANEL_WIDTH, TABLE_PANEL_HEIGHT);
		getContentPane().add(tablePanel);

		final JPanel controlPanel = new JPanel();
		controlPanel.setSize(new Dimension(CONTROL_PANEL_WIDTH,
				CONTROL_PANEL_HEIGHT));
		controlPanel.setPreferredSize(new Dimension(CONTROL_PANEL_WIDTH,
				CONTROL_PANEL_HEIGHT));
		controlPanel.setMinimumSize(new Dimension(CONTROL_PANEL_WIDTH,
				CONTROL_PANEL_HEIGHT));
		controlPanel.setMaximumSize(new Dimension(CONTROL_PANEL_WIDTH,
				CONTROL_PANEL_HEIGHT));
		controlPanel.setBounds(10, 420, CONTROL_PANEL_WIDTH,
				CONTROL_PANEL_HEIGHT);
		getContentPane().add(controlPanel);

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

	@Override
	public void windowOpened(final WindowEvent e) {
		// no implementation
	}

	/**
	 * User selected to exit the window. Confirm exit.
	 */
	@Override
	public void windowClosing(final WindowEvent e) {
		final int reply = JOptionPane.showConfirmDialog(this,
				"Are you sure you wish to exit?", "Exit",
				JOptionPane.YES_NO_OPTION);

		if (reply == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

	/**
	 * Window is being disposed. Save all data.
	 */
	@Override
	public abstract void windowClosed(WindowEvent e);

	@Override
	public final void windowIconified(final WindowEvent e) {
		// no implementation
	}

	@Override
	public final void windowDeiconified(final WindowEvent e) {
		// no implementation
	}

	@Override
	public final void windowActivated(final WindowEvent e) {
		// no implementation
	}

	@Override
	public final void windowDeactivated(final WindowEvent e) {
		// no implementation
	}
}
