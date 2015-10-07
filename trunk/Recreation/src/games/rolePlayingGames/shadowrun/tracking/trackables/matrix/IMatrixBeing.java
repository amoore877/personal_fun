package games.rolePlayingGames.shadowrun.tracking.trackables.matrix;

import games.rolePlayingGames.shadowrun.tracking.trackables.IShadowrunTrackable;

/**
 * A being of some sort reachable on the Matrix. Would have a rating, firewall,
 * and data processing. Only need to track such things for non-player beings.
 * 
 * @author Andrew
 *
 */
public interface IMatrixBeing extends IShadowrunTrackable {

	/**
	 * @return device/agent rating.
	 */
	int getRating();
}
