package games.rolePlayingGames.tracking.note.impl.example;

import games.rolePlayingGames.tracking.note.AbstractNote;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Basic note for tracking. A simple string.
 * 
 * @author Andrew
 */
public final class BasicNote extends AbstractNote {

	/**
	 * Constructor.
	 * 
	 * @param iFullDesc
	 *            note description.
	 */
	public BasicNote(final String iFullDesc) {
		super(iFullDesc);
	}

	@Override
	public void edit() {
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));

		final JTextField descriptionField = new JTextField(getFullDesc());
		editPanel.add(new JLabel("Description: "));
		editPanel.add(descriptionField);

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this note", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			final String newFullDesc = descriptionField.getText();

			if (!newFullDesc.equals(getFullDesc())) {
				setFullDesc(newFullDesc);
			} else {
				System.out.println("Desc unchanged: [" + getFullDesc() + "]");
			}
		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	@Override
	public String toString() {
		return getFullDesc();
	}

	@Override
	public String toFullString() {
		return getFullDesc();
	}
}
