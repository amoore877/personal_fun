package games.rolePlayingGames.tracking.trackable.character;

import games.rolePlayingGames.tracking.note.damage.IDamageNote;
import games.rolePlayingGames.tracking.trackable.ICombatTrackable;
import games.rolePlayingGames.tracking.trackable.IDestructibleTrackable;

/**
 * Character interface. Marker class.
 * 
 * @param <D>
 *            most high-level damage that this trackable can hold.
 * 
 * @author Andrew
 */
public interface ICharacter<D extends IDamageNote> extends ICombatTrackable,
		IDestructibleTrackable<D> {

}