package games.setting.ambiance.music;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BackgroundMusic {

	/**
	 * Stop music.
	 */
	private static final String STOP = "stop";

	/**
	 * Pause music.
	 */
	private static final String PAUSE = "pause";

	/**
	 * Fade out music.
	 */
	private static final String FADE = "fade";

	/**
	 * Increase music intensity.
	 */
	private static final String INCREASE_INTENSITY = "++";

	/**
	 * Decrease music intensity.
	 */
	private static final String DECREASE_INTENSITY = "--";

	/**
	 * Change music.
	 */
	private static final String CHANGE = "change";

	/**
	 * Exit.
	 */
	private static final String EXIT_0 = "0";

	/**
	 * Exit string.
	 */
	private static final String EXIT = "exit";

	/**
	 * Main.
	 * 
	 * @param args
	 *            none accepted.
	 */
	public static void main(final String[] args) {
		new BackgroundMusic().start();
	}

	/**
	 * The actual program.
	 */
	public void start() {
		System.out.println("Program started.");

		boolean runningMain = true;
		final BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		// TODO running music object/thread/tracking

		while (runningMain) {
			try {
				// TODO options (dynamic)
				System.out.println("TODO (0 to exit):");

				final String mainOptionString = reader.readLine();

				if (mainOptionString.equalsIgnoreCase(STOP)) {
					// TODO stop music

				} else if (mainOptionString.equalsIgnoreCase(PAUSE)) {
					// TODO pause music

				} else if (mainOptionString.equalsIgnoreCase(FADE)) {
					// TODO fade music out

				} else if (mainOptionString
						.equalsIgnoreCase(INCREASE_INTENSITY)) {
					// TODO increase music intensity (not always applicable)

				} else if (mainOptionString
						.equalsIgnoreCase(DECREASE_INTENSITY)) {
					// TODO decrease music intensity (not always applicable)

				} else if (mainOptionString.equalsIgnoreCase(CHANGE)) {
					// TODO musical options
					System.out.println("TODO (0 to exit):");

					final int musicOptionInt = Integer.parseInt(reader
							.readLine());
					// TODO music selection
					// TODO --this is current music; do nothing
					// TODO --fade into new music

					// TODO exit (changed mind)

					// TODO unknown option
				} else if (mainOptionString.equalsIgnoreCase(EXIT)
						|| mainOptionString.equalsIgnoreCase(EXIT_0)) {
					System.out.println("User chose to exit program.");
					runningMain = false;
				} else {
					System.out.println("Unknown option: [" + mainOptionString
							+ "].");
				}

			} catch (final IOException iException) {
				System.out.println("IO Error");
				iException.printStackTrace();
				runningMain = false;
			} catch (final NumberFormatException iException) {
				System.out
						.println("Number Format Exception. Expected an integer and a non-integer was given.");
			}
		}

		System.out.println("Exiting program.");
	}
}
