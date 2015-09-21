package games.rolePlayingGames.tracking.note;

/**
 * BasicNote interface.
 * 
 * @author Andrew
 */
public interface INote {

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
