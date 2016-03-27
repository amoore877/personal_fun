package games.rolePlayingGames.dnd.dice;

import games.rolePlayingGames.dice.DieType;
import games.rolePlayingGames.dice.InitiativeRoller;

/**
 * Rolls initiative for DND based on given modifier, using standard d20.
 * 
 * @author Andrew
 */
public class DNDInitiativeRoller extends InitiativeRoller {

	/**
	 * Initiative modifier.
	 */
	private final int myModifier;

	/**
	 * Constructor.
	 * 
	 * @param iModifier
	 *            modifier to use for all initiatives with this roller.
	 */
	public DNDInitiativeRoller(final int iModifier) {
		myModifier = iModifier;
	}

	@Override
	public int rollInitiative() {
		return DNDRoller.rollDice(DieType.D20, 1).get(0) + myModifier;
	}
}
