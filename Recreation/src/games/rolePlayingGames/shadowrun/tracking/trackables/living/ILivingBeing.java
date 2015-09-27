package games.rolePlayingGames.shadowrun.tracking.trackables.living;

import games.rolePlayingGames.shadowrun.tracking.notes.damage.IShadowrunDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.quality.IShadowrunQualityNote;
import games.rolePlayingGames.shadowrun.tracking.notes.status.IShadowrunStatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.IShadowrunTrackable;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.IShadowrunItem;
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
		IShadowrunTrackable, ICharacter<D>,
		IEffectableTrackable<IShadowrunStatusEffectNote>,
		ITraitableTrackable<IShadowrunQualityNote>,
		IInventoryManageableTrackable<IShadowrunItem> {
	/**
	 * @return essence attribute, rounded down.
	 */
	int getEssence();

	/**
	 * @return magic/resonance attribute.
	 */
	int getSpecialAtt();
}
