package games.rolePlayingGames.shadowrun.tracking.trackables.matrix.device;

import games.rolePlayingGames.tracking.trackable.ICombatTrackable;

/**
 * A Shadowrun device that has a pilot score, and therefore can enter combat.
 * Only track pilot scores on non-player devices. Marker class.
 * 
 * @author Andrew
 */
public interface IAutoPilotDevice extends IDevice, ICombatTrackable {

}
