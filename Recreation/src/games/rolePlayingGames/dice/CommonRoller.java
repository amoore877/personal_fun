package games.rolePlayingGames.dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Common functionality for rolling dice.
 * 
 * @author Andrew
 *
 */
public class CommonRoller {

	/**
	 * The random to use for rolling.
	 */
	private static final Random RANDOM = new Random();

	/**
	 * Roll a die with a number of sides.
	 * 
	 * @param iSides
	 *            the number of sides.
	 * @return the resulting roll.
	 */
	public static Integer rollDie(int iSides) {

		Integer oRoll = RANDOM.nextInt(iSides) + 1;

		System.out.println("Rolled a [" + oRoll + "] on a die with [" + iSides
				+ "] sides.");

		return oRoll;
	}

	/**
	 * Roll a number of dice with a number of sides.
	 * 
	 * @param iDiceCount
	 *            the number of dice.
	 * @param iSides
	 *            the number of sides on each die.
	 * @return the resulting rolls.
	 */
	public static List<Integer> rollDice(int iDiceCount, int iSides) {
		List<Integer> oRolls = new ArrayList<Integer>();

		for (int dieNumber = 0; dieNumber < iDiceCount; dieNumber++) {
			oRolls.add(rollDie(iSides));
		}

		System.out.println("Rolled [" + oRolls.toString() + "] with ["
				+ iDiceCount + "] dice, each with [" + iSides + "] sides.");

		return oRolls;
	}

}
