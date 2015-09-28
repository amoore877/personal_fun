package games.rolePlayingGames.shadowrun.tracking.trackables.item;

import games.rolePlayingGames.tracking.trackable.ICombatTrackable;

/**
 * An item that is timed for combat (such as a grenade). Must have some effect,
 * otherwise it wouldn't matter that is has initiative.
 * 
 * @author Andrew
 */
public interface ITimedItem extends IShadowrunItem, ICombatTrackable {

	/**
	 * @return effect of the item when time is up.
	 */
	String getEffect();

}
