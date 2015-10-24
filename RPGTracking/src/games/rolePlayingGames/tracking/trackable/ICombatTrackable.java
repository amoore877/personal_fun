package games.rolePlayingGames.tracking.trackable;

/**
 * Combat-trackable object/character. Has initiative.
 * 
 * @author Andrew
 */
public interface ICombatTrackable extends ITrackable {

	/**
	 * @return combat initiative of this trackable thing.
	 */
	int getInitiative();

	/**
	 * Set the initiative of this trackable.
	 * 
	 * @param iInitiative
	 *            new initiative.
	 */
	void setInitiative(int iInitiative);
}
