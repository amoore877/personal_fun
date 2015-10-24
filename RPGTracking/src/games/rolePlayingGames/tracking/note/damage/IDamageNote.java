package games.rolePlayingGames.tracking.note.damage;

import games.rolePlayingGames.tracking.note.IFullDescNote;

/**
 * A damage note.
 * 
 * @author Andrew
 */
public interface IDamageNote extends IFullDescNote {

	/**
	 * @return damage amount.
	 */
	int getDamage();

	/**
	 * @return healed/repaired amount.
	 */
	int getHealed();
}
