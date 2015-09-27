package games.rolePlayingGames.shadowrun.tracking.trackables.matrix;

import games.rolePlayingGames.tracking.trackable.ICombatTrackable;

/**
 * A Shadowrun device that has a pilot score, and therefore can enter combat.
 * 
 * @author Andrew
 */
public interface IAutoPilotDevice extends IDevice, ICombatTrackable {

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
