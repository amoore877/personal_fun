package ruu.project.datamodels;

/**
 * Bottom layer node whose probability we are interested in.
 * 
 * @author Andrew
 */
public abstract class EndNode extends Node {

	/**
	 * Constructor.
	 * 
	 * @param iProbability
	 *            Probability of this node given its parent.
	 */
	public EndNode(final double iProbability) {
		super(iProbability);
	}

	@Override
	public abstract boolean equals(Object iOtherObject);
}
