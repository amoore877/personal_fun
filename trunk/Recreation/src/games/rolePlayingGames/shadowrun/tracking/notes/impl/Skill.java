package games.rolePlayingGames.shadowrun.tracking.notes.impl;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.notes.ability.AbstractAbility;

import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A skill or active soft.
 * 
 * @author Andrew
 */
public final class Skill extends AbstractAbility {

	/**
	 * Level of the skill.
	 */
	private int myLevel;

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name of the skill/active soft.
	 * @param iLevel
	 *            level of the skill.
	 */
	public Skill(final String iName, final int iLevel) {
		super(iName);
		myLevel = iLevel;
	}

	/**
	 * @return level of the skill.
	 */
	public int getLevel() {
		return myLevel;
	}

	/**
	 * Set the level of the skill.
	 * 
	 * @param iLevel
	 *            new level of the skill.
	 */
	private void setLevel(final int iLevel) {
		myLevel = iLevel;
	}

	@Override
	public String toFullString() {
		return toString();
	}

	@Override
	public void edit() {
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));

		final JTextField nameField = ShadowrunTrackingUtil.addStringField(
				editPanel, "Name", getFullDesc());

		final JFormattedTextField levelField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Level", getLevel(), 0, 13);

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this skill", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			final String newName = nameField.getText();

			if (!newName.equals(getFullDesc())) {
				setFullDesc(newName);
			} else {
				System.out.println("Name unchanged: [" + getFullDesc() + "]");
			}

			try {
				levelField.commitEdit();
			} catch (final ParseException iException) {
				System.err.println(iException.getMessage());
			}
			final int newLevel = Integer.parseInt(levelField.getValue()
					.toString());

			if (newLevel != getLevel()) {
				setLevel(newLevel);
			} else {
				System.out.println("Level unchanged: [" + getLevel() + "]");
			}
		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	@Override
	public String toString() {
		return getFullDesc() + " " + getLevel();
	}

}
