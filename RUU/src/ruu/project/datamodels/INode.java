package ruu.project.datamodels;

/**
 * Interface for network nodes.
 * 
 * @author Andrew
 */
public interface INode {

	/**
	 * @return the probability for this node given its parent.
	 */
	public double getProbability();
}
