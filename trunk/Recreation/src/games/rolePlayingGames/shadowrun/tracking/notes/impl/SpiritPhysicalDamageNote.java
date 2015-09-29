package games.rolePlayingGames.shadowrun.tracking.notes.impl;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.notes.damage.spirit.AbstractSpiritDamageNote;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

/**
 * Shadowrun spirit physical damage note.
 * 
 * In Shadowrun, spirit wounds are treated distinctly from each other. In
 * addition:
 * 
 * 1. A wound can only be healed magically, once.
 * 
 * 2. Wounds from drain (and some other sources) cannot be healed except through
 * natural processes.
 * 
 * @author Andrew
 */
public final class SpiritPhysicalDamageNote extends AbstractSpiritDamageNote {

	/**
	 * Constructor.
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
	public SpiritPhysicalDamageNote(final String iDesc, final int iDamage,
			final boolean iNaturalOnly) {
		super(iDesc, iDamage, iNaturalOnly);
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder(getFullDesc());

		oResult.append(" Damage: " + getDamage());

		oResult.append(" Healed: " + getHealed());

		oResult.append(" Natural Heal Only: " + isNaturalOnly());

		oResult.append(" Magically Healed: " + isMagicallyHealed());

		return oResult.toString();
	}

	/**
	 * Heal in a pop-up menu.
	 */
	public final void heal() {
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

			// healing type
			final JPanel healingTypePanel = new JPanel(new GridLayout(1, 0));
			healPanel.add(new JLabel("New Healing type:"));
			final JRadioButton magHealButton = new JRadioButton("Magical");
			magHealButton.setMnemonic(KeyEvent.VK_M);
			final JRadioButton naturalHealButton = new JRadioButton("Natural");
			naturalHealButton.setMnemonic(KeyEvent.VK_N);
			final ButtonGroup healTypeButtonGroup = new ButtonGroup();
			healTypeButtonGroup.add(magHealButton);
			healTypeButtonGroup.add(naturalHealButton);
			naturalHealButton.setSelected(true);
			if (isNaturalOnly()) {
				magHealButton.setEnabled(false);
				magHealButton.setToolTipText("Natural healing only.");
			} else if (isMagicallyHealed()) {
				magHealButton.setEnabled(false);
				magHealButton.setToolTipText("Already magically healed.");
			}
			healingTypePanel.add(magHealButton);
			healingTypePanel.add(naturalHealButton);
			healPanel.add(healingTypePanel);

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

				if (magHealButton.isSelected()) {
					// magical healing has been done. Cannot magically heal
					// more.
					setMagicallyHealed(true);
				}

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

		// magically healed
		final JCheckBox magHealedBox = new JCheckBox("Magic Healed: ",
				isMagicallyHealed());
		editPanel.add(magHealedBox);

		// natural healing only
		final JCheckBox naturalHealBox = new JCheckBox("Natural Heal Only: ",
				isNaturalOnly());
		editPanel.add(naturalHealBox);

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

			// magically healed
			final boolean newMagHealed = magHealedBox.isSelected();
			if (newMagHealed != isMagicallyHealed()) {
				setMagicallyHealed(newMagHealed);
			} else {
				System.out.println("Magically Healed unchanged: ["
						+ isMagicallyHealed() + "]");
			}

			// natural healing only
			final boolean newNaturalHealOnly = naturalHealBox.isSelected();
			if (newNaturalHealOnly != isNaturalOnly()) {
				setNaturalOnly(newNaturalHealOnly);
			} else {
				System.out.println("Natural Heal only unchanged: ["
						+ isNaturalOnly() + "]");
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

		if (isMagicallyHealed() || isNaturalOnly()) {
			oResult.append("X");
			oResult.append("(" + getHealed() + ")");
		}

		return oResult.toString();
	}
}
