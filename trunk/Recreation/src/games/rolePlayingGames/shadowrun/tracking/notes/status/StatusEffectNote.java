package games.rolePlayingGames.shadowrun.tracking.notes.status;

import games.rolePlayingGames.tracking.note.AbstractNote;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Status effect note.
 * 
 * @author Andrew
 */
public final class StatusEffectNote extends AbstractNote implements
		IShadowrunStatusEffectNote {

	/**
	 * Brief description.
	 */
	private String myBriefDesc;

	/**
	 * Combat status effect flag.
	 */
	private boolean myCombatStatusEffect;

	/**
	 * Status effect type.
	 */
	private StatusEffectType myStatusEffectType;

	/**
	 * Constructor.
	 * 
	 * @param iFullDesc
	 *            note description.
	 * @param iBriefDesc
	 *            brief (one or two short words) description of effect.
	 * @param iCombatStatusEffect
	 *            true if the status effect is related to combat.
	 */
	public StatusEffectNote(final String iFullDesc, final String iBriefDesc,
			final boolean iCombatStatusEffect,
			final StatusEffectType iQualityType) {
		super(iFullDesc);

		if (iBriefDesc == null) {
			myBriefDesc = "";
		} else {
			myBriefDesc = iBriefDesc;
		}

		myCombatStatusEffect = iCombatStatusEffect;

		if (iQualityType == null) {
			myStatusEffectType = StatusEffectType.NEUTRAL;
		} else {
			myStatusEffectType = iQualityType;
		}
	}

	/**
	 * Set the note's description.
	 * 
	 * @param iBriefDesc
	 *            new description.
	 */
	protected void setBriefDesc(final String iBriefDesc) {
		System.out.println("Brief Desc changing from [" + myBriefDesc
				+ "] to [" + iBriefDesc + "]");

		if (iBriefDesc == null) {
			myBriefDesc = "";
		} else {
			myBriefDesc = iBriefDesc;
		}
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

		final JCheckBox combatStatusEffectBox = new JCheckBox(
				"Combat Status Effect: ", isCombatStatusEffect());
		editPanel.add(combatStatusEffectBox);

		final JPanel statusEffectTypePanel = new JPanel(new GridLayout(1, 0));
		editPanel.add(new JLabel("Status Effect type:"));
		final JRadioButton positiveButton = new JRadioButton("Positive");
		positiveButton.setMnemonic(KeyEvent.VK_P);
		final JRadioButton negativeButton = new JRadioButton("Negative");
		negativeButton.setMnemonic(KeyEvent.VK_N);
		final JRadioButton neutralButton = new JRadioButton("Neutral");
		neutralButton.setMnemonic(KeyEvent.VK_U);
		final ButtonGroup qualityTypeButtonGroup = new ButtonGroup();
		qualityTypeButtonGroup.add(positiveButton);
		qualityTypeButtonGroup.add(negativeButton);
		qualityTypeButtonGroup.add(neutralButton);
		if (StatusEffectType.POSITIVE.equals(getStatusEffectType())) {
			positiveButton.setSelected(true);
		} else if (StatusEffectType.NEGATIVE.equals(getStatusEffectType())) {
			negativeButton.setSelected(true);
		} else {
			neutralButton.setSelected(true);
		}
		statusEffectTypePanel.add(positiveButton);
		statusEffectTypePanel.add(negativeButton);
		statusEffectTypePanel.add(neutralButton);
		editPanel.add(statusEffectTypePanel);

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

			final boolean newCombatQuality = combatStatusEffectBox.isSelected();
			if (newCombatQuality != isCombatStatusEffect()) {
				setCombatQuality(newCombatQuality);
			} else {
				System.out.println("Combat Quality unchanged: ["
						+ isCombatStatusEffect() + "]");
			}

			StatusEffectType newStatusEffectType;
			if (positiveButton.isSelected()) {
				newStatusEffectType = StatusEffectType.POSITIVE;
			} else if (negativeButton.isSelected()) {
				newStatusEffectType = StatusEffectType.NEGATIVE;
			} else {
				newStatusEffectType = StatusEffectType.NEUTRAL;
			}

			if (!newStatusEffectType.equals(getStatusEffectType())) {
				setStatusEffectType(newStatusEffectType);
			} else {
				System.out.println("Status Effect Type unchanged: ["
						+ getStatusEffectType() + "]");
			}

		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	@Override
	public boolean isCombatStatusEffect() {
		return myCombatStatusEffect;
	}

	/**
	 * Set combat status effect flag. true if the status effect is related to
	 * combat.
	 * 
	 * @param iCombatStatusEffect
	 *            true if the status effect is related to combat.
	 */
	private void setCombatQuality(final boolean iCombatStatusEffect) {
		myCombatStatusEffect = iCombatStatusEffect;
	}

	@Override
	public StatusEffectType getStatusEffectType() {
		return myStatusEffectType;
	}

	/**
	 * Set status effect type.
	 * 
	 * @param iStatusEffectType
	 *            status effect type.
	 */
	private void setStatusEffectType(final StatusEffectType iStatusEffectType) {
		myStatusEffectType = iStatusEffectType;
	}

	@Override
	public String getBriefDesc() {
		return myBriefDesc;
	}

	@Override
	public String toString() {
		return getStatusEffectType().getValue() + ":" + getBriefDesc();
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder(getStatusEffectType()
				.getValue() + ":");

		oResult.append(getFullDesc());

		return oResult.toString();
	}
}
