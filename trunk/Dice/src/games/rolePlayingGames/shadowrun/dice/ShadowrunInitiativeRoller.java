package games.rolePlayingGames.shadowrun.dice;

import games.rolePlayingGames.dice.InitiativeRoller;

/**
 * Rolls initiative for Shadowrun based on number of d6's and base initiative.
 * 
 * @author Andrew
 */
public class ShadowrunInitiativeRoller extends InitiativeRoller {

	/**
	 * Base initiative.
	 */
	private final int myBaseInitiative;

	/**
	 * Number of dice to roll.
	 */
	private final int myNumDice;

	/**
	 * Constructor.
	 * 
	 * @param iBaseInitiative
	 *            base initiative. Reaction + Intuition.
	 * @param iNumDice
	 *            number of initiative dice.
	 */
	public ShadowrunInitiativeRoller(final int iBaseInitiative,
			final int iNumDice) {
		myBaseInitiative = iBaseInitiative;
		myNumDice = iNumDice;
	}

	@Override
	public int rollInitiative() {
		return new ShadowrunRollResult(ShadowrunRoller.rollDice(myNumDice,
				false)).getSum() + myBaseInitiative;
	}

}
