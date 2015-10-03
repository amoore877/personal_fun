package games.rolePlayingGames.shadowrun.tracking.trackables.impl;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.ItemPhysicalDamageNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.AbstractShadowrunItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.ITimedItem;

import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Some timed item; has initiative in combat.
 * 
 * @author Andrew
 */
public final class TimedItem extends AbstractShadowrunItem implements
		ITimedItem {

	/**
	 * Initiative.
	 */
	private int myInitiative;

	/**
	 * Effect of the item.
	 */
	private String myEffect;

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name of item.
	 * @param iBody
	 *            body attribute.
	 * @param iArmor
	 *            armor attribute.
	 * @param iInitiative
	 *            initiative.
	 * @param iEffect
	 *            effect.
	 */
	public TimedItem(final String iName, final int iBody, final int iArmor,
			final int iInitiative, final String iEffect) {
		super(iName, iBody, iArmor);

		myInitiative = iInitiative;

		if (iEffect == null || iEffect.isEmpty()) {
			myEffect = "unknown";
		} else {
			myEffect = iEffect;
		}
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder(getName());

		oResult.append(": Max Health-" + getMaximumHealth());

		for (final ItemPhysicalDamageNote damageNote : getDamageNotes()) {
			oResult.append(", Damage-" + damageNote.toString());
		}

		oResult.append(" Body: " + getBody() + " Armor: " + getArmor());

		oResult.append(" Initiative: " + getInitiative());

		return oResult.toString();
	}

	@Override
	public void edit() {
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));

		// name
		final JTextField nameField = ShadowrunTrackingUtil.addStringField(
				editPanel, "Name", getName());

		// current damage notes
		ShadowrunTrackingUtil.addDamageButtons(editPanel, this);

		// initiative
		final JFormattedTextField initiativeField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Initiative", getInitiative());

		// effect
		final JTextField effectField = ShadowrunTrackingUtil.addStringField(
				editPanel, "Effect", getEffect());

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

			// initiative
			try {
				initiativeField.commitEdit();
			} catch (final ParseException iException) {
				System.err.println(iException.getMessage());
			}
			final int newInitiative = Integer.parseInt(initiativeField
					.getValue().toString());

			if (newInitiative != getInitiative()) {
				setInitiative(newInitiative);
			} else {
				System.out.println("Initiative unchanged: [" + getInitiative()
						+ "]");
			}

			// effect
			final String newEffect = effectField.getText();

			if (!newEffect.equals(getEffect())) {
				setEffect(newEffect);
			} else {
				System.out.println("Effect unchanged: [" + getEffect() + "]");
			}
		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	@Override
	public int getInitiative() {
		return myInitiative;
	}

	@Override
	public void setInitiative(final int iInitiative) {
		myInitiative = iInitiative;
	}

	@Override
	public String toString() {
		final StringBuilder oResult = new StringBuilder(getName());

		if (getTotalDamage() != 0) {
			oResult.append(" " + getTotalDamage() + "/" + getMaximumHealth());
		}

		return oResult.toString();
	}

	@Override
	public String getEffect() {
		return myEffect;
	}

	/**
	 * Set the effect.
	 * 
	 * @param iEffect
	 *            effect of the item.
	 */
	private void setEffect(final String iEffect) {
		myEffect = iEffect;
	}
}
