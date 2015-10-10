package games.rolePlayingGames.shadowrun.tracking.trackables.impl.character;

import games.rolePlayingGames.shadowrun.tracking.notes.impl.DeviceMatrixDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.StatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.notes.quality.IShadowrunQualityNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.IShadowrunItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.hacking.IPlayerHacker;
import games.rolePlayingGames.shadowrun.util.ShadowrunCommonUtils;

import java.util.ArrayList;

/**
 * A player-controlled decker/rigger. Not a technomancer.
 * 
 * @author Andrew
 */
public class PlayerHacker extends PlayerCharacter implements IPlayerHacker {

	/**
	 * Rating of cyberdeck/rig.
	 */
	private final int myRating;

	/**
	 * Matrix damage notes.
	 */
	private final ArrayList<DeviceMatrixDamageNote> myMatrixDamage = new ArrayList<DeviceMatrixDamageNote>();

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
	 * @param iRating
	 *            rating of cyberdeck/rig.
	 */
	public PlayerHacker(final String iName, final int iEssence,
			final int iBody, final int iWillpower, final int iSpecial,
			final ArrayList<StatusEffectNote> iStatusEffects,
			final ArrayList<IShadowrunItem> iInventory,
			final ArrayList<IShadowrunQualityNote> iQualities, final int iCon,
			final int iConResist, final int iPhysicalLimit, final int iRating) {
		super(iName, iEssence, iBody, iWillpower, iSpecial, iStatusEffects,
				iInventory, iQualities, iCon, iConResist, iPhysicalLimit);

		myRating = iRating;
	}

	@Override
	public int getMaximumMatrixHealth() {
		return ShadowrunCommonUtils.getMaximumHealth(getRating());
	}

	@Override
	public int getTotalMatrixDamage() {
		int oTotalDamage = 0;

		for (final DeviceMatrixDamageNote damageNote : getMatrixDamageNotes()) {
			final int currentDamage = damageNote.getDamage();
			final int currentHealed = damageNote.getHealed();

			oTotalDamage += Math.max(0, (currentDamage - currentHealed));
		}

		return oTotalDamage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<DeviceMatrixDamageNote> getMatrixDamageNotes() {
		return (ArrayList<DeviceMatrixDamageNote>) myMatrixDamage.clone();
	}

	@Override
	public void removeMatrixDamageNote(final DeviceMatrixDamageNote iDamageNote) {
		if (iDamageNote != null) {
			myMatrixDamage.remove(iDamageNote);
		}
	}

	@Override
	public void addDamageNote(final DeviceMatrixDamageNote iDamageNote) {
		if (iDamageNote != null) {
			myMatrixDamage.add(iDamageNote);
		}
	}

	@Override
	public int getRating() {
		return myRating;
	}
}
