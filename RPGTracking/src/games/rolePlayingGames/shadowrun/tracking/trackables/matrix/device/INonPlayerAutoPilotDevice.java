package games.rolePlayingGames.shadowrun.tracking.trackables.matrix.device;

import games.rolePlayingGames.shadowrun.tracking.trackables.IAbleActor;

/**
 * A Shadowrun NPC auto-pilot device. Need details.
 * 
 * @author Andrew
 */
public interface INonPlayerAutoPilotDevice extends IAbleActor,
		IAutoPilotDevice, INonPlayerDevice {

	/**
	 * @return pilot attribute.
	 */
	int getPilot();
}
