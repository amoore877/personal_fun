package games.rolePlayingGames.shadowrun.tracking.notes.ability;

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

		final JTextField nameField = new JTextField(getFullDesc());
		editPanel.add(new JLabel("Name: "));
		editPanel.add(nameField);

		final JFormattedTextField levelField = new JFormattedTextField(
				new NumberFormatter(NumberFormat.getIntegerInstance()));
		levelField.setValue(Integer.valueOf(getLevel()));
		editPanel.add(new JLabel("Level: "));
		editPanel.add(levelField);

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
