package games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon;

import games.rolePlayingGames.shadowrun.util.DamageElement;

/**
 * Shadowrun abstract melee weapon.
 * 
 * @author Andrew
 */
public abstract class AbstractMeleeWeapon extends AbstractShadowrunWeapon
		implements IMeleeWeapon {

	/**
	 * Reach.
	 */
	private int myReach;

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
	 *            damage value.
	 * @param iArmorPiercing
	 *            armor piercing.
	 * @param iDamageElement
	 *            damage element.
	 * @param iReach
	 *            reach of the weapon.
	 */
	public AbstractMeleeWeapon(final String iName, final String iBenefits,
			final int iAccuracy, final int iDamageValue,
			final int iArmorPiercing, final DamageElement iDamageElement,
			final int iReach) {
		super(iName, iBenefits, iAccuracy, iDamageValue, iArmorPiercing,
				iDamageElement);
		myReach = iReach;
	}

	@Override
	public abstract void use();

	@Override
	public abstract String toFullString();

	@Override
	public abstract void edit();

	@Override
	public abstract String toString();

	@Override
	public int getReach() {
		return myReach;
	}

	/**
	 * Set the reach.
	 * 
	 * @param iReach
	 *            new reach value.
	 */
	protected final void setReach(final int iReach) {
		myReach = iReach;
	}
}
