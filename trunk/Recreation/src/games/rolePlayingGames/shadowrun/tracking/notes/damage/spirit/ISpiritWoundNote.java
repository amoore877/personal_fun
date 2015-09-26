package games.rolePlayingGames.shadowrun.tracking.notes.damage.spirit;

import games.rolePlayingGames.shadowrun.tracking.notes.damage.IShadowrunDamageNote;

/**
 * A spirit wound in Shadowrun.
 * 
 * @author Andrew
 */
public interface ISpiritWoundNote extends IShadowrunDamageNote {

	/**
	 * @return true if the wound can only be healed naturally, false otherwise.
	 */
	boolean isNaturalOnly();

	/**
	 * @return true if partially healed already, false otherwise.
	 */
	boolean isMagicallyHealed();
}
