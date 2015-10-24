package games.rolePlayingGames.dnd.dice;

import games.rolePlayingGames.dice.DieType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * DND dice rolling program.
 * 
 * @author Andrew
 *
 */
public class RollForDND {

	/**
	 * Main.
	 * 
	 * @param args
	 *            arguments (none parsed).
	 */
	public static void main(final String[] args) {
		new RollForDND().start();
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

					System.out
							.println("Enter type of die (2, 4, 6, 8, 10, 12, 20, 99/100)?");

					final String dieTypeString = reader.readLine();

					final DieType dieTypeTemp = DieType.valueOf(Integer
							.parseInt(dieTypeString));

					final DieType dieType;
					if (dieTypeTemp.equals(DieType.D99)) {
						dieType = DieType.D100;
					} else {
						dieType = dieTypeTemp;
					}

					final List<Integer> rolledResults = DnDRoller.rollDice(
							dieType, diceNum);

					int total = 0;

					for (final Integer rolledResult : rolledResults) {
						total += rolledResult;
					}

					System.out.println("\n\n\n\n\n\n" + "Result ["
							+ rolledResults.toString() + "] \n"
							+ "Dice Rolled: [" + rolledResults.size() + "] \n"
							+ "Total: [" + total + "]");

					if (dieType.equals(DieType.D20)) {
						if (total == 1) {
							System.out.println("Critical fail!");
						} else if ((total == 20) && (diceNum == 1)) {
							System.out.println("Critical success!");
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
			} catch (final EnumConstantNotPresentException iException) {
				System.out
						.println("Enum Exception. Expected a valid die type.");
			}
		}

		System.out.println("Exiting program.");
	}
}
