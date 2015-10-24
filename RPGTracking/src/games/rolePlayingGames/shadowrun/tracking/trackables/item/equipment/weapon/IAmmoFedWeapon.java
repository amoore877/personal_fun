package games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon;

/**
 * A weapon that is itself consumable, has charge, or uses bullets, etc.
 * 
 * @author Andrew
 *
 */
public interface IAmmoFedWeapon extends IShadowrunWeapon {

	/**
	 * @return total capacity of this weapon's "clip" or fully charged state.
	 */
	int getClipCapacity();

	/**
	 * @return ammo in the weapon's "clip."
	 */
	int getAmmoInClip();

	/**
	 * @return get number of spare "clips."
	 */
	int getSpareClips();

	/**
	 * Decrease spare clips by 1 and "reload" the weapon. Checking should be
	 * done to ensure a spare clip is available.
	 */
	void reload();
}
