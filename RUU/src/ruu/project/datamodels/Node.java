package ruu.project.datamodels;

/**
 * Some node in the network.
 * 
 * @author Andrew
 *
 */
public abstract class Node implements INode {
	/**
	 * Probability of this node given its parent.
	 */
	private final Double myProbability;

	/**
	 * Constructor.
	 * 
	 * @param iProbability
	 *            Probability of this node given its parent.
	 */
	public Node(final double iProbability) {
		myProbability = iProbability;
	}

	@Override
	public final double getProbability() {
		return myProbability;
	}

}
