package ruu.project.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Random;

import ruu.project.datamodels.Node;
import ruu.project.datamodels.ParentNode;
import ruu.project.datamodels.impl.AppNode;
import ruu.project.datamodels.impl.CPUNode;
import ruu.project.datamodels.impl.IPSNode;

/**
 * Belief Network application class.
 * 
 * @author Andrew
 *
 */
public final class BeliefNetworkMobileIPS {

	/**
	 * Default file to look for.
	 */
	private static final String DEFAULT_FILE = "cdf_ips_range_given_CPU.txt";

	/**
	 * Exit string.
	 */
	private static final String EXIT_STRING = "exit";

	/**
	 * Random, for getting some value in a distribution of IPS nodes.
	 */
	private static final Random RANDOM = new Random();

	/**
	 * High CPU probabilities.
	 */
	/**
	 * Probability of high CPU given Angry Birds.
	 */
	private static final double P_HI_CPU_GIVEN_ANGRY_BIRDS = 0.99;

	/**
	 * Probability of high CPU given Music.
	 */
	private static final double P_HI_CPU_GIVEN_MUSIC = 0.01;

	/**
	 * Probability of high CPU given Manager.
	 */
	private static final double P_HI_CPU_GIVEN_MGR = 0.66;

	/**
	 * Med CPU probabilities.
	 */
	/**
	 * Probability of med CPU given Angry Birds.
	 */
	private static final double P_MED_CPU_GIVEN_ANGRY_BIRDS = 0.01;

	/**
	 * Probability of med CPU given Music.
	 */
	private static final double P_MED_CPU_GIVEN_MUSIC = 0.01;

	/**
	 * Probability of med CPU given Manager.
	 */
	private static final double P_MED_CPU_GIVEN_MGR = 0.17;

	/**
	 * Low CPU probabilities.
	 */
	/**
	 * Probability of Low CPU given Angry Birds.
	 */
	private static final double P_LO_CPU_GIVEN_ANGRY_BIRDS = 0.01;

	/**
	 * Probability of Low CPU given Music.
	 */
	private static final double P_LO_CPU_GIVEN_MUSIC = 0.99;

	/**
	 * Probability of Low CPU given Manager.
	 */
	private static final double P_LO_CPU_GIVEN_MGR = 0.17;

	/**
	 * Callable nodes.
	 */
	/**
	 * App Nodes.
	 */
	/**
	 * Angry Birds node.
	 */
	protected final AppNode myAngryBirdsNode;

	/**
	 * Music node.
	 */
	protected final AppNode myMusicNode;

	/**
	 * Manager node.
	 */
	protected final AppNode myMgrNode;

	/**
	 * CPU Nodes for direct examination (not as children).
	 */
	/**
	 * High CPU node.
	 */
	protected final CPUNode myHighCPUNode;

	/**
	 * Med CPU node.
	 */
	protected final CPUNode myMedCPUNode;

	/**
	 * Low CPU node.
	 */
	protected final CPUNode myLowCPUNode;

