package games.rolePlayingGames.shadowrun.tracking.trackables.matrix.hacking;

import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.INonPlayerMatrixBeing;

/**
 * A Shadowrun device/persona/icon/being that has Attack/Sleaze. Can enter
 * combat.
 * 
 * @author Andrew
 */
public interface INonPlayerHackingBeing extends IHackingBeing,
		INonPlayerMatrixBeing {

	/**
	 * @return attack attribute.
	 */
	int getAttack();

	/**
	 * @return sleaze attribute.
	 */
	int getSleaze();
}
