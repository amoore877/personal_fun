package games.rolePlayingGames.tracking.trackable;

/**
 * Trackable object. Does not necessarily have initiative but we want to keep
 * track of its status/existence for some reason.
 * 
 * @author Andrew
 */
public interface ITrackable {

	/**
	 * @return name of this trackable thing.
	 */
	String getName();

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
