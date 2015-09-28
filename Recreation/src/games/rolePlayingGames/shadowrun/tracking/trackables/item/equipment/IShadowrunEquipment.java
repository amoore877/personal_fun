package games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment;

import games.rolePlayingGames.shadowrun.tracking.trackables.item.IShadowrunItem;
import games.rolePlayingGames.tracking.trackable.item.IEquipment;

/**
 * Equipment in Shadowrun. An item that serves some purpose that we want to
 * track.
 * 
 * @author Andrew
 */
public interface IShadowrunEquipment extends IShadowrunItem, IEquipment {

	/**
	 * @return full description of all benefits. Could be empty.
	 */
	String getBenefits();

}
