package games.rolePlayingGames.shadowrun.tracking.trackables.matrix.device;

/**
 * A Shadowrun NPC auto-pilot device. Need details.
 * 
 * @author Andrew
 */
public interface INonPlayerAutoPilotDevice extends IAutoPilotDevice,
		INonPlayerDevice {

	/**
	 * @return pilot attribute.
	 */
	int getPilot();
}
