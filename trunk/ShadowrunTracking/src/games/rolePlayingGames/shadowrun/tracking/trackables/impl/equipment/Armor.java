package games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.ItemPhysicalDamageNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.AbstractShadowrunEquipment;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.IShadowrunArmor;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Shadowrun Armor. Has armor value and description. Body is assumed to be same
 * as armor, following rules of structures.
 * 
 * @author Andrew
 *
 */
public final class Armor extends AbstractShadowrunEquipment implements
		IShadowrunArmor {

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name of item.
	 * @param iArmor
	 *            armor attribute.
	 * @param iBenefits
	 *            description of any other benefits beyond being armor.
	 */
	public Armor(final String iName, final int iArmor, final String iBenefits) {
		super(iName, iArmor, iArmor, iBenefits);
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder(getName());

		oResult.append(": Max Health-" + getMaximumHealth());

		for (final ItemPhysicalDamageNote damageNote : getDamageNotes()) {
			oResult.append(", Damage-" + damageNote.toString());
		}

		oResult.append(" Body: " + getBody() + " Armor: " + getArmor());

		oResult.append(" Benefits: " + getBenefits());

		return oResult.toString();
	}

	@Override
	public void edit() {
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));

		// name
		final JTextField nameField = ShadowrunTrackingUtil.addStringField(
				editPanel, "Name", getName());

		// benefits
		final JTextField benefitsField = ShadowrunTrackingUtil.addStringField(
				editPanel, "Benefits", getBenefits());

		// current damage notes
		ShadowrunTrackingUtil.addDamageButtons(editPanel, this);

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this armor", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// name
			ShadowrunTrackingUtil.examineChangedString(nameField, "Name",
					(s) -> setName(s), () -> getName());

			// benefits
			ShadowrunTrackingUtil.examineChangedString(benefitsField,
					"Benefits", (s) -> setBenefits(s), () -> getBenefits());
		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	@Override
	public String toString() {
		final StringBuilder oResult = new StringBuilder(getName() + ": "
				+ getArmor());

		if (getTotalDamage() != 0) {
			oResult.append(" DAMAGED");
		}

		return oResult.toString();
	}
}
