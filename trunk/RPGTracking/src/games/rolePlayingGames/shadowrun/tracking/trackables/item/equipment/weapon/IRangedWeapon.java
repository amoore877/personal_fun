package games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon;

import games.rolePlayingGames.shadowrun.util.RangedWeaponType;

import java.util.Set;

/**
 * Ranged weapon.
 * 
 * @author Andrew
 *
 */
public interface IRangedWeapon extends IShadowrunWeapon {

	/**
	 * @return categories of ranged weapon types this weapon can belong to.
	 */
	Set<RangedWeaponType> getRangedWeaponTypes();

}
