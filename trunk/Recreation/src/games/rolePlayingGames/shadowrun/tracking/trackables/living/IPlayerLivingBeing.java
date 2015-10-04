package games.rolePlayingGames.shadowrun.tracking.trackables.living;

import games.rolePlayingGames.shadowrun.tracking.trackables.IPlayer;

/**
 * Shadowrun player-controlled living being. Don't need as much information as
 * we do about NPCs, since players keep their own stats. Marker class.
 * 
 * @author Andrew
 */
public interface IPlayerLivingBeing extends IPlayer {

	/**
	 * @return number of dice to con with.
	 */
	int getCon();

	/**
	 * @return number of dice to resist con with.
	 */
	int getConResist();

	/**
	 * @return physical limit. If damage taken from one attack exceeds this (or
	 *         damage is at least 10), then character is knocked down.
	 */
	int getPhysicalLimit();
}
