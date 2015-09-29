package games.rolePlayingGames.shadowrun.tracking.notes.impl;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.notes.damage.spirit.AbstractSpiritDamageNote;

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
 * Shadowrun spirit stun damage note.
 * 
 * In Shadowrun, spirit wounds are treated distinctly from each other. In
 * addition:
 * 
 * 1. Spirit stun can only be healed with time.
 * 
 * @author Andrew
 */
public final class SpiritStunDamageNote extends AbstractSpiritDamageNote {

	/**
	 * Constructor. Cannot magically heal stun.
	 * 
	 * @param iDesc
	 *            very brief note as to cause of damage (ex. "shot", "manaball",
	 *            "stab", etc.).
	 * @param iDamage
	 *            amount of damage.
	 * @param iNaturalOnly
	 *            true if the wound can only be healed naturally, false
	 *            otherwise.
	 */
	public SpiritStunDamageNote(final String iDesc, final int iDamage) {
		super(iDesc, iDamage, true);
		// Cannot magically heal stun.
		setMagicallyHealed(true);
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder(getFullDesc());

		oResult.append(" Stun: " + getDamage());

		oResult.append(" Healed: " + getHealed());

		oResult.append(" Natural Heal Only: " + isNaturalOnly());

		return oResult.toString();
	}

	/**
	 * Heal in a pop-up menu.
	 */
	public void heal() {
		if (getHealed() >= getDamage()) {
			JOptionPane.showMessageDialog(null,
					"Stun is already fully healed!", "Cannot heal further",
					JOptionPane.ERROR_MESSAGE);
		} else {
			final JPanel healPanel = new JPanel(new GridLayout(0, 1));

			// description
			healPanel.add(new JLabel(getFullDesc()));

			// current damage
			healPanel.add(new JLabel("Stun: " + getDamage()));

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
		final JTextField descriptionField = ShadowrunTrackingUtil
				.addStringField(editPanel, "Description", getFullDesc());

		// damage
		final JFormattedTextField damageField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Damage", getDamage());

		// healed
		final JFormattedTextField healedField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Healed", getHealed());

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

		oResult.append("X");

		oResult.append("(" + getHealed() + ")");

		return oResult.toString();
	}
}
