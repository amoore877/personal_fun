package games.rolePlayingGames.shadowrun.tracking.trackables.matrix;

import games.rolePlayingGames.shadowrun.tracking.notes.impl.DeviceMatrixDamageNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.IShadowrunTrackable;

import java.util.ArrayList;

/**
 * A trackable that can take matrix damage.
 * 
 * @author Andrew
 */
public interface IMatrixDamageableTrackable extends IShadowrunTrackable {

	/**
	 * @return device/agent rating.
	 */
	int getRating();

	/**
	 * Set device/agent rating.
	 * 
	 * @param iRating
	 *            new device/agent rating.
	 */
	void setRating(int iRating);

	/**
	 * @return firewall attribute.
	 */
	int getFirewall();

	/**
	 * Set firewall attribute.
	 * 
	 * @param iFirewall
	 *            new firewall.
	 */
	void setFirewall(int iFirewall);

	/**
	 * @return data processing attribute.
	 */
	int getDataProcessing();

	/**
	 * Set data processing attribute.
	 * 
	 * @param iDataProcessing
	 *            new data processing attribute.
	 */
	void setDataProcessing(int iDataProcessing);

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
