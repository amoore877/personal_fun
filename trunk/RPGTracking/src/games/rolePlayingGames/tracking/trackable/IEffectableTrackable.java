package games.rolePlayingGames.tracking.trackable;

import games.rolePlayingGames.tracking.note.status.IStatusEffectNote;

import java.util.ArrayList;

/**
 * Trackable that can have status effects.
 * 
 * @author Andrew
 *
 * @param <E>
 *            highest level status effect that can be applied.
 */
public interface IEffectableTrackable<E extends IStatusEffectNote> extends
		ITrackable {

	/**
	 * @return list of status effects.
	 */
	ArrayList<E> getStatusEffectNotes();

	/**
	 * Remove the given status effect note.
	 * 
	 * @param iStatusEffectNote
	 *            the note to remove.
	 */
	void removeStatusEffectNote(E iStatusEffectNote);

	/**
	 * Add the given status effect note.
	 * 
	 * @param iStatusEffectNote
	 *            the note to add.
	 */
	void addStatusEffectNote(E iStatusEffectNote);
}
