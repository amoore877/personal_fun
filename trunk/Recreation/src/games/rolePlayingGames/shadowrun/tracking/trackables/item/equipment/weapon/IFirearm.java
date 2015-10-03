package games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon;

import games.rolePlayingGames.shadowrun.util.FiringMode;

import java.util.Set;

/**
 * A firearm in Shadowrun.
 * 
 * @author Andrew
 */
public interface IFirearm extends IRangedWeapon, IAmmoFedWeapon {

	/**
	 * @return set of firing modes for the weapon.
	 */
	Set<FiringMode> getFiringModes();

	/**
	 * @return recoil compensation.
	 */
	int getRecoilCompensation();
}
