package games.rolePlayingGames.tracking.trackable.character;

import games.rolePlayingGames.tracking.note.damage.IDamageNote;

/**
 * Player character. Marker class.
 * 
 * @param <D>
 *            most high-level damage that this trackable can hold.
 * 
 * @author Andrew
 */
public interface IPlayerCharacter<D extends IDamageNote> extends ICharacter<D> {

}
