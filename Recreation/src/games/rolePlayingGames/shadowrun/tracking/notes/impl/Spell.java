package games.rolePlayingGames.shadowrun.tracking.notes.impl;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.notes.ability.AbstractAbility;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Some spell or special ability.
 * 
 * @author Andrew
 */
public final class Spell extends AbstractAbility {

	/**
	 * Brief description of the ability.
	 */
	private String myBriefDesc;

	/**
	 * Constructor.
	 * 
	 * @param iFullDesc
	 *            full description of the ability.
	 * @param iBriefDesc
	 *            brief description; such as name + roll info, or name only.
	 */
	public Spell(final String iFullDesc, final String iBriefDesc) {
		super(iFullDesc);

		if (iBriefDesc == null) {
			myBriefDesc = "";
		} else {
			myBriefDesc = iBriefDesc;
		}
	}

	/**
	 * @return brief description.
	 */
	public String getBriefDesc() {
		return myBriefDesc;
	}

	/**
	 * Set the brief description.
	 * 
	 * @param iBriefDesc
	 *            brief description; such as name + roll info, or name only.
	 */
	private void setBriefDesc(final String iBriefDesc) {
		if (iBriefDesc == null) {
			myBriefDesc = "";
		} else {
			myBriefDesc = iBriefDesc;
		}
	}

	@Override
	public String toFullString() {
		return getFullDesc();
	}

	@Override
	public void edit() {
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));

		final JTextField fullDescField = ShadowrunTrackingUtil.addStringField(
				editPanel, "Full Desc", getFullDesc());

		final JTextField briefDescField = ShadowrunTrackingUtil.addStringField(
				editPanel, "Brief Desc", getBriefDesc());

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this spell", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			final String newFullDesc = fullDescField.getText();

			if (!newFullDesc.equals(getFullDesc())) {
				setFullDesc(newFullDesc);
			} else {
				System.out.println("Full Desc unchanged: [" + getFullDesc()
						+ "]");
			}

			final String newBriefDesc = briefDescField.getText();

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

	@Override
	public String toString() {
		return getBriefDesc();
	}

}
