package games.rolePlayingGames.tracking.note.impl.basic;

import games.rolePlayingGames.tracking.note.status.trait.AbstractTraitNote;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Basic trait note.
 * 
 * @author Andrew
 */
public class BasicTraitNote extends AbstractTraitNote {

	/**
	 * Constructor.
	 * 
	 * @param iFullDesc
	 *            note description.
	 * @param iBriefDesc
	 *            brief (one or two short words) description of effect.
	 */
	public BasicTraitNote(final String iFullDesc, final String iBriefDesc) {
		super(iFullDesc, iBriefDesc);
	}

	@Override
	public String toFullString() {
		return getFullDesc();
	}

	@Override
	public String toString() {
		return getBriefDesc();
	}

	@Override
	public void edit() {
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));

		final JTextField fullDescriptionField = new JTextField(getFullDesc());
		editPanel.add(new JLabel("Full Description: "));
		editPanel.add(fullDescriptionField);

		final JTextField briefDescriptionField = new JTextField(getBriefDesc());
		editPanel.add(new JLabel("Brief Description: "));
		editPanel.add(briefDescriptionField);

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this note", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			final String newFullDesc = fullDescriptionField.getText();

			if (!newFullDesc.equals(getFullDesc())) {
				setFullDesc(newFullDesc);
			} else {
				System.out.println("Full Desc unchanged: [" + getFullDesc()
						+ "]");
			}

			final String newBriefDesc = briefDescriptionField.getText();

			if (!newBriefDesc.equals(getBriefDesc())) {
				setBriefDesc(newBriefDesc);
			} else {
				System.out.println("Brief Desc unchanged: [" + getBriefDesc()
						+ "]");
			}
		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}
}
