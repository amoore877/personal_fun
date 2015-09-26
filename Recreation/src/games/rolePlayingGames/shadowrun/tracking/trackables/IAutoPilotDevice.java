package games.rolePlayingGames.shadowrun.tracking.trackables;

/**
 * A Shadowrun device that has a pilot score.
 * 
 * @author Andrew
 */
public interface IAutoPilotDevice extends IDevice {

	/**
	 * @return pilot attribute.
	 */
	int getPilot();

	/**
	 * Set the pilot attribute.
	 * 
	 * @param iPilot
	 *            new pilot.
	 */
	void setPilot(int iPilot);
}
