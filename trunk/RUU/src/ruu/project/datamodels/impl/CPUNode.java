package ruu.project.datamodels.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import ruu.project.datamodels.ParentNode;

/**
 * INode in CPU layer, representing some CPU state.
 * 
 * @author Andrew
 *
 */
public final class CPUNode extends ParentNode<IPSNode, IPSNode> {

	/**
	 * Constructor.
	 * 
	 * @param iChildren
	 *            children to this node.
	 * @param iProbability
	 *            Probability of this node given its parent.
	 */
	public CPUNode(final ArrayList<IPSNode> iChildren, final double iProbability) {
		super(iChildren, iProbability);
	}

	@Override
	public LinkedHashMap<IPSNode, Double> calculateEndNodeProbabilities() {
		// given this CPU node as the evidence, use simple probability tables to
		// get chance of each IPS node (will be the stored probability)
		final LinkedHashMap<IPSNode, Double> oResults = new LinkedHashMap<IPSNode, Double>();

		for (final IPSNode ipsNode : getChildren()) {
			oResults.put(ipsNode, Double.valueOf(ipsNode.getProbability()));
		}

		return oResults;
	}

}
