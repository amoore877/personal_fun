package games.rolePlayingGames.shadowrun.tracking.trackables.matrix.device;

import games.rolePlayingGames.shadowrun.tracking.trackables.item.AbstractShadowrunItem;
import games.rolePlayingGames.tracking.trackable.ICombatTrackable;
import games.rolePlayingGames.tracking.trackable.IInventoryManageableTrackable;

/**
 * A Shadowrun device that has a pilot score, and therefore can enter combat.
 * Only track pilot scores on non-player devices. Marker class.
 * 
 * @author Andrew
 */
public interface IAutoPilotDevice extends IDevice, ICombatTrackable,
		IInventoryManageableTrackable<AbstractShadowrunItem> {

}
