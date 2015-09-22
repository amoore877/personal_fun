package games.rolePlayingGames.tracking.trackable;

import games.rolePlayingGames.tracking.trackable.item.IItem;

import java.util.List;

/**
 * Trackable that can hold some inventory of items.
 * 
 * @author Andrew
 */
public interface IInventoryManageableTrackable extends ITrackable {

	/**
	 * @return list of items in inventory.
	 */
	List<IItem> getInventory();

}
