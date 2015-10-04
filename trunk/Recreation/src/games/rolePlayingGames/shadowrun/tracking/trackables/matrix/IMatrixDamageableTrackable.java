package games.rolePlayingGames.shadowrun.tracking.trackables.matrix;

import games.rolePlayingGames.shadowrun.tracking.notes.impl.DeviceMatrixDamageNote;

import java.util.ArrayList;

/**
 * A trackable that can take matrix damage.
 * 
 * @author Andrew
 */
public interface IMatrixDamageableTrackable extends IMatrixBeing {
	/**
	 * @return maximum health of this trackable.
	 */
	int getMaximumMatrixHealth();

	/**
	 * @return total matrix damage.
	 */
	int getMatrixDamage();

	/**
	 * @return list of damage notes.
	 */
	ArrayList<DeviceMatrixDamageNote> getMatrixDamageNotes();

	/**
	 * Remove the given damage note.
	 * 
	 * @param iDamageNote
	 *            the note to remove.
	 */
	void removeMatrixDamageNote(DeviceMatrixDamageNote iDamageNote);

	/**
	 * Add the given damage note.
	 * 
	 * @param iDamageNote
	 *            the note to add.
	 */
	void addDamageNote(DeviceMatrixDamageNote iDamageNote);
}
