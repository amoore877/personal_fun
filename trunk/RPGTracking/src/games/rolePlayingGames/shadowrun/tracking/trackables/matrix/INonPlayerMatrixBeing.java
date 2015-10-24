package games.rolePlayingGames.shadowrun.tracking.trackables.matrix;

import games.rolePlayingGames.shadowrun.tracking.trackables.INonPlayer;
import games.rolePlayingGames.shadowrun.tracking.trackables.IShadowrunCombatTrackable;

/**
 * Non-player matrix beings. Need data processing and firewall.
 * 
 * @author Andrew
 *
 */
public interface INonPlayerMatrixBeing extends INonPlayer, IMatrixBeing,
		IShadowrunCombatTrackable {

	/**
	 * @return firewall attribute.
	 */
	int getFirewall();

	/**
	 * @return data processing attribute.
	 */
	int getDataProcessing();
}
