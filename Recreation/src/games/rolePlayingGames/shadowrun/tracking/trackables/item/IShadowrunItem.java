package games.rolePlayingGames.shadowrun.tracking.trackables.item;

import games.rolePlayingGames.shadowrun.tracking.notes.damage.device.ItemPhysicalDamageNote;
import games.rolePlayingGames.tracking.trackable.IDestructibleTrackable;
import games.rolePlayingGames.tracking.trackable.item.IItem;

/**
 * Some item in Shadowrun. Can be destroyed in the physical world.
 * 
 * @author Andrew
 */
public interface IShadowrunItem extends IItem,
		IDestructibleTrackable<ItemPhysicalDamageNote> {

	/**
	 * @return structure/body score of the item.
	 */
	int getBody();

	/**
	 * @return armor score of the item.
	 */
	int getArmor();
}
