package games.rolePlayingGames.shadowrun.tracking.notes.impl;

import games.rolePlayingGames.shadowrun.tracking.notes.damage.device.AbstractDeviceDamageNote;

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
 * Shadowrun device/item damage note.
 * 
 * In Shadowrun, device wounds are not really treated distinctly from each
 * other, but it makes for better notes to do so. In addition:
 * 
 * 1. Physical and Matrix device wounds are healed with hardware tests.
 * 
 * @author Andrew
 */
public final class ItemPhysicalDamageNote extends AbstractDeviceDamageNote {

	/**
	 * Constructor.
	 * 
	 * @param iDesc
	 *            very brief note as to cause of damage (ex. "shot", "manaball",
	 *            "stab", etc.).
	 * @param iDamage
	 *            amount of damage.
	 */
	public ItemPhysicalDamageNote(final String iDesc, final int iDamage) {
		super(iDesc, iDamage);
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder(getFullDesc());

		oResult.append(" Damage: " + getDamage());

		oResult.append(" Healed: " + getHealed());

		return oResult.toString();
	}

	/**
	 * Heal in a pop-up menu.
	 */
	public void heal() {
		if (getHealed() >= getDamage()) {
			JOptionPane.showMessageDialog(null,
					"Damage is already fully healed!", "Cannot heal further",
					JOptionPane.ERROR_MESSAGE);
		} else {
			final JPanel healPanel = new JPanel(new GridLayout(0, 1));

			// description
			healPanel.add(new JLabel(getFullDesc()));

			// current damage
			healPanel.add(new JLabel("Damage: " + getDamage()));

			// current heal
			healPanel.add(new JLabel("Healed: " + getHealed()));

			// healing amount
			final JPanel healAmountPanel = new JPanel(new GridLayout(1, 0));
			final JFormattedTextField healField = new JFormattedTextField(
					new NumberFormatter(NumberFormat.getIntegerInstance()));
			healField.setValue(0);
			healAmountPanel.add(new JLabel("Healed: "));
			healAmountPanel.add(healField);
			healPanel.add(healAmountPanel);

			final int result = JOptionPane.showConfirmDialog(null, healPanel,
					"Add healing", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);

			// results
			if (result == JOptionPane.OK_OPTION) {
				try {
					healField.commitEdit();
				} catch (final ParseException iException) {
					System.err.println(iException.getMessage());
				}
				final int newHealed = Integer.parseInt(healField.getValue()
						.toString());

				heal(newHealed);

			} else if (result == JOptionPane.CANCEL_OPTION) {
				System.out.println("Cancel selected.");
			} else {
				System.err.println("Unknown option selected.");
			}
		}
	}

	@Override
	public void edit() {
		// input panel
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));

		// desc
		final JPanel descPanel = new JPanel(new GridLayout(1, 0));
		final JTextField descriptionField = new JTextField(getFullDesc());
		descPanel.add(new JLabel("Description: "));
		descPanel.add(descriptionField);
		editPanel.add(descPanel);

		// damage
		final JPanel damagePanel = new JPanel(new GridLayout(1, 0));
		final JFormattedTextField damageField = new JFormattedTextField(
				new NumberFormatter(NumberFormat.getIntegerInstance()));
		damageField.setValue(getDamage());
		damagePanel.add(new JLabel("Damage: "));
		damagePanel.add(damageField);
		editPanel.add(damagePanel);

		// healed
		final JPanel healedPanel = new JPanel(new GridLayout(1, 0));
		final JFormattedTextField healedField = new JFormattedTextField(
				new NumberFormatter(NumberFormat.getIntegerInstance()));
		healedField.setValue(getHealed());
		healedPanel.add(new JLabel("Healed: "));
		healedPanel.add(healedField);
		editPanel.add(healedPanel);

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
		final StringBuilder oResult = new StringBuilder(
				String.valueOf(getDamage()));

		oResult.append("(" + getHealed() + ")");

		return oResult.toString();
	}
}
