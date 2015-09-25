package games.rolePlayingGames.shadowrun.dice;

import games.rolePlayingGames.dice.CommonRoller;
import games.rolePlayingGames.dice.DieType;

import java.util.ArrayList;
import java.util.List;

/**
 * Roll dice for Shadowrun.
 * 
 * @author Andrew
 *
 */
public class ShadowrunRoller {

	/**
	 * Roll a number of dice with a number of sides.
	 * 
	 * @param iDiceCount
	 *            the number of dice.
	 * @param iEdge
	 *            true if edge rules are used, false otherwise.
	 * @return the resulting rolls.
	 */
	public static List<Integer> rollDice(final int iDiceCount,
			final boolean iEdge) {
		final List<Integer> oRolls = new ArrayList<Integer>();

		int totalDice = iDiceCount;

		for (int dieNumber = 0; dieNumber < totalDice; dieNumber++) {
			final Integer roll = CommonRoller.rollDie(DieType.D6.getValue());
			oRolls.add(roll);

			if ((iEdge) && (roll.intValue() == 6)) {
				System.out.println("Exploding 6!");
				totalDice++;
			}
		}

		if (totalDice == iDiceCount) {
			System.out.println("Rolled [" + oRolls.toString() + "] with ["
					+ iDiceCount + "] dice.");
		} else {
			System.out.println("Rolled [" + oRolls.toString()
					+ "], originally with [" + iDiceCount
					+ "] dice but increased to [" + totalDice + "] with edge.");
		}
		return oRolls;
	}

}
