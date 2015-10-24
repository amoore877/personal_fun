package games.rolePlayingGames.shadowrun.tracking.notes.damage.character;

import games.rolePlayingGames.shadowrun.tracking.notes.damage.IShadowrunDamageNote;

/**
 * A character wound in Shadowrun.
 * 
 * @author Andrew
 */
public interface ICharacterWoundNote extends IShadowrunDamageNote {

	/**
	 * @return true if the wound can only be healed naturally, false otherwise.
	 */
	boolean isNaturalOnly();

	/**
	 * @return true if physically healed, false otherwise.
	 */
	boolean isPhysicallyHealed();

	/**
	 * @return true if magically healed, false otherwise.
	 */
	boolean isMagicallyHealed();
}
