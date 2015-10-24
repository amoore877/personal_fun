package games.rolePlayingGames.shadowrun.tracking.notes.status;

import games.rolePlayingGames.tracking.note.status.IStatusEffectNote;

/**
 * A status effect in Shadowrun.
 * 
 * @author Andrew
 */
public interface IShadowrunStatusEffectNote extends IStatusEffectNote {

	/**
	 * @return true if the status effect is related to combat.
	 */
	boolean isCombatStatusEffect();

	/**
	 * @return status effect type.
	 */
	StatusEffectType getStatusEffectType();

}