	/**
	 * Constructor.
	 * 
	 * @throws IOException
	 *             on IO exception.
	 * @throws NumberFormatException
	 *             on number exception.
	 */
	public BeliefNetworkMobileIPS(final String iFileToUse) throws IOException,
			NumberFormatException {
		// CPU children
		final ArrayList<IPSNode> highCPUChildren = new ArrayList<IPSNode>();
		final ArrayList<IPSNode> medCPUChildren = new ArrayList<IPSNode>();
		final ArrayList<IPSNode> lowCPUChildren = new ArrayList<IPSNode>();

		// System.out.println("Attempting to read file [" + iFileToUse + "]");
		final FileReader fileReader = new FileReader(new File(iFileToUse));

		final BufferedReader bufferedReader = new BufferedReader(fileReader);

		// throw out first line
		bufferedReader.readLine();
		// start at second line
		String line = bufferedReader.readLine();
		// parse for min IPS start
		String[] splitLine = line.split(",");

		// track bottom of IPS range
		double minOfCurrIPS = Double.valueOf(splitLine[0]);

		while (line != null) {
			// System.out.println("Reading line [" + line + "]");

			// split line by commas
			splitLine = line.split(",");

			// get max of the current IPS range
			final double maxOfCurrIPS = Double.valueOf(splitLine[0]);

			// create child for low CPU
			lowCPUChildren.add(new IPSNode(Double.valueOf(splitLine[1]),
					minOfCurrIPS, maxOfCurrIPS));
			// create child for med CPU
			medCPUChildren.add(new IPSNode(Double.valueOf(splitLine[2]),
					minOfCurrIPS, maxOfCurrIPS));
			// create child for high CPU
			highCPUChildren.add(new IPSNode(Double.valueOf(splitLine[3]),
					minOfCurrIPS, maxOfCurrIPS));

			// set new minimum to current maximum
			minOfCurrIPS = maxOfCurrIPS;

			// next line
			line = bufferedReader.readLine();
		}

		/**
		 * Construct CPU nodes for examination (not as children)
		 */
		myLowCPUNode = new CPUNode(lowCPUChildren, 0);
		myMedCPUNode = new CPUNode(medCPUChildren, 0);
		myHighCPUNode = new CPUNode(highCPUChildren, 0);

		/**
		 * Construct App nodes.
		 */
		/**
		 * Angry birds.
		 */
		final ArrayList<CPUNode> angryBirdsChildren = new ArrayList<CPUNode>();
		angryBirdsChildren.add(new CPUNode(lowCPUChildren,
				P_LO_CPU_GIVEN_ANGRY_BIRDS));
		angryBirdsChildren.add(new CPUNode(medCPUChildren,
				P_MED_CPU_GIVEN_ANGRY_BIRDS));
		angryBirdsChildren.add(new CPUNode(highCPUChildren,
				P_HI_CPU_GIVEN_ANGRY_BIRDS));
		myAngryBirdsNode = new AppNode(angryBirdsChildren, (double) 1
				/ (double) 3);

		/**
		 * Music.
		 */
		final ArrayList<CPUNode> musicChildren = new ArrayList<CPUNode>();
		musicChildren.add(new CPUNode(lowCPUChildren, P_LO_CPU_GIVEN_MUSIC));
		musicChildren.add(new CPUNode(medCPUChildren, P_MED_CPU_GIVEN_MUSIC));
		musicChildren.add(new CPUNode(highCPUChildren, P_HI_CPU_GIVEN_MUSIC));
		myMusicNode = new AppNode(musicChildren, (double) 1 / (double) 3);

		/**
		 * Manager.
		 */
		final ArrayList<CPUNode> mgrChildren = new ArrayList<CPUNode>();
		mgrChildren.add(new CPUNode(lowCPUChildren, P_LO_CPU_GIVEN_MGR));
		mgrChildren.add(new CPUNode(medCPUChildren, P_MED_CPU_GIVEN_MGR));
		mgrChildren.add(new CPUNode(highCPUChildren, P_HI_CPU_GIVEN_MGR));
		myMgrNode = new AppNode(mgrChildren, (double) 1 / (double) 3);

		bufferedReader.close();
	}

