package games.rolePlayingGames.shadowrun.tracking.notes.impl;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.notes.status.IShadowrunStatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.notes.status.StatusEffectType;
import games.rolePlayingGames.tracking.note.AbstractNote;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	 * @param iStatusEffectType
	 *            type of status effect.
	 */
	public StatusEffectNote(final String iFullDesc, final String iBriefDesc,
			final boolean iCombatStatusEffect,
			final StatusEffectType iStatusEffectType) {
		super(iFullDesc);

		if (iBriefDesc == null) {
			myBriefDesc = "";
		} else {
			myBriefDesc = iBriefDesc;
		}

		myCombatStatusEffect = iCombatStatusEffect;

		if (iStatusEffectType == null) {
			myStatusEffectType = StatusEffectType.NEUTRAL;
		} else {
			myStatusEffectType = iStatusEffectType;
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

		final JTextField fullDescriptionField = ShadowrunTrackingUtil
				.addStringField(editPanel, "Full Desc", getFullDesc());

		final JTextField briefDescriptionField = ShadowrunTrackingUtil
				.addStringField(editPanel, "Brief Desc", getBriefDesc());

		final JCheckBox combatStatusEffectBox = new JCheckBox(
				"Combat Status Effect: ", isCombatStatusEffect());
		editPanel.add(combatStatusEffectBox);

		final JComboBox<StatusEffectType> statusEffectTypeBox = ShadowrunTrackingUtil
				.addEnumComboBox(editPanel, "Type", StatusEffectType.values(),
						getStatusEffectType());

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this status effect", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			ShadowrunTrackingUtil.examineChangedString(fullDescriptionField,
					"Full Desc", (s) -> setFullDesc(s), () -> getFullDesc());

			ShadowrunTrackingUtil.examineChangedString(briefDescriptionField,
					"Brief Desc", (s) -> setBriefDesc(s), () -> getBriefDesc());

			ShadowrunTrackingUtil.examineChangedBoolean(combatStatusEffectBox,
					"Combat Quality", (b) -> setCombatQuality(b),
					() -> isCombatStatusEffect());

			StatusEffectType newStatusEffectType = (StatusEffectType) statusEffectTypeBox
					.getSelectedItem();

			if (newStatusEffectType == null) {
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
