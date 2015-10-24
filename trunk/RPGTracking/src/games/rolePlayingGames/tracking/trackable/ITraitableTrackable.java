package games.rolePlayingGames.tracking.trackable;

import games.rolePlayingGames.tracking.note.status.trait.ITraitNote;

import java.util.ArrayList;

/**
 * Trackable that can have status effects.
 * 
 * @author Andrew
 *
 * @param <T>
 *            highest level trait that can be applied.
 */
public interface ITraitableTrackable<T extends ITraitNote> extends ITrackable {

	/**
	 * @return list of traits.
	 */
	ArrayList<T> getTraitNotes();

	/**
	 * Remove the given trait note.
	 * 
	 * @param iTraitNote
	 *            the note to remove.
	 */
	void removeTraitNote(T iTraitNote);

	/**
	 * Add the given trait note.
	 * 
	 * @param iTraitNote
	 *            the note to add.
	 */
	void addTraitNote(T iTraitNote);
}
