package games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon;

import games.rolePlayingGames.shadowrun.util.DamageElement;

/**
 * A weapon that has some limited usage before needing to be
 * reloaded/recharged/retrieved from an enemy's neck.
 * 
 * @author Andrew
 */
public abstract class AbstractAmmoFedWeapon extends AbstractShadowrunWeapon
		implements IAmmoFedWeapon {

	/**
	 * Capacity of clip.
	 */
	private int myClipCapacity;

	/**
	 * Ammo in current clip.
	 */
	private int myAmmoInClip;

	/**
	 * Spare clips.
	 */
	private int mySpareClips;

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
	 * @param iClipCapacity
	 *            clip capacity.
	 * @param iSpareClips
	 *            spare clips available.
	 */
	public AbstractAmmoFedWeapon(final String iName, final String iBenefits,
			final int iAccuracy, final int iDamageValue,
			final int iArmorPiercing, final DamageElement iDamageElement,
			final int iClipCapacity, final int iSpareClips) {
		super(iName, iBenefits, iAccuracy, iDamageValue, iArmorPiercing,
				iDamageElement);
		myClipCapacity = iClipCapacity;
		myAmmoInClip = iClipCapacity;
		mySpareClips = iSpareClips;
	}

	@Override
	public final int getClipCapacity() {
		return myClipCapacity;
	}

	/**
	 * Set clip capacity.
	 * 
	 * @param iClipCapacity
	 *            new clip capacity.
	 */
	protected final void setClipCapacity(final int iClipCapacity) {
		myClipCapacity = iClipCapacity;
	}

	@Override
	public final int getAmmoInClip() {
		return myAmmoInClip;
	}

	/**
	 * Set ammo in clip.
	 * 
	 * @param iAmmoInClip
	 *            new ammo in clip.
	 */
	protected final void setAmmoInClip(final int iAmmoInClip) {
		myAmmoInClip = iAmmoInClip;
	}

	@Override
	public final int getSpareClips() {
		return mySpareClips;
	}

	/**
	 * Set spare clips.
	 * 
	 * @param iSpareClips
	 *            new spare clips.
	 */
	protected final void setSpareClips(final int iSpareClips) {
		mySpareClips = iSpareClips;
	}

	@Override
	public final void reload() {
		mySpareClips--;
		myAmmoInClip = myClipCapacity;
	}

	@Override
	public abstract void use();

	@Override
	public abstract String toFullString();

	@Override
	public abstract void edit();

	@Override
	public abstract String toString();

}
