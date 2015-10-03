package games.rolePlayingGames.shadowrun.util;

/**
 * The firing modes of Shadowrun firearms.
 * 
 * @author Andrew
 */
public enum FiringMode {

	/**
	 * Single shot. Must use simple action to rechamber. Can only fire single
	 * shot.
	 */
	SS,
	/**
	 * Semi-automatic. Can fire single round as simple action or SA-burst as
	 * complex action.
	 */
	SA,
	/**
	 * Burst-fire. Can fire regular burst fire as simple action or long burst as
	 * complex action.
	 */
	BF,
	/**
	 * Full-auto. Can fire a simple full-auto or complex full-auto or do
	 * suppressive fire.
	 */
	FA;

}
