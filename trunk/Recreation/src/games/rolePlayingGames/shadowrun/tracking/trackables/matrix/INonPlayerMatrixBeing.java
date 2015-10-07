package games.rolePlayingGames.shadowrun.tracking.trackables.matrix;

import games.rolePlayingGames.shadowrun.tracking.trackables.INonPlayer;

/**
 * Non-player matrix beings. Need data processing and firewall.
 * 
 * @author Andrew
 *
 */
public interface INonPlayerMatrixBeing extends INonPlayer, IMatrixBeing {

	/**
	 * @return firewall attribute.
	 */
	int getFirewall();

	/**
	 * @return data processing attribute.
	 */
	int getDataProcessing();
}
