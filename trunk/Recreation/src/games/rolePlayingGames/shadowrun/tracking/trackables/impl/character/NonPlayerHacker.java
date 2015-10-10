package games.rolePlayingGames.shadowrun.tracking.trackables.impl.character;

import games.rolePlayingGames.shadowrun.tracking.notes.ability.AbstractAbility;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.DeviceMatrixDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.StatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.notes.quality.IShadowrunQualityNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.IShadowrunItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.hacking.INonPlayerHacker;
import games.rolePlayingGames.shadowrun.util.ShadowrunCommonUtils;

import java.util.ArrayList;

/**
 * NPC hacker/rigger. Not a technomancer.
 * 
 * @author Andrew
 */
public final class NonPlayerHacker extends NonPlayerCharacter implements
		INonPlayerHacker {
	/**
	 * Rating of cyberdeck/rig.
	 */
	private final int myRating;

	/**
	 * Matrix damage notes.
	 */
	private final ArrayList<DeviceMatrixDamageNote> myMatrixDamage = new ArrayList<DeviceMatrixDamageNote>();

	/**
	 * Attack.
	 */
	private final int myAttack;

	/**
	 * Sleaze.
	 */
	private final int mySleaze;

	/**
	 * Data processing.
	 */
	private final int myDataProcessing;

	/**
	 * Firewall.
	 */
	private final int myFirewall;

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
	 * @param iAgility
	 *            agility.
	 * @param iReaction
	 *            reaction.
	 * @param iStrength
	 *            strength.
	 * @param iLogic
	 *            logic.
	 * @param iIntuition
	 *            intuition.
	 * @param iCharisma
	 *            charisma.
	 * @param iInitDice
	 *            initiative dice.
	 * @param iAbilities
	 *            abilities.
	 * @param iRating
	 *            rating of cyberdeck/rig.
	 * @param iAttack
	 *            attack.
	 * @param iSleaze
	 *            sleaze.
	 * @param iDataProcessing
	 *            data processing.
	 * @param iFirewall
	 *            firewall.
	 */
	public NonPlayerHacker(final String iName, final int iEssence,
			final int iBody, final int iWillpower, final int iSpecial,
			final ArrayList<StatusEffectNote> iStatusEffects,
			final ArrayList<IShadowrunItem> iInventory,
			final ArrayList<IShadowrunQualityNote> iQualities,
			final int iAgility, final int iReaction, final int iStrength,
			final int iLogic, final int iIntuition, final int iCharisma,
			final int iInitDice, final ArrayList<AbstractAbility> iAbilities,
			final int iRating, final int iAttack, final int iSleaze,
			final int iDataProcessing, final int iFirewall) {
		super(iName, iEssence, iBody, iWillpower, iSpecial, iStatusEffects,
				iInventory, iQualities, iAgility, iReaction, iStrength, iLogic,
				iIntuition, iCharisma, iInitDice, iAbilities);

		myRating = iRating;
		myAttack = iAttack;
		mySleaze = iSleaze;
		myDataProcessing = iDataProcessing;
		myFirewall = iFirewall;
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

	@Override
	public int getAttack() {
		return myAttack;
	}

	@Override
	public int getSleaze() {
		return mySleaze;
	}

	@Override
	public int getFirewall() {
		return myFirewall;
	}

	@Override
	public int getDataProcessing() {
		return myDataProcessing;
	}
}
