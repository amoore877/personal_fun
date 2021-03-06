package games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon.AbstractMeleeWeapon;
import games.rolePlayingGames.shadowrun.util.DamageElement;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Non-charge melee weapon.
 * 
 * @author Andrew
 */
public final class MeleeWeapon extends AbstractMeleeWeapon {

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name of the weapon.
	 * @param iBenefits
	 *            benefits of weapon, beyond being a weapon.
	 * @param iAccuracy
	 *            accuracy.
	 * @param iDamageValue
	 *            damage value, without strength.
	 * @param iArmorPiercing
	 *            armor piercing.
	 * @param iDamageElement
	 *            damage element.
	 * @param iReach
	 *            reach of the weapon.
	 */
	public MeleeWeapon(final String iName, final String iBenefits,
			final int iAccuracy, final int iDamageValue,
			final int iArmorPiercing, final DamageElement iDamageElement,
			final int iReach) {
		super(iName, iBenefits, iAccuracy, iDamageValue, iArmorPiercing,
				iDamageElement, iReach);
	}

	@Override
	public void use() {
		// TODO prompt for strength
		// TODO roll
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder(getName());

		oResult.append(" Acc:" + getAccuracy());

		oResult.append(" DV:" + getDamageValue());

		oResult.append(" AP:" + getArmorPiercing());

		oResult.append(" Reach:" + getReach());

		if (!getBenefits().isEmpty()) {
			oResult.append(" Extra: " + getBenefits());
		}

		if (!getDamageElement().equals(DamageElement.REGULAR)) {
			oResult.append(" Element:" + getDamageElement().toString());
		}

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
				"Edit this weapon", JOptionPane.OK_CANCEL_OPTION,
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
		final StringBuilder oResult = new StringBuilder(getName());

		if (getTotalDamage() != 0) {
			oResult.append(" DAMAGED");
		} else {
			oResult.append(" Acc:" + getAccuracy());

			oResult.append(" DV:" + getDamageValue());

			oResult.append(" AP:" + getArmorPiercing());

			oResult.append(" Reach:" + getReach());

			if (!getDamageElement().equals(DamageElement.REGULAR)) {
				oResult.append(" Element:" + getDamageElement().toString());
			}
		}

		return oResult.toString();
	}

}
