package games.rolePlayingGames.shadowrun.tracking.notes.impl;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.notes.damage.character.AbstractCharacterDamageNote;

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
 * Shadowrun character stun damage note.
 * 
 * In Shadowrun, stun wounds are treated distinctly from each other. In
 * addition:
 * 
 * 1. A wound can only be healed physically once.
 * 
 * 2. Stun cannot be healed magically.
 * 
 * 3. Wounds from drain (and some other sources) cannot be healed except through
 * natural processes.
 * 
 * @author Andrew
 */
public final class CharacterStunDamageNote extends AbstractCharacterDamageNote {

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
	public CharacterStunDamageNote(final String iDesc, final int iDamage,
			final boolean iNaturalOnly) {
		super(iDesc, iDamage, iNaturalOnly);
		// Cannot magically heal stun.
		setMagicallyHealed(true);
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder(getFullDesc());

		oResult.append(" Stun: " + getDamage());

		oResult.append(" Healed: " + getHealed());

		oResult.append(" Natural Heal Only: " + isNaturalOnly());

		oResult.append(" Physically Healed: " + isPhysicallyHealed());

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

			// healing type
			final JPanel healingTypePanel = new JPanel(new GridLayout(1, 0));
			healPanel.add(new JLabel("New Healing type:"));
			final JRadioButton physHealButton = new JRadioButton("Physical ");
			physHealButton.setMnemonic(KeyEvent.VK_P);
			final JRadioButton aidedHealButton = new JRadioButton("Aided");
			aidedHealButton.setMnemonic(KeyEvent.VK_A);
			final JRadioButton naturalHealButton = new JRadioButton("Natural");
			naturalHealButton.setMnemonic(KeyEvent.VK_N);
			final ButtonGroup healTypeButtonGroup = new ButtonGroup();
			healTypeButtonGroup.add(physHealButton);
			healTypeButtonGroup.add(aidedHealButton);
			healTypeButtonGroup.add(naturalHealButton);
			naturalHealButton.setSelected(true);
			if (isNaturalOnly()) {
				physHealButton.setEnabled(false);
				physHealButton.setToolTipText("Natural healing only.");
				aidedHealButton.setEnabled(false);
				aidedHealButton.setToolTipText("Natural healing only.");
			} else if (isPhysicallyHealed()) {
				physHealButton.setEnabled(false);
				physHealButton.setToolTipText("Already physically healed.");
			}
			healingTypePanel.add(physHealButton);
			healingTypePanel.add(aidedHealButton);
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

				// any sort of healing has been done. Cannot physically heal
				// anymore
				setPhysicallyHealed(true);

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

		// physically healed
		final JCheckBox physHealedBox = new JCheckBox("Phys Healed: ",
				isPhysicallyHealed());
		editPanel.add(physHealedBox);

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
			ShadowrunTrackingUtil.examineChangedString(descriptionField,
					"Desc", (s) -> setFullDesc(s), () -> getFullDesc());

			// damage
			ShadowrunTrackingUtil.examineChangedInt(damageField, "Damage",
					(i) -> setDamage(i), () -> getDamage());

			// healed
			ShadowrunTrackingUtil.examineChangedInt(healedField, "Healed",
					(i) -> setHealed(i), () -> getHealed());

			// physically healed
			ShadowrunTrackingUtil.examineChangedBoolean(physHealedBox,
					"Physically Healed", (b) -> setPhysicallyHealed(b),
					() -> isPhysicallyHealed());

			// natural healing only
			ShadowrunTrackingUtil.examineChangedBoolean(naturalHealBox,
					"Natural Heal only", (b) -> setNaturalOnly(b),
					() -> isNaturalOnly());

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

		if (isPhysicallyHealed() || isNaturalOnly()) {
			if (isNaturalOnly()) {
				oResult.append("X");
			} else if (isPhysicallyHealed()) {
				oResult.append("x");
			}
			oResult.append("(" + getHealed() + ")");
		}

		return oResult.toString();
	}
}
