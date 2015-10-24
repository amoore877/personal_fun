package games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon;

import games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.AbstractShadowrunEquipment;
import games.rolePlayingGames.shadowrun.util.DamageElement;

/**
 * Abstract Shadowrun weapon. Assumed to have 0 Body and 0 Armor, which should
 * be unadjustable.
 * 
 * @author Andrew
 */
public abstract class AbstractShadowrunWeapon extends
		AbstractShadowrunEquipment implements IShadowrunWeapon {

	/**
	 * Accuracy.
	 */
	private int myAccuracy;

	/**
	 * Damage value.
	 */
	private int myDamageValue;

	/**
	 * Armor piercing.
	 */
	private int myArmorPiercing;

	/**
	 * Damage element.
	 */
	private DamageElement myDamageElement;

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
	 */
	public AbstractShadowrunWeapon(final String iName, final String iBenefits,
			final int iAccuracy, final int iDamageValue,
			final int iArmorPiercing, final DamageElement iDamageElement) {
		super(iName, 0, 0, iBenefits);
		myAccuracy = iAccuracy;
		myDamageValue = iDamageValue;
		myArmorPiercing = iArmorPiercing;
		if (iDamageElement == null) {
			myDamageElement = DamageElement.REGULAR;
		} else {
			myDamageElement = iDamageElement;
		}
	}

	@Override
	public int getAccuracy() {
		return myAccuracy;
	}

	/**
	 * Set accuracy.
	 * 
	 * @param iAccuracy
	 *            new accuracy.
	 */
	protected final void setAccuracy(final int iAccuracy) {
		myAccuracy = iAccuracy;
	}

	@Override
	public int getDamageValue() {
		return myDamageValue;
	}

	/**
	 * Set damage value.
	 * 
	 * @param iDamageValue
	 *            new damage value.
	 */
	protected final void setDamageValue(final int iDamageValue) {
		myDamageValue = iDamageValue;
	}

	@Override
	public int getArmorPiercing() {
		return myArmorPiercing;
	}

	/**
	 * Set armor piercing.
	 * 
	 * @param iArmorPiercing
	 *            new armor piercing.
	 */
	protected final void setArmorPiercing(final int iArmorPiercing) {
		myArmorPiercing = iArmorPiercing;
	}

	@Override
	public DamageElement getDamageElement() {
		return myDamageElement;
	}

	/**
	 * Set damage element.
	 * 
	 * @param iDamageElement
	 *            new damage element.
	 */
	protected final void setDamageElement(final DamageElement iDamageElement) {
		if (iDamageElement == null) {
			myDamageElement = DamageElement.REGULAR;
		} else {
			myDamageElement = iDamageElement;
		}
	}

	@Override
	public abstract String toFullString();

	@Override
	public abstract void edit();

	@Override
	public abstract String toString();

}
