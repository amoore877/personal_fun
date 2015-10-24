package games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon;

import games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.IShadowrunEquipment;
import games.rolePlayingGames.shadowrun.util.DamageElement;
import games.rolePlayingGames.tracking.trackable.item.IWeapon;

/**
 * A weapon in Shadowrun. All weapons have Accuracy, Damage Value, Armor
 * Piercing, and a Damage Element.
 * 
 * @author Andrew
 */
public interface IShadowrunWeapon extends IShadowrunEquipment, IWeapon {

	/**
	 * @return accuracy of the weapon.
	 */
	int getAccuracy();

	/**
	 * @return damage value of the weapon.
	 */
	int getDamageValue();

	/**
	 * @return armor piercing of the weapon.
	 */
	int getArmorPiercing();

	/**
	 * @return damage element.
	 */
	DamageElement getDamageElement();

	/**
	 * Use the weapon. Display a dialog asking for "bullet" usage, if
	 * applicable.
	 */
	void use();
}
