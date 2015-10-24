package games.rolePlayingGames.tracking.trackable.impl.basic;

import games.rolePlayingGames.tracking.trackable.item.AbstractItem;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Basic item.
 * 
 * @author Andrew
 */
public final class BasicItem extends AbstractItem {

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name.
	 */
	public BasicItem(final String iName) {
		super(iName);
	}

	@Override
	public String toFullString() {
		return getName();
	}

	@Override
	public void edit() {
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));

		final JTextField nameField = new JTextField(getName());
		editPanel.add(new JLabel("Name: "));
		editPanel.add(nameField);

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this note", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			final String newName = nameField.getText();

			if (!newName.equals(getName())) {
				setName(newName);
			} else {
				System.out.println("Name unchanged: [" + getName() + "]");
			}
		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	@Override
	public String toString() {
		return getName();
	}

}
