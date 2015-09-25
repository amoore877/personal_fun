package games.rolePlayingGames.dnd.dice;

import games.rolePlayingGames.dice.CommonRoller;
import games.rolePlayingGames.dice.DieType;

import java.util.ArrayList;
import java.util.List;

/**
 * Roll dice for DND.
 * 
 * @author Andrew
 *
 */
public class DnDRoller {

	/**
	 * Roll a number of dice with a number of sides.
	 * 
	 * @param iDiceType
	 *            the type of dice.
	 * @param iDiceCount
	 *            the number of dice.
	 * @return the resulting rolls.
	 */
	public static List<Integer> rollDice(final DieType iDiceType,
			final int iDiceCount) {
		final List<Integer> oRolls = new ArrayList<Integer>();

		final int totalDice = iDiceCount;

		for (int dieNumber = 0; dieNumber < totalDice; dieNumber++) {
			final Integer roll = CommonRoller.rollDie(iDiceType.getValue());
			if (iDiceType.equals(DieType.D100)) {
				oRolls.add(roll - 1);
			} else {
				oRolls.add(roll);
			}
		}

		System.out.println("Rolled [" + oRolls.toString() + "] with ["
				+ iDiceCount + "] dice.");

		return oRolls;
	}

}
