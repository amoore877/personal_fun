package games.rolePlayingGames.shadowrun.tracking.trackables.item;

import games.rolePlayingGames.shadowrun.tracking.notes.impl.ItemPhysicalDamageNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.IShadowrunTrackable;
import games.rolePlayingGames.tracking.trackable.IDestructibleTrackable;
import games.rolePlayingGames.tracking.trackable.item.IItem;

/**
 * Some item in Shadowrun. Can be destroyed in the physical world.
 * 
 * @author Andrew
 */
public interface IShadowrunItem extends IItem,
		IDestructibleTrackable<ItemPhysicalDamageNote>, IShadowrunTrackable {

	/**
	 * @return structure/body score of the item.
	 */
	int getBody();

	/**
	 * @return armor score of the item.
	 */
	int getArmor();
}
