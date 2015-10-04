package games.rolePlayingGames.shadowrun.tracking.trackables.matrix;

import games.rolePlayingGames.shadowrun.tracking.trackables.INonPlayer;

/**
 * Non-player matrix beings. Need data processing, rating, and firewall.
 * 
 * @author Andrew
 *
 */
public interface INonPlayerMatrixBeing extends INonPlayer, IMatrixBeing {

	/**
	 * @return device/agent rating.
	 */
	int getRating();

	/**
	 * Set device/agent rating.
	 * 
	 * @param iRating
	 *            new device/agent rating.
	 */
	void setRating(int iRating);

	/**
	 * @return firewall attribute.
	 */
	int getFirewall();

	/**
	 * @return data processing attribute.
	 */
	int getDataProcessing();
}