	/**
	 * The actual program.
	 */
	public void start() {
		System.out.println("Program started. Type exit to quit.");

		boolean running = true;
		final BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		while (running) {

			try {
				System.out.println("\n\nIs CPU known?\n" + "0 for unknown\n"
						+ "1 for Low CPU\n" + "2 for Med CPU\n"
						+ "3 for High CPU\n");

				final String cpuStateString = reader.readLine();

				if (!cpuStateString.equalsIgnoreCase(EXIT_STRING)) {
					// evidence to use
					ParentNode<? extends Node, IPSNode> evidenceNode = null;

					if (!cpuStateString.equals("0")) {
						// known CPU state
						if (cpuStateString.equals("1")) {
							// low CPU
							System.out.println("Low CPU is known");
							evidenceNode = myLowCPUNode;
						} else if (cpuStateString.equals("2")) {
							// med CPU
							System.out.println("Medium CPU is known");
							evidenceNode = myMedCPUNode;
						} else if (cpuStateString.equals("3")) {
							// high CPU
							System.out.println("High CPU is known");
							evidenceNode = myHighCPUNode;
						} else {
							System.out
									.println("Invalid command for CPU state: ["
											+ cpuStateString + "]");
						}
					} else {
						// unknown CPU state; app must be known
						System.out.println("CPU State is unknown.\n"
								+ "Which App is known?\n"
								+ "1 for Angry Birds\n" + "2 for Music\n"
								+ "3 for Manager\n");

						final String appString = reader.readLine();

						if (appString.equals("1")) {
							// angry birds
							System.out.println("Angry Birds is known");
							evidenceNode = myAngryBirdsNode;
						} else if (appString.equals("2")) {
							// music
							System.out.println("Music is known");
							evidenceNode = myMusicNode;
						} else if (appString.equals("3")) {
							// manager
							System.out.println("Manager is known");
							evidenceNode = myMgrNode;
						} else {
							System.out
									.println("Invalid command for App choice: ["
											+ appString + "]");
						}
					}

					// perform calculations and display results/data
					if (evidenceNode != null) {
						printResults(
								evidenceNode.calculateEndNodeProbabilities(),
								true);
					} else {
						System.out
								.println("Invalid command was given. Null evidence node.");
					}
				} else {
					System.out.println("User chose to exit program.");
					running = false;
				}
			} catch (final IOException iException) {
				System.out.println("IO Error");
				iException.printStackTrace();
				running = false;
			}
		}

		System.out.println("Exiting program.");
	}

	/**
	 * Print results of walking the probability tables for some evidence.
	 * 
	 * @param iIPSProbabilitiesMap
	 *            mapping of IPS ranges to their probabilities.
	 * @param iTrialMode
	 *            true if should run a trial, false to get single result with no
	 *            other output.
	 */
	private static void printResults(
			final LinkedHashMap<IPSNode, Double> iIPSProbabilitiesMap,
			final boolean iTrialMode) {
		if (iTrialMode) {
			System.out.println("\n\n\n\n\n\nResults:");
		}

		/**
		 * Loop through probabilities
		 */
		// create string showing IPS range probabilities
		final StringBuilder ipsRangeProbabilities = new StringBuilder(
				"\n\nIPS Range probabilities:\n");
		// get total probability weights (would likely equal 1, but no
		// guarantee
		// due to rounding)
		double totalWeights = 0;

		for (final Entry<IPSNode, Double> entry : iIPSProbabilitiesMap
				.entrySet()) {
			ipsRangeProbabilities.append(entry.getKey().toString() + " : "
					+ entry.getValue() + "\n");
			totalWeights = totalWeights + entry.getValue();
		}

		if (iTrialMode) {
			System.out.println(ipsRangeProbabilities.toString());
		}

		// generate trial data (or single response)
		printTrial(iIPSProbabilitiesMap, totalWeights, iTrialMode);
	}

	/**
	 * Print trial data from the probabilities map.
	 * 
	 * @param iIPSProbabilitiesMap
	 *            mapping of IPS ranges to their probabilities.
	 * @param iTotalWeights
	 *            total probability of all IPS ranges. Would expect to be 1, but
	 *            no guarantee due to rounding.
	 * @param iTrialMode
	 *            true if should run a trial, false to get single result with no
	 *            other output.
	 */
	private static void printTrial(
			final LinkedHashMap<IPSNode, Double> iIPSProbabilitiesMap,
			final double iTotalWeights, final boolean iTrialMode) {
		// full trial
		if (iTrialMode) {
			System.out.println("\n\n\n\nTrial Run Data:\n");
		}

		final StringBuilder trialData = new StringBuilder("");

		// get 100 ping responses
		for (int i = 0; i < 100; i++) {
			double distributionChoice = iTotalWeights * RANDOM.nextDouble();

			// loop through all IPSNodes until distribution choice hits 0 or
			// lower
			for (final Entry<IPSNode, Double> entry : iIPSProbabilitiesMap
					.entrySet()) {
				distributionChoice = distributionChoice - entry.getValue();

				if (distributionChoice <= 0) {
					// found randomly chosen node to use for this ping
					// response
					if (iTrialMode) {
						trialData.append("Response " + i + ": ");
					}
					trialData.append(entry.getKey().getRandomResponseTime()
							+ "\n");
					break;
				}
			}
		}

		System.out.println(trialData.toString());
	}

