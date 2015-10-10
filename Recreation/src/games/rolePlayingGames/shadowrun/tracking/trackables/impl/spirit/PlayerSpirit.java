package games.rolePlayingGames.shadowrun.tracking.trackables.impl.spirit;

import games.rolePlayingGames.shadowrun.tracking.notes.damage.spirit.AbstractSpiritDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.StatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.notes.quality.IShadowrunQualityNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.IShadowrunItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.living.AbstractSpirit;
import games.rolePlayingGames.shadowrun.tracking.trackables.living.IPlayerLivingBeing;

import java.util.ArrayList;

/**
 * A player-controlled spirit.
 * 
 * @author Andrew
 */
public final class PlayerSpirit extends AbstractSpirit implements
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
	 * @param iServices
	 *            services.
	 * @param iCon
	 *            total con score.
	 * @param iConResist
	 *            con resist.
	 * @param iPhysicalLimit
	 *            physical limit.
	 */
	public PlayerSpirit(final String iName, final int iEssence,
			final int iBody, final int iWillpower, final int iSpecial,
			final ArrayList<StatusEffectNote> iStatusEffects,
			final ArrayList<IShadowrunItem> iInventory,
			final ArrayList<IShadowrunQualityNote> iQualities,
			final int iServices, final int iCon, final int iConResist,
			final int iPhysicalLimit) {
		super(iName, iEssence, iBody, iWillpower, iSpecial, iStatusEffects,
				iInventory, iQualities, iServices);

		myCon = iCon;
		myConResist = iConResist;
		myPhysicalLimit = iPhysicalLimit;
	}

	@Override
	public int getCon() {
		return myCon;
	}

	@Override
	public int getConResist() {
		return myConResist;
	}

	@Override
	public int getPhysicalLimit() {
		return myPhysicalLimit;
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder();

		oResult.append("Name=" + getName());

		for (final AbstractSpiritDamageNote damageNote : getDamageNotes()) {
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
