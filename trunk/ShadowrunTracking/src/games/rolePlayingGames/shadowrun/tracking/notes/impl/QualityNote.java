package games.rolePlayingGames.shadowrun.tracking.notes.impl;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.notes.quality.IShadowrunQualityNote;
import games.rolePlayingGames.shadowrun.tracking.notes.quality.QualityType;
import games.rolePlayingGames.tracking.note.AbstractNote;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Quality note.
 * 
 * @author Andrew
 */
public final class QualityNote extends AbstractNote implements
		IShadowrunQualityNote {

	/**
	 * Brief description.
	 */
	private String myBriefDesc;

	/**
	 * Combat quality flag.
	 */
	private boolean myCombatQuality;

	/**
	 * Quality type.
	 */
	private QualityType myQualityType;

	/**
	 * Constructor.
	 * 
	 * @param iFullDesc
	 *            note description.
	 * @param iBriefDesc
	 *            brief (one or two short words) description of effect.
	 * @param iCombatQuality
	 *            true if the quality is related to combat.
	 */
	public QualityNote(final String iFullDesc, final String iBriefDesc,
			final boolean iCombatQuality, final QualityType iQualityType) {
		super(iFullDesc);

		if (iBriefDesc == null) {
			myBriefDesc = "";
		} else {
			myBriefDesc = iBriefDesc;
		}

		myCombatQuality = iCombatQuality;

		if (iQualityType == null) {
			myQualityType = QualityType.NEUTRAL;
		} else {
			myQualityType = iQualityType;
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

		final JCheckBox combatQualityBox = new JCheckBox("Combat Quality: ",
				isCombatQuality());
		editPanel.add(combatQualityBox);

		final JComboBox<QualityType> qualityTypeBox = ShadowrunTrackingUtil
				.addEnumComboBox(editPanel, "Type", QualityType.values(),
						getQualityType());

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this quality", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			ShadowrunTrackingUtil.examineChangedString(fullDescriptionField,
					"Full Desc", (s) -> setFullDesc(s), () -> getFullDesc());

			ShadowrunTrackingUtil.examineChangedString(briefDescriptionField,
					"Brief Desc", (s) -> setBriefDesc(s), () -> getBriefDesc());

			ShadowrunTrackingUtil.examineChangedBoolean(combatQualityBox,
					"Combat Quality", (b) -> setCombatQuality(b),
					() -> isCombatQuality());

			QualityType newQualityType = (QualityType) qualityTypeBox
					.getSelectedItem();
			if (newQualityType == null) {
				newQualityType = QualityType.NEUTRAL;
			}

			if (!newQualityType.equals(getQualityType())) {
				setQualityType(newQualityType);
			} else {
				System.out.println("Quality Type unchanged: ["
						+ getQualityType() + "]");
			}

		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	@Override
	public boolean isCombatQuality() {
		return myCombatQuality;
	}

	/**
	 * Set combat quality flag. true if the quality is related to combat.
	 * 
	 * @param iCombatQuality
	 *            true if the quality is related to combat.
	 */
	private void setCombatQuality(final boolean iCombatQuality) {
		myCombatQuality = iCombatQuality;
	}

	@Override
	public QualityType getQualityType() {
		return myQualityType;
	}

	/**
	 * Set quality type.
	 * 
	 * @param iQualityType
	 *            quality type.
	 */
	private void setQualityType(final QualityType iQualityType) {
		myQualityType = iQualityType;
	}

	@Override
	public String getBriefDesc() {
		return myBriefDesc;
	}

	@Override
	public String toString() {
		return getQualityType().getValue() + ":" + getBriefDesc();
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder(getQualityType()
				.getValue() + ":");

		oResult.append(getFullDesc());

		return oResult.toString();
	}
}
