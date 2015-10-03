package games.rolePlayingGames.shadowrun.dice;

import java.util.List;

/**
 * Roll result for Shadowrun.
 * 
 * @author Andrew
 */
public final class ShadowrunRollResult {

	/**
	 * Sixes rolled.
	 */
	private final int sixes;

	/**
	 * Fives rolled.
	 */
	private final int fives;

	/**
	 * Ones rolled.
	 */
	private final int ones;

	/**
	 * Total dice rolled.
	 */
	private final int totalDice;

	/**
	 * Minimum ones for glitch.
	 */
	private final int glitchMinimum;

	/**
	 * List of rolled dice.
	 */
	private final List<Integer> rolledDice;

	/**
	 * Constructor.
	 * 
	 * @param iRolledDice
	 *            list of rolled dice.
	 */
	public ShadowrunRollResult(final List<Integer> iRolledDice) {
		rolledDice = iRolledDice;

		totalDice = rolledDice.size();

		// need at least half dice as 1 to be a glitch
		glitchMinimum = (totalDice / 2) + (totalDice % 2);

		int sixesTemp = 0;
		int fivesTemp = 0;
		int onesTemp = 0;
		for (final Integer rolledResult : rolledDice) {
			switch (rolledResult) {
			case 6:
				sixesTemp++;
				break;
			case 5:
				fivesTemp++;
				break;
			case 1:
				onesTemp++;
				break;
			}
		}

		sixes = sixesTemp;
		fives = fivesTemp;
		ones = onesTemp;
	}

	/**
	 * @return number of sixes rolled.
	 */
	public int getSixes() {
		return sixes;
	}

	/**
	 * @return number of gives rolled.
	 */
	public int getFives() {
		return fives;
	}

	/**
	 * @return number of ones rolled.
	 */
	public int getOnes() {
		return ones;
	}

	/**
	 * @return total number of dice rolled.
	 */
	public int getTotalDice() {
		return totalDice;
	}

	/**
	 * @return minimum ones to glitch.
	 */
	public int getGlitchMinimum() {
		return glitchMinimum;
	}

	/**
	 * @return string list of rolled dice.
	 */
	@Override
	public String toString() {
		return rolledDice.toString();
	}

	/**
	 * @return number of fives and sixes.
	 */
	public int getHits() {
		return getFives() + getSixes();
	}

	/**
	 * @return true if there are at least as many ones as the glitch minimum.
	 */
	public boolean isGlitch() {
		return (ones >= glitchMinimum);
	}

	/**
	 * @return true if a glitch and no hits.
	 */
	public boolean isCriticalGlitch() {
		return isGlitch() && (getHits() == 0);
	}
}
