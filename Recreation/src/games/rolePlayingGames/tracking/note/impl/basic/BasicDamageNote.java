package games.rolePlayingGames.tracking.note.impl.basic;

import games.rolePlayingGames.tracking.note.damage.AbstractDamageNote;

import java.awt.GridLayout;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

/**
 * A basic note for damage.
 * 
 * @author Andrew
 */
public final class BasicDamageNote extends AbstractDamageNote {

	/**
	 * Constructor. Default healed amount to 0.
	 * 
	 * @param iFullDesc
	 *            note description.
	 * @param iDamage
	 *            damage amount.
	 */
	public BasicDamageNote(final String iFullDesc, final int iDamage) {
		super(iFullDesc, iDamage);
	}

	/**
	 * Constructor.
	 * 
	 * @param iDesc
	 *            note description.
	 * @param iDamage
	 *            damage amount.
	 * @param iHealed
	 *            healed amount.
	 */
	public BasicDamageNote(final String iDesc, final int iDamage,
			final int iHealed) {
		super(iDesc, iDamage, iHealed);
	}

	@Override
	public final void edit() {
		// input panel
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));

		final JTextField descriptionField = new JTextField(getFullDesc());
		editPanel.add(new JLabel("Description: "));
		editPanel.add(descriptionField);

		final JFormattedTextField damageField = new JFormattedTextField(
				new NumberFormatter(NumberFormat.getIntegerInstance()));
		damageField.setValue(Integer.valueOf(getDamage()));
		editPanel.add(new JLabel("Damage: "));
		editPanel.add(damageField);

		final JFormattedTextField healedField = new JFormattedTextField(
				new NumberFormatter(NumberFormat.getIntegerInstance()));
		healedField.setValue(Integer.valueOf(getHealed()));
		editPanel.add(new JLabel("Healed: "));
		editPanel.add(healedField);

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this note", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		// results
		if (result == JOptionPane.OK_OPTION) {
			// desc
			final String newDesc = descriptionField.getText();

			if (!newDesc.equals(getFullDesc())) {
				setFullDesc(newDesc);
			} else {
				System.out.println("Desc unchanged: [" + getFullDesc() + "]");
			}

			// damage
			try {
				damageField.commitEdit();
			} catch (final ParseException iException) {
				System.err.println(iException.getMessage());
			}
			final int newDamage = Integer.parseInt(damageField.getValue()
					.toString());

			if (newDamage != getDamage()) {
				setDamage(newDamage);
			} else {
				System.out.println("Damage unchanged: [" + getDamage() + "]");
			}

			// healed
			try {
				healedField.commitEdit();
			} catch (final ParseException iException) {
				System.err.println(iException.getMessage());
			}
			final int newHealed = Integer.parseInt(healedField.getValue()
					.toString());

			if (newHealed != getHealed()) {
				setHealed(newHealed);
			} else {
				System.out.println("Healed unchanged: [" + getHealed() + "]");
			}

		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(getDamage());

		if (getHealed() > 0) {
			result.append("(" + getHealed() + ")");
		}

		return result.toString();
	}

	@Override
	public String toFullString() {
		final StringBuilder result = new StringBuilder(getFullDesc());

		result.append("- Damage: " + getDamage());

		if (getHealed() > 0) {
			result.append(", Healed: " + getHealed() + "");
		}

		return result.toString();
	}
}
