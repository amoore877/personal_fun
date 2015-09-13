package games.rolePlayingGames.shadowrun.dice;

import games.rolePlayingGames.dice.CommonRoller;

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
	public static List<Integer> rollDice(int iDiceCount, boolean iEdge) {
		List<Integer> oRolls = new ArrayList<Integer>();

		int totalDice = iDiceCount;

		for (int dieNumber = 0; dieNumber < totalDice; dieNumber++) {
			Integer roll = CommonRoller.rollDie(6);
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
