package games.rolePlayingGames.shadowrun.dice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
	 *            arguments. For no args, start general program. For 1 arg, must
	 *            be the number of dice to roll, no edge. For 2 args, must be
	 *            number of dice to roll, and y or n for edge.
	 */
	public static void main(final String[] args) {
		if (args.length == 0) {
			// no args, run general program
			new RollForShadowrun().start();
		} else {
			// at least one arg
			try {
				// get dice count
				final int diceCount = Integer.parseInt(args[0]);

				boolean useEdge;
				if (args.length > 1) {
					// get edge argument
					if (args[1].equals("y")) {
						useEdge = true;
					} else {
						useEdge = false;
					}
				} else {
					useEdge = false;
				}

				printResults(
						new ShadowrunRollResult(ShadowrunRoller.rollDice(
								diceCount, useEdge)), false);
			} catch (final NumberFormatException iException) {
				iException.printStackTrace();
				printUsage();
			}
		}
	}

	/**
	 * Print usage.
	 */
	private static void printUsage() {
		System.out.println("Usage:\t[d] [y|n]\n"
				+ "No args\tRun program continuously.\n" + "d\tRoll d dice.\n"
				+ "y\tUse edge.\n" + "n\tDon't use edge.");
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

					printResults(
							new ShadowrunRollResult(ShadowrunRoller.rollDice(
									diceNum, edgeUsed)), true);

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

	/**
	 * Print results of given roll.
	 * 
	 * @param iRollResult
	 *            roll to print out.
	 * @param iVerbose
	 *            true for verbose output, false otherwise.
	 */
	private static void printResults(final ShadowrunRollResult iRollResult,
			final boolean iVerbose) {
		final int hits = iRollResult.getHits();

		if (iVerbose) {
			System.out.println("\n\n\n\n\n\n" + "Result ["
					+ iRollResult.toString() + "] \n" + "Dice Rolled: ["
					+ iRollResult.getTotalDice() + "] \n" + "Hits: [" + hits
					+ "] \n" + "Ones: [" + iRollResult.getOnes()
					+ "], Minimum to glitch: ["
					+ iRollResult.getGlitchMinimum() + "]");
		} else {
			System.out.println("Hits: [" + hits + "]");
		}
		if (iRollResult.isCriticalGlitch()) {
			System.out.println("Critical glitch!");
		} else if (iRollResult.isGlitch()) {
			System.out.println("Glitch!");
		}
	}
}
