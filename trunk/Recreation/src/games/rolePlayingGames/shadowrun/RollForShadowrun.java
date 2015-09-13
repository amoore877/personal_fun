package games.rolePlayingGames.shadowrun;

import games.rolePlayingGames.shadowrun.dice.ShadowrunRoller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Shadowrun dice rolling program.
 * 
 * @author Andrew
 *
 */
public class RollForShadowrun {

	/**
	 * Main.
	 * 
	 * @param args
	 *            arguments (none parsed).
	 */
	public static void main(final String[] args) {
		new RollForShadowrun().start();
	}

	/**
	 * The actual program.
	 */
	public void start() {
		System.out.println("Program started.");

		boolean running = true;
		final BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		while (running) {

			try {
				System.out.println("Enter number of dice (0 to exit):");

				final String diceNumString = reader.readLine();

				final int diceNum = Integer.parseInt(diceNumString);

				if (diceNum != 0) {

					System.out.println("Edge being used (y/n)?");

					final String edgeUsedString = reader.readLine();

					boolean edgeUsed;

					if (edgeUsedString.equalsIgnoreCase("y")
							|| edgeUsedString.equalsIgnoreCase("yes")
							|| edgeUsedString.equals("1")) {
						edgeUsed = true;
					} else {
						edgeUsed = false;
					}

					final List<Integer> rolledResults = ShadowrunRoller
							.rollDice(diceNum, edgeUsed);

					int sixes = 0;
					int fives = 0;
					int ones = 0;
					final int glitchMinimum = rolledResults.size() / 2;
					for (final Integer rolledResult : rolledResults) {
						switch (rolledResult) {
						case 6:
							sixes++;
							break;
						case 5:
							fives++;
							break;
						case 1:
							ones++;
							break;
						}
					}

					final int hits = sixes + fives;

					System.out.println("\n\n\n\n\n\n" + "Result ["
							+ rolledResults.toString() + "] \n"
							+ "Dice Rolled: [" + rolledResults.size() + "] \n"
							+ "Hits: [" + hits + "] \n" + "Ones: [" + ones
							+ "], Minimum to glitch: [" + glitchMinimum + "]");
					if (ones >= glitchMinimum) {
						if (hits == 0) {
							System.out.println("Critical glitch!");
						} else {
							System.out.println("Glitch!");
						}
					}

					System.out.println("\n\n\n\n\n\n");
				} else {
					System.out.println("User chose to exit program.");
					running = false;
				}
			} catch (final IOException iException) {
				System.out.println("IO Error");
				iException.printStackTrace();
				running = false;
			} catch (final NumberFormatException iException) {
				System.out
						.println("Number Format Exception. Expected an integer and a non-integer was given.");
			}
		}

		System.out.println("Exiting program.");
	}
}
