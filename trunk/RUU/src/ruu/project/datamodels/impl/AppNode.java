package ruu.project.datamodels.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import ruu.project.datamodels.ParentNode;

/**
 * Node in the App layer, representing some application is on.
 * 
 * @author Andrew
 *
 */
public final class AppNode extends ParentNode<CPUNode, IPSNode> {

	/**
	 * Constructor.
	 * 
	 * @param iChildren
	 *            children to this node.
	 * @param iProbability
	 *            Probability of this node given its parent.
	 */
	public AppNode(final ArrayList<CPUNode> iChildren, final double iProbability) {
		super(iChildren, iProbability);
	}

	@Override
	public LinkedHashMap<IPSNode, Double> calculateEndNodeProbabilities() {
		/**
		 * Make extended use of probability tables:
		 * 
		 * p(IPS_x|App_y ) = p(IPS_x|CPU_hi )*p(CPU_hi|App_y ) +
		 * p(IPS_x|CPU_med)*p(CPU_med|App_y ) + p(IPS_x|CPU_low
		 * )*p(CPU_low|App_y )
		 */
		final LinkedHashMap<IPSNode, Double> oResults = new LinkedHashMap<IPSNode, Double>();

		// loop through CPU nodes
		for (final CPUNode cpuNode : getChildren()) {
			// get results for this CPU (unweighted at this point)
			final HashMap<IPSNode, Double> cpuProbabilityResults = cpuNode
					.calculateEndNodeProbabilities();

			// loop through unweighted CPU results, and add to output map with
			// the weighting applied
			for (final Entry<IPSNode, Double> cpuProbabilityResult : cpuProbabilityResults
					.entrySet()) {
				final IPSNode ipsNodeForCPU = cpuProbabilityResult.getKey();
				final Double ipsNodeResultForCPU = cpuProbabilityResult
						.getValue();

				/**
				 * add to results
				 */
				Double currentProbabilityForIPSNode = oResults
						.get(ipsNodeForCPU);

				if (currentProbabilityForIPSNode == null) {
					// we have not dealt with this node before
					currentProbabilityForIPSNode = Double.valueOf(0);
				}

				// add to results, applying weighting based on the CPU state
				currentProbabilityForIPSNode = Double
						.valueOf(currentProbabilityForIPSNode.doubleValue()
								+ (cpuNode.getProbability() * ipsNodeResultForCPU));

				// put new value to the result map
				oResults.put(ipsNodeForCPU, currentProbabilityForIPSNode);
			}
		}

		return oResults;
	}

}
