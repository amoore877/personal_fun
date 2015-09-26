package games.rolePlayingGames.tracking.note.damage;

import games.rolePlayingGames.tracking.note.IFullDescNote;

/**
 * A stun note.
 * 
 * @author Andrew
 */
public interface IStunNote extends IFullDescNote {

	/**
	 * @return stun amount.
	 */
	int getStun();

	/**
	 * @return healed/repaired amount.
	 */
	int getHealed();
}
