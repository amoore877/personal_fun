package games.rolePlayingGames.shadowrun.util;

import games.rolePlayingGames.shadowrun.dice.ShadowrunRollResult;
import games.rolePlayingGames.shadowrun.dice.ShadowrunRoller;

public final class ShadowrunCommonUtils {

	private ShadowrunCommonUtils() {
	}

	/**
	 * Compute maximum health based on given attribute (Body for physical,
	 * Willpower for Stun, Device Rating for Matrix).
	 * 
	 * Computation is always (ceiling(Att / 2) + 8).
	 * 
	 * @param iAtt
	 *            attribute to compute max health off of.
	 * @return related max health.
	 */
	public static int getMaximumHealth(final int iAtt) {
		return (int) (Math.ceil(((double) iAtt / 2)) + 8);
	}

	/**
	 * Compute mental limit.
	 * 
	 * Computation is ceiling(((2 * Logic) + Intuition + Willpower) /3).
	 * 
	 * @param iLogic
	 *            logic.
	 * @param iIntuition
	 *            intuition.
	 * @param iWillpower
	 *            willpower.
	 * @return mental limit.
	 */
	public static int getMentalLimit(final int iLogic, final int iIntuition,
			final int iWillpower) {
		return (int) Math
				.ceil((((double) iLogic * 2) + iIntuition + iWillpower) / 3);
	}

	/**
	 * Compute physical limit.
	 * 
	 * Computation is ceiling(((2 * Strength) + Body + Reaction) /3).
	 * 
	 * @param iStrength
	 *            strength.
	 * @param iBody
	 *            body.
	 * @param iReaction
	 *            reaction.
	 * @return physical limit.
	 */
	public static int getPhysicalLimit(final int iStrength, final int iBody,
			final int iReaction) {
		return (int) Math
				.ceil((((double) iStrength * 2) + iBody + iReaction) / 3);
	}

	/**
	 * Compute social limit.
	 * 
	 * Computation is ceiling(((2 * Charisma) + Willpower + Essence) /3).
	 * 
	 * @param iCharisma
	 *            charisma.
	 * @param iWillpower
	 *            willpower.
	 * @param iEssence
	 *            essence.
	 * @return social limit.
	 */
	public static int getSocialLimit(final int iCharisma, final int iWillpower,
			final int iEssence) {
		return (int) Math
				.ceil((((double) iCharisma * 2) + iWillpower + iEssence) / 3);
	}

	/**
	 * Roll regular meat initiative. Assume only 1d6.
	 * 
	 * @param iIntuition
	 *            intuition.
	 * @param iReaction
	 *            reaction.
	 * @return a rolled initiative amount.
	 */
	public static int rollInitiative(final int iIntuition, final int iReaction) {
		return rollInitiative(iIntuition, iReaction, 1);
	}

	/**
	 * Roll regular meat initiative.
	 * 
	 * @param iIntuition
	 *            intuition.
	 * @param iReaction
	 *            reaction.
	 * @param iDiceNum
	 *            number of dice to roll.
	 * @return a rolled initiative amount.
	 */
	public static int rollInitiative(final int iIntuition, final int iReaction,
			final int iDiceNum) {
		return iIntuition
				+ iReaction
				+ new ShadowrunRollResult(ShadowrunRoller.rollDice(iDiceNum,
						false)).getSum();
	}

	/**
	 * Roll AR initiative. Assume only 1d6.
	 * 
	 * @param iIntuition
	 *            intuition.
	 * @param iReaction
	 *            reaction.
	 * @return a rolled initiative amount.
	 */
	public static int rollARInitiative(final int iIntuition, final int iReaction) {
		return rollARInitiative(iIntuition, iReaction, 1);
	}

	/**
	 * Roll AR initiative.
	 * 
	 * @param iIntuition
	 *            intuition.
	 * @param iReaction
	 *            reaction.
	 * @param iDiceNum
	 *            number of dice to roll.
	 * @return a rolled initiative amount.
	 */
	public static int rollARInitiative(final int iIntuition,
			final int iReaction, final int iDiceNum) {
		return iIntuition
				+ iReaction
				+ new ShadowrunRollResult(ShadowrunRoller.rollDice(iDiceNum,
						false)).getSum();
	}

	/**
	 * Roll astral initiative. Assume 2d6.
	 * 
	 * @param iIntuition
	 *            intuition.
	 * @return a rolled initiative amount.
	 */
	public static int rollAstralInitiative(final int iIntuition) {
		return rollAstralInitiative(iIntuition, 2);
	}

	/**
	 * Roll astral initiative.
	 * 
	 * @param iIntuition
	 *            intuition.
	 * @param iDiceNum
	 *            number of dice to roll.
	 * @return a rolled initiative amount.
	 */
	public static int rollAstralInitiative(final int iIntuition,
			final int iDiceNum) {
		return (2 * iIntuition)
				+ new ShadowrunRollResult(ShadowrunRoller.rollDice(iDiceNum,
						false)).getSum();
	}

	/**
	 * Roll cold VR initiative. Assume 3d6.
	 * 
	 * @param iDataProcessing
	 *            data processing.
	 * @param iIntuition
	 *            intuition.
	 * @return a rolled initiative amount.
	 */
	public static int rollColdVRInitiative(final int iDataProcessing,
			final int iIntuition) {
		return rollColdVRInitiative(iDataProcessing, iIntuition, 3);
	}

	/**
	 * Roll cold VR initiative.
	 * 
	 * @param iDataProcessing
	 *            data processing.
	 * @param iIntuition
	 *            intuition.
	 * @param iDiceNum
	 *            number of dice to roll.
	 * @return a rolled initiative amount.
	 */
	public static int rollColdVRInitiative(final int iDataProcessing,
			final int iIntuition, final int iDiceNum) {
		return iDataProcessing
				+ iIntuition
				+ new ShadowrunRollResult(ShadowrunRoller.rollDice(iDiceNum,
						false)).getSum();
	}

	/**
	 * Roll hot VR initiative. Assume 4d6.
	 * 
	 * @param iDataProcessing
	 *            data processing or pilot.
	 * @param iIntuition
	 *            intuition or pilot.
	 * @return a rolled initiative amount.
	 */
	public static int rollHotVRInitiative(final int iDataProcessing,
			final int iIntuition) {
		return rollHotVRInitiative(iDataProcessing, iIntuition, 4);
	}

	/**
	 * Roll hot VR initiative.
	 * 
	 * @param iDataProcessing
	 *            data processing or pilot.
	 * @param iIntuition
	 *            intuition or pilot.
	 * @param iDiceNum
	 *            number of dice to roll.
	 * @return a rolled initiative amount.
	 */
	public static int rollHotVRInitiative(final int iDataProcessing,
			final int iIntuition, final int iDiceNum) {
		return iDataProcessing
				+ iIntuition
				+ new ShadowrunRollResult(ShadowrunRoller.rollDice(iDiceNum,
						false)).getSum();
	}
}
