package games.rolePlayingGames.tracking.note;

import games.rolePlayingGames.tracking.IUniqueObject;

/**
 * BasicNote interface.
 * 
 * @author Andrew
 */
public interface INote extends IUniqueObject {

	/**
	 * @return Short String representation of the note.
	 */
	@Override
	String toString();

	/**
	 * @return Long String representation of the note.
	 */
	String toFullString();

	/**
	 * Edit the note in a pop-up window.
	 */
	void edit();
}
