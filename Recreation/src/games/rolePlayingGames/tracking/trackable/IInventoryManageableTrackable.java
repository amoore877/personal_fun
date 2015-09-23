package games.rolePlayingGames.tracking.trackable;

import games.rolePlayingGames.tracking.trackable.item.IItem;

import java.util.ArrayList;

/**
 * Trackable that can hold some inventory of items.
 * 
 * @param <I>
 *            most high-level inventory item that this trackable can hold.
 * 
 * @author Andrew
 */
public interface IInventoryManageableTrackable<I extends IItem> extends
		ITrackable {

	/**
	 * @return list of items in inventory.
	 */
	ArrayList<I> getInventory();

}
