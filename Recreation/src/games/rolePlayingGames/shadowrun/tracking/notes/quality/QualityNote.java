package games.rolePlayingGames.shadowrun.tracking.notes.quality;

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

		final JTextField fullDescriptionField = new JTextField(getFullDesc());
		editPanel.add(new JLabel("Full Description: "));
		editPanel.add(fullDescriptionField);

		final JTextField briefDescriptionField = new JTextField(getBriefDesc());
		editPanel.add(new JLabel("Brief Description: "));
		editPanel.add(briefDescriptionField);

		final JCheckBox combatQualityBox = new JCheckBox("Combat Quality: ",
				isCombatQuality());
		editPanel.add(combatQualityBox);

		final JPanel qualityTypePanel = new JPanel(new GridLayout(1, 0));
		editPanel.add(new JLabel("Quality type:"));
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
		if (QualityType.POSITIVE.equals(getQualityType())) {
			positiveButton.setSelected(true);
		} else if (QualityType.NEGATIVE.equals(getQualityType())) {
			negativeButton.setSelected(true);
		} else {
			neutralButton.setSelected(true);
		}
		qualityTypePanel.add(positiveButton);
		qualityTypePanel.add(negativeButton);
		qualityTypePanel.add(neutralButton);
		editPanel.add(qualityTypePanel);

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

			final boolean newCombatQuality = combatQualityBox.isSelected();
			if (newCombatQuality != isCombatQuality()) {
				setCombatQuality(newCombatQuality);
			} else {
				System.out.println("Combat Quality unchanged: ["
						+ isCombatQuality() + "]");
			}

			QualityType newQualityType;
			if (positiveButton.isSelected()) {
				newQualityType = QualityType.POSITIVE;
			} else if (negativeButton.isSelected()) {
				newQualityType = QualityType.NEGATIVE;
			} else {
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
