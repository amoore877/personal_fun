package games.rolePlayingGames.tracking.note.status;

import games.rolePlayingGames.tracking.note.IFullDescNote;

/**
 * A status effect note. Some effect beyond just damage.
 * 
 * @author Andrew
 */
public interface IStatusEffectNote extends IFullDescNote {

	/**
	 * @return the brief title of this effect.
	 */
	String getBriefDesc();

}