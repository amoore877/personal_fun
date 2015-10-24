package games.rolePlayingGames.tracking.trackable;

import games.rolePlayingGames.tracking.note.damage.IStunNote;

import java.util.ArrayList;

/**
 * A trackable that can take damage.
 * 
 * @param <S>
 *            most high-level stun that this trackable can hold.
 * 
 * @author Andrew
 */
public interface IStunnableTrackable<S extends IStunNote> extends ITrackable {

	/**
	 * @return maximum stun health of this trackable.
	 */
	int getMaximumStunHealth();

	/**
	 * @return total accumulated stun.
	 */
	int getTotalStun();

	/**
	 * @return list of stun notes.
	 */
	ArrayList<S> getStunNotes();

	/**
	 * Remove the given stun note.
	 * 
	 * @param iStunNote
	 *            the note to remove.
	 */
	void removeStunNote(S iStunNote);

	/**
	 * Add the given stun note.
	 * 
	 * @param iStunNote
	 *            the note to add.
	 */
	void addStunNote(S iStunNote);
}
