package games.rolePlayingGames.shadowrun.tracking.trackables.impl;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.ItemPhysicalDamageNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.AbstractShadowrunItem;

import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The most basic Shadowrun item. Something that can be destroyed.
 * 
 * @author Andrew
 *
 */
public final class ShadowrunBasicItem extends AbstractShadowrunItem {

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name of item.
	 * @param iBody
	 *            body attribute.
	 * @param iArmor
	 *            armor attribute.
	 */
	public ShadowrunBasicItem(final String iName, final int iBody,
			final int iArmor) {
		super(iName, iBody, iArmor);
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder(getName());

		oResult.append(": Max Health-" + getMaximumHealth());

		for (final ItemPhysicalDamageNote damageNote : getDamageNotes()) {
			oResult.append(", Damage-" + damageNote.toString());
		}

		oResult.append(" Body: " + getBody() + " Armor: " + getArmor());

		return oResult.toString();
	}

	@Override
	public void edit() {
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));

		// name
		final JTextField nameField = ShadowrunTrackingUtil.addStringField(
				editPanel, "Name", getName());

		// body
		final JFormattedTextField bodyField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Body", getBody());

		// armor
		final JFormattedTextField armorField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Armor", getArmor());

		// current damage notes
		ShadowrunTrackingUtil.addDamageButtons(editPanel, this);

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this item", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// name
			final String newName = nameField.getText();

			if (!newName.equals(getName())) {
				setName(newName);
			} else {
				System.out.println("Name unchanged: [" + getName() + "]");
			}

			// body
			try {
				bodyField.commitEdit();
			} catch (final ParseException iException) {
				System.err.println(iException.getMessage());
			}
			final int newBody = Integer.parseInt(bodyField.getValue()
					.toString());

			if (newBody != getBody()) {
				setBody(newBody);
			} else {
				System.out.println("Body unchanged: [" + getBody() + "]");
			}

			// armor
			try {
				armorField.commitEdit();
			} catch (final ParseException iException) {
				System.err.println(iException.getMessage());
			}
			final int newArmor = Integer.parseInt(armorField.getValue()
					.toString());

			if (newArmor != getArmor()) {
				setArmor(newArmor);
			} else {
				System.out.println("Armor unchanged: [" + getArmor() + "]");
			}
		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	@Override
	public String toString() {
		final StringBuilder oResult = new StringBuilder(getName());

		if (getTotalDamage() != 0) {
			oResult.append(" " + getTotalDamage() + "/" + getMaximumHealth());
		}

		return oResult.toString();
	}
}
