package games.rolePlayingGames.tracking.trackable;

import games.rolePlayingGames.tracking.note.damage.IDamageNote;

import java.util.ArrayList;

/**
 * A trackable that can take damage.
 * 
 * @param <D>
 *            most high-level damage that this trackable can hold.
 * 
 * @author Andrew
 */
public interface IDestructibleTrackable<D extends IDamageNote> extends
		ITrackable {

	/**
	 * @return maximum health of this trackable.
	 */
	int getMaximumHealth();

	/**
	 * @return total accumulated damage.
	 */
	int getTotalDamage();

	/**
	 * @return list of damage notes.
	 */
	ArrayList<D> getDamageNotes();

	/**
	 * Remove the given damage note.
	 * 
	 * @param iDamageNote
	 *            the note to remove.
	 */
	void removeDamageNote(D iDamageNote);

	/**
	 * Add the given damage note.
	 * 
	 * @param iDamageNote
	 *            the note to add.
	 */
	void addDamageNote(D iDamageNote);
}
