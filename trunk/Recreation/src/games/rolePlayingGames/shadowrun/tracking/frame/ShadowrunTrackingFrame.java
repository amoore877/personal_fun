package games.rolePlayingGames.shadowrun.tracking.frame;

import games.rolePlayingGames.shadowrun.tracking.table.ShadowrunTrackingTable;
import games.rolePlayingGames.tracking.frame.AbstractTrackingFrame;

import java.awt.HeadlessException;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

/**
 * Shadowrun tracking frame.
 * 
 * @author Andrew
 */
public final class ShadowrunTrackingFrame extends
		AbstractTrackingFrame<ShadowrunTrackingTable> {

	public ShadowrunTrackingFrame(final String iTitle,
			final ShadowrunTrackingTable iTable, final JPanel iControlPanel)
			throws HeadlessException {
		super(iTitle, iTable, iControlPanel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void windowClosed(final WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