	/**
	 * Main.
	 * 
	 * @param iArgs
	 *            [fileToUse.txt] [AB|MUS|MGR|HI|MED|LO]
	 */
	public static void main(final String[] iArgs) {
		String fileToUse = DEFAULT_FILE;
		String evidence = null;

		if (iArgs.length != 0) {
			// non-empty arguments
			final String firstArgument = iArgs[0];

			int evidenceIndex;
			if (firstArgument.endsWith(".txt")) {
				// first argument is the file to use
				fileToUse = firstArgument;

				// check for second argument for evidence
				evidenceIndex = 1;
			} else {
				// first argument is not the file to use; check if it is
				// evidence
				evidenceIndex = 0;
			}

			if (iArgs.length > evidenceIndex) {
				evidence = iArgs[evidenceIndex];
			}
		}

		// System.out.println("Using file: [" + fileToUse + "]");
		// System.out.println("Evidence: [" + evidence + "]");

		BeliefNetworkMobileIPS beliefNetwork = null;
		try {
			beliefNetwork = new BeliefNetworkMobileIPS(fileToUse);
		} catch (final IOException iException) {
			System.err.println("IO Exception: " + iException.getMessage());
		} catch (final NumberFormatException iException) {
			System.err.println("Number exception: " + iException.getMessage());
		}

		if (beliefNetwork != null) {
			// no evidence; constant looping
			if (evidence == null) {
				beliefNetwork.start();
			} else {
				// evidence present; run once
				ParentNode<? extends Node, IPSNode> evidenceNode = null;

				if (evidence.equals("LO")) {
					// low CPU
					evidenceNode = beliefNetwork.myLowCPUNode;
				} else if (evidence.equals("MED")) {
					// med CPU
					evidenceNode = beliefNetwork.myMedCPUNode;
				} else if (evidence.equals("HI")) {
					// high CPU
					evidenceNode = beliefNetwork.myHighCPUNode;
				} else if (evidence.equals("AB")) {
					// angry birds
					evidenceNode = beliefNetwork.myAngryBirdsNode;
				} else if (evidence.equals("MUS")) {
					// music
					evidenceNode = beliefNetwork.myMusicNode;
				} else if (evidence.equals("MGR")) {
					// manager
					evidenceNode = beliefNetwork.myMgrNode;
				}

				if (evidenceNode != null) {
					printResults(evidenceNode.calculateEndNodeProbabilities(),
							false);
				} else {
					System.out
							.println("Invalid command was given. Null evidence node.");

					System.out
							.println("Belief Network for IPS Generation\n"
									+ "[fileToUse.txt] [AB|MUS|MGR|HI|MED|LO]\n\n"
									+ "fileToUse.txt\n"
									+ "Optional. File to use for IPS ranges and probabilities based on CPU."
									+ " Will use the default data if unspecified."
									+ " Must end in .txt and be in expected format.\n"
									+ "AB|MUS|MGR|HI|MED|LO \t\t"
									+ "Optional. Specifies evidence set (single choice)."
									+ "AB is for Angry Birds."
									+ " MUS is for Music."
									+ " MGR is for app manager."
									+ " HI, MED, and LO specify CPU states."
									+ " Will open in continuous trials mode if unspecificed.");
				}
			}
		}
	}
}
