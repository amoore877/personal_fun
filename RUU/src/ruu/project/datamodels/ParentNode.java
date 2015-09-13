package ruu.project.datamodels;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Parent node to some other nodes (either parent or end nodes).
 * 
 * Programmatically, the network is really split into several trees using
 * depth-first splitting.
 * 
 * <N> is the type of the immediate children.
 * 
 * <E> is the type of the end nodes.
 * 
 * @author Andrew
 *
 */
public abstract class ParentNode<N extends Node, E extends EndNode> extends
		Node {

	/**
	 * Child nodes to this node.
	 */
	private final ArrayList<N> myChildren;

	/**
	 * Constructor.
	 * 
	 * <N> is the type of the children.
	 * 
	 * <E> is the type of the end nodes.
	 * 
	 * @param iChildren
	 *            children to this node.
	 * @param iProbability
	 *            Probability of this node given its parent.
	 */
	public ParentNode(final ArrayList<N> iChildren, final double iProbability) {
		super(iProbability);
		myChildren = iChildren;
	}

	/**
	 * Assuming this node is the evidence, get the probabilities of each of the
	 * end nodes and return the nodes mapped to their new probabilities.
	 * 
	 * @return list of all end nodes with their probabilities set.
	 */
	public abstract LinkedHashMap<E, Double> calculateEndNodeProbabilities();

	public ArrayList<N> getChildren() {
		return myChildren;
	}

}
