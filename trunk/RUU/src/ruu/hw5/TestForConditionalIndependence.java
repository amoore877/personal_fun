package ruu.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestForConditionalIndependence {

	private static int[][] myAdjacencyMatrix;
	private static int myFirstNode;
	private static int mySecondNode;
	private static List<Integer> myConditioningSet;
	private static int mySize;
	private static final List<Path> PATHS = new ArrayList<Path>();

	public static void main(final String[] args) {
		System.out.println("Program started.");

		final BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		System.out.println("Size of the square matrix (greater than 2)?");

		try {
			final String sizeString = reader.readLine();

			mySize = Integer.parseInt(sizeString);

			if (mySize <= 2) {
				System.out.println("Inappropriate size of matrix: [" + mySize
						+ "].");
				System.exit(3);
			}

			System.out.println("Using matrix of size " + mySize + "x" + mySize
					+ ".");

			// assemble adjacency matrix
			myAdjacencyMatrix = new int[mySize][mySize];

			for (int row = 0; row < mySize; row++) {
				System.out
						.println("Children of node ["
								+ row
								+ "]?"
								+ "\nUse format 01101..."
								+ "\nMissing data will be considered 0 and extra data will be ignored.");

				final String childrenString = reader.readLine();
				final int numOfDeclaredChildren = childrenString.length();

				for (int col = 0; col < mySize; col++) {
					if ((numOfDeclaredChildren - 1) < col) {
						// missing data; default to 0
						System.out.println("Missing data; default to 0.");
						myAdjacencyMatrix[row][col] = 0;
					} else {
						try {
							final String childChar = childrenString.substring(
									col, col + 1);

							final int childValue = Integer.parseInt(childChar);

							if (childValue == 1) {
								// valid connection
								myAdjacencyMatrix[row][col] = 1;
							} else if (childValue == 0) {
								// valid disconnection
								myAdjacencyMatrix[row][col] = 0;
							} else {
								// invalid input
								System.err
										.println("Invalid input; default to 0.");
								myAdjacencyMatrix[row][col] = 0;
							}
						} catch (final StringIndexOutOfBoundsException iException) {
							// out of bounds; default to 0
							System.err.println("Out of bounds; default to 0.");
							myAdjacencyMatrix[row][col] = 0;
						} catch (final NumberFormatException iException) {
							// invalid input; default to 0
							System.err.println("Invalid input; default to 0.");
							myAdjacencyMatrix[row][col] = 0;
						}
					}
				}
			}

			// show adj matrix
			final StringBuilder adjMatrixString = new StringBuilder(
					"Adjacency Matrix: \n");
			for (int i = 0; i < mySize; i++) {
				adjMatrixString.append("[");

				for (int j = 0; j < mySize; j++) {
					adjMatrixString.append(" " + myAdjacencyMatrix[i][j] + " ");
				}

				adjMatrixString.append("]\n");
			}
			System.out.println(adjMatrixString.toString());

			// get input nodes
			System.out.println("First node to test? (0-" + (mySize - 1) + ").");

			final String firstNodeString = reader.readLine();

			myFirstNode = Integer.parseInt(firstNodeString);

			if (myFirstNode < 0 || myFirstNode >= mySize) {
				System.err.println("Invalid node choice: [" + myFirstNode
						+ "] given size [" + mySize + "].");
				System.exit(4);
			}

			System.out
					.println("Second node to test? (0-" + (mySize - 1) + ").");

			final String secondNodeString = reader.readLine();

			mySecondNode = Integer.parseInt(secondNodeString);

			if (mySecondNode < 0 || mySecondNode >= mySize
					|| mySecondNode == myFirstNode) {
				System.err.println("Invalid node choice: [" + mySecondNode
						+ "] given size [" + mySize + "] and first node ["
						+ myFirstNode + "].");
				System.exit(4);
			}

			// get conditioning set
			System.out
					.println("Conditioning set? For an empty set, type -1. /nAll other integer values other than (0-"
							+ (mySize - 1)
							+ ") will be ignored. /nWill not condition on the nodes being tested.");

			final String conditioningString = reader.readLine();

			myConditioningSet = new ArrayList<Integer>();

			if (conditioningString.equals("-1")) {
				// empty conditioning set
				System.out.println("Empty conditioning list.");
			} else {
				// non-empty conditioning set

				for (int conditionIndex = 0; conditionIndex < conditioningString
						.length(); conditionIndex++) {
					try {
						final int condition = Integer
								.parseInt(conditioningString.substring(
										conditionIndex, conditionIndex + 1));

						if (condition == myFirstNode
								|| condition == mySecondNode) {
							System.out
									.println("Ignoring condition matching a node being tested.");
						} else {
							myConditioningSet.add(Integer.valueOf(condition));
						}

					} catch (final StringIndexOutOfBoundsException iException) {
						// out of bounds
						System.err.println("Out of bounds; ignore.");
					} catch (final NumberFormatException iException) {
						// invalid input
						System.err.println("Invalid input; ignore.");
					}
				}
			}

			// print conditioning set
			final StringBuilder conditioningSetString = new StringBuilder(
					"Conditioning set: \n[");
			for (final Integer condition : myConditioningSet) {
				conditioningSetString.append(" " + condition + " ");
			}
			conditioningSetString.append("]\n");
			System.out.println(conditioningSetString.toString());

			// begin the path
			final List<NodeOnPath> currentPathList = new ArrayList<NodeOnPath>();
			currentPathList
					.add(new NodeOnPath(myFirstNode, TransitionType.None));
			final Path currentPath = new Path(currentPathList, false, false);

			// data gathered; perform test
			testForConditionalIndependence(currentPath);

			// print data
			System.out.println("\n\n\n\n\nCOMPLETED");

			int relevantPaths = 0;
			int blockedRelevantPaths = 0;
			System.out.println("Total paths found: [" + PATHS.size() + "]");
			for (final Path path : PATHS) {
				if (path.isReachedDestination()) {
					relevantPaths++;

					final StringBuilder pathString = new StringBuilder(
							"Relevant path found: [");

					for (final NodeOnPath nodeOnPath : path.getNodesOnPath()) {
						pathString.append(" " + nodeOnPath.getNodeNumber()
								+ " ");
					}

					pathString.append("].");
					if (path.isBlocked()) {
						blockedRelevantPaths++;
						pathString.append(" BLOCKED");
					} else {
						pathString.append(" NOT BLOCKED");
					}
					pathString.append("\n");
					System.out.println(pathString.toString());
				}
			}
			System.out.println("Total relevant paths found: [" + relevantPaths
					+ "].");
			if (relevantPaths == 0) {
				System.out.println("The nodes [" + myFirstNode + "] and ["
						+ mySecondNode + "] are completely independent.");
			} else {
				System.out.println("Total blocked relevant paths found: ["
						+ blockedRelevantPaths + "].");
				if (blockedRelevantPaths < relevantPaths) {
					System.out.println("["
							+ (relevantPaths - blockedRelevantPaths)
							+ "] unblocked paths from [" + myFirstNode
							+ "] to [" + mySecondNode
							+ "]. Not conditionally independent.");
				} else {
					System.out
							.println("All paths from ["
									+ myFirstNode
									+ "] to ["
									+ mySecondNode
									+ "] blocked by the conditioning set. Conditionally independent.");
				}
			}

		} catch (final IOException iException) {
			System.out.println("IO Exception: [" + iException.getMessage()
					+ "].");
			System.exit(1);
		} catch (final NumberFormatException iException) {
			System.out.println("User did not specify a number when needed: ["
					+ iException.getMessage() + "].");
			System.exit(2);
		}
	}

	private static void testForConditionalIndependence(final Path iCurrentPath) {
		// grab the latest node (this is the one we are currently handling)
		final List<NodeOnPath> currentPathList = iCurrentPath.getNodesOnPath();
		final NodeOnPath currentNodeOnPath = currentPathList
				.get(currentPathList.size() - 1);
		final int currentIntOnPath = currentNodeOnPath.getNodeNumber();
		final TransitionType currentTransitionTypeOnPath = currentNodeOnPath
				.getTransitionType();

		// print current path
		final StringBuilder pathString = new StringBuilder("Current path: [");
		for (final NodeOnPath nodeOnPath : currentPathList) {
			pathString.append(" " + nodeOnPath.getNodeNumber() + " ");
		}
		pathString.append("] Blocked: " + iCurrentPath.isBlocked());
		System.out.println(pathString.toString());

		// see if we are at the end
		if (currentIntOnPath == mySecondNode) {
			// reached destination
			iCurrentPath.setReachedDestination(true);
			PATHS.add(iCurrentPath);
		} else if (currentPathList.size() == mySize) {
			// reached maximum number of paths (stops infinite loops if the
			// nodes can have circular connections)
			PATHS.add(iCurrentPath);
		} else {
			// have not reached the end yet
			// loop through children and parents
			for (int childParentIndex = 0; childParentIndex < mySize; childParentIndex++) {
				if (!nodeOnPath(childParentIndex, currentPathList)) {
					// child
					if (myAdjacencyMatrix[currentIntOnPath][childParentIndex] == 1) {
						// form new node and branching path
						final NodeOnPath nextNodeOnPath = new NodeOnPath(
								childParentIndex, TransitionType.TAIL_TO_HEAD);
						final Path newPath = iCurrentPath.clone();
						newPath.addNodeOnPath(nextNodeOnPath);

						// calculate blocking from the current node if this path
						// is taken
						if (!currentTransitionTypeOnPath
								.equals(TransitionType.None)
								&& !newPath.isBlocked()) {
							// this node has a head and a tail or two tails;
							// path blocked if this node is in the conditioning
							// set
							if (nodeInConditioningSet(currentIntOnPath)) {
								newPath.setBlocked(true);
							}
						}

						// recurse
						testForConditionalIndependence(newPath);
					}

					// parent
					if (myAdjacencyMatrix[childParentIndex][currentIntOnPath] == 1) {
						// form new node and branching path
						final NodeOnPath nextNodeOnPath = new NodeOnPath(
								childParentIndex, TransitionType.HEAD_TO_TAIL);
						final Path newPath = iCurrentPath.clone();
						newPath.addNodeOnPath(nextNodeOnPath);

						// calculate blocking from the current node if this path
						// is taken
						if (currentTransitionTypeOnPath
								.equals(TransitionType.HEAD_TO_TAIL)
								&& !newPath.isBlocked()) {
							// this node has a head and a tail; path blocked if
							// this node is in the conditioning set
							if (nodeInConditioningSet(currentIntOnPath)) {
								newPath.setBlocked(true);
							}
						} else if (currentTransitionTypeOnPath
								.equals(TransitionType.TAIL_TO_HEAD)
								&& !newPath.isBlocked()) {
							// this node has two heads; path blocked if neither
							// this node nor any of its descendants are in the
							// conditioning set
							final List<Integer> nodeList = new ArrayList<Integer>();
							nodeList.add(Integer.valueOf(currentIntOnPath));
							newPath.setBlocked(!nodeOrDescendantsInConditioningSet(
									nodeList, new ArrayList<Integer>()));
						}

						// recurse
						testForConditionalIndependence(newPath);
					}
				} else {
					System.out.println("INode [" + childParentIndex
							+ "] is already on this path. Ignoring.");
				}

			}
		}
	}

	private static boolean nodeOrDescendantsInConditioningSet(
			final List<Integer> iNodeList,
			final ArrayList<Integer> iAttemptedList) {
		final List<Integer> newNodeList = new ArrayList<Integer>();
		for (final Integer node : iNodeList) {
			if (nodeInConditioningSet(node)) {
				// found a conditioned node in the list
				return true;
			} else {
				// this node was not a conditioned node
				iAttemptedList.add(node);
				// get children of this node for recursive attempt
				for (int childIndex = 0; childIndex < mySize; childIndex++) {
					if (!iAttemptedList.contains(Integer.valueOf(childIndex))) {
						// have not attempt this node
						if (myAdjacencyMatrix[node][childIndex] == 1) {
							// child connection present
							newNodeList.add(Integer.valueOf(childIndex));
						}// no child connection
					}// attempted this node before
				}
			}
		}

		if (!newNodeList.isEmpty()) {
			// still have nodes to try
			return nodeOrDescendantsInConditioningSet(newNodeList,
					iAttemptedList);
		} else {
			// no nodes left to try
			return false;
		}
	}

	private static boolean nodeInConditioningSet(final int iNodeIndex) {
		for (final Integer condition : myConditioningSet) {
			if (condition.intValue() == iNodeIndex) {
				return true;
			}
		}
		return false;
	}

	private static boolean nodeOnPath(final int iNodeIndex,
			final List<NodeOnPath> iCurrentPath) {
		for (final NodeOnPath nodeOnPath : iCurrentPath) {
			if (nodeOnPath.getNodeNumber() == iNodeIndex) {
				return true;
			}
		}
		return false;
	}
}
