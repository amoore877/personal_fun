package games.rolePlayingGames.shadowrun.tracking.trackables;

import games.rolePlayingGames.tracking.trackable.ITrackable;

/**
 * A trackable that can take damage.
 * 
 * @author Andrew
 */
public interface IMatrixDamageableTrackable extends ITrackable {

	/**
	 * @return maximum health of this trackable.
	 */
	int getMaximumMatrixHealth();

	/**
	 * @return total matrix damage.
	 */
	int getMatrixDamage();

	/**
	 * Set total matrix damage.
	 * 
	 * @param iMatrixDamage
	 *            new damage.
	 */
	void setMatrixDamage(int iMatrixDamage);
}
