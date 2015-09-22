package games.rolePlayingGames.tracking.trackable;

import games.rolePlayingGames.tracking.note.damage.IDamageNote;

import java.util.List;

/**
 * A trackable that can take damage.
 * 
 * @author Andrew
 */
public interface IDestructibleTrackable extends ITrackable {

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
	List<IDamageNote> getDamageNotes();
}
