package recipebook.gui;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public abstract class RecipeBookFrame extends JFrame implements ActionListener, WindowListener {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 6617577850492886040L;

	@Override
	public final void windowClosing(WindowEvent e) {
		confirmExit();
	}

	/**
	 * Confirm exit.
	 */
	protected final void confirmExit() {
		final int reply = JOptionPane.showConfirmDialog(this, "Are you sure you wish to exit?", "Exit",
				JOptionPane.YES_NO_OPTION);

		if (reply == JOptionPane.YES_OPTION) {
			this.dispose();
			System.exit(0);
		}
	}

}
