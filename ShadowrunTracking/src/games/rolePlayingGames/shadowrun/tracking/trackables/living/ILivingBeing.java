package games.rolePlayingGames.shadowrun.tracking.trackables.living;

import games.rolePlayingGames.shadowrun.tracking.notes.damage.IShadowrunDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.QualityNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.StatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.IShadowrunCombatTrackable;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.AbstractShadowrunItem;
import games.rolePlayingGames.tracking.trackable.IEffectableTrackable;
import games.rolePlayingGames.tracking.trackable.IInventoryManageableTrackable;
import games.rolePlayingGames.tracking.trackable.ITraitableTrackable;
import games.rolePlayingGames.tracking.trackable.character.ICharacter;

/**
 * A living, breathing, character in Shadowrun.
 * 
 * @param <D>
 *            highest level damage note this being uses.
 * @author Andrew
 *
 */
public interface ILivingBeing<D extends IShadowrunDamageNote> extends
		IShadowrunCombatTrackable, ICharacter<D>,
		IEffectableTrackable<StatusEffectNote>,
		ITraitableTrackable<QualityNote>,
		IInventoryManageableTrackable<AbstractShadowrunItem> {
	/**
	 * @return essence attribute, rounded down.
	 */
	int getEssence();

	/**
	 * @return magic/resonance attribute.
	 */
	int getSpecialAtt();

	/**
	 * @return body attribute.
	 */
	int getBody();

	/**
	 * @return willpower attribute.
	 */
	int getWillpower();

	/**
	 * @return maximum stun health.
	 */
	int getMaximumStunHealth();

	/**
	 * @return total stun damage.
	 */
	int getTotalStunDamage();
}
