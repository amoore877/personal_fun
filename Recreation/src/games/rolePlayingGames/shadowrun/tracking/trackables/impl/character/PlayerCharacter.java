package games.rolePlayingGames.shadowrun.tracking.trackables.impl.character;

import games.rolePlayingGames.shadowrun.tracking.notes.damage.character.AbstractCharacterDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.QualityNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.StatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.notes.quality.IShadowrunQualityNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.AbstractShadowrunItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.IShadowrunItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.living.AbstractCharacter;
import games.rolePlayingGames.shadowrun.tracking.trackables.living.IPlayerLivingBeing;

import java.util.ArrayList;

/**
 * Player-controlled character/technomancer.
 * 
 * @author Andrew
 */
public class PlayerCharacter extends AbstractCharacter implements
		IPlayerLivingBeing {

	/**
	 * Total con score.
	 */
	private final int myCon;

	/**
	 * Total con resist score.
	 */
	private final int myConResist;

	/**
	 * Physical limit.
	 */
	private final int myPhysicalLimit;

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name.
	 * @param iEssence
	 *            essence, rounded down.
	 * @param iBody
	 *            body.
	 * @param iWillpower
	 *            willpower.
	 * @param iSpecial
	 *            magic/resonance.
	 * @param iStatusEffects
	 *            status effects.
	 * @param iInventory
	 *            inventory.
	 * @param iQualities
	 *            qualities.
	 * @param iCon
	 *            total con score.
	 * @param iConResist
	 *            con resist.
	 * @param iPhysicalLimit
	 *            physical limit.
	 */
	public PlayerCharacter(final String iName, final int iEssence,
			final int iBody, final int iWillpower, final int iSpecial,
			final ArrayList<StatusEffectNote> iStatusEffects,
			final ArrayList<AbstractShadowrunItem> iInventory,
			final ArrayList<QualityNote> iQualities, final int iCon,
			final int iConResist, final int iPhysicalLimit) {
		super(iName, iEssence, iBody, iWillpower, iSpecial, iStatusEffects,
				iInventory, iQualities);

		myCon = iCon;
		myConResist = iConResist;
		myPhysicalLimit = iPhysicalLimit;
	}

	@Override
	public final int getCon() {
		return myCon;
	}

	@Override
	public final int getConResist() {
		return myConResist;
	}

	@Override
	public final int getPhysicalLimit() {
		return myPhysicalLimit;
	}

	@Override
	public final String toFullString() {
		final StringBuilder oResult = new StringBuilder();

		oResult.append("Name=" + getName());

		for (final AbstractCharacterDamageNote damageNote : getDamageNotes()) {
			oResult.append(", Damage=" + damageNote.toString());
		}

		for (final IShadowrunQualityNote quality : getTraitNotes()) {
			oResult.append(", Quality=" + quality.getBriefDesc());
		}

		for (final IShadowrunItem item : getInventory()) {
			oResult.append(", Item=" + item.toString());
		}

		return oResult.toString();
	}
}
