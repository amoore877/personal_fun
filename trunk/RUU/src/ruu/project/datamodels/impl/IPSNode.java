package ruu.project.datamodels.impl;

import java.util.Random;

import ruu.project.datamodels.EndNode;

/**
 * INode in the IPS layer, representing some range of IPS response time.
 * 
 * @author Andrew
 *
 */
public final class IPSNode extends EndNode {

	/**
	 * Minimum IPS response time. Exclusive.
	 */
	private final double myMinResponseTime;

	/**
	 * Maximum IPS response time. Inclusive.
	 */
	private final double myMaxResponseTime;

	/**
	 * Random, for getting some value between the minimum and maximum.
	 */
	private final Random myRandom = new Random();

	/**
	 * Construct an IPS node, representing some range of IPS response time..
	 * 
	 * @param iProbability
	 *            Probability of this node given its parent.
	 * @param iMinResponseTime
	 *            Minimum IPS response time. Exclusive.
	 * @param iMaxResponseTime
	 *            Maximum IPS response time. Inclusive.
	 */
	public IPSNode(final double iProbability, final double iMinResponseTime,
			final double iMaxResponseTime) {
		super(iProbability);
		myMinResponseTime = iMinResponseTime;
		myMaxResponseTime = iMaxResponseTime;

		// System.out.println("Constructed IPS Node: " + toString() + " "
		// + iProbability);
	}

	/**
	 * @return Minimum IPS response time. Exclusive.
	 */
	public double getMinResponseTime() {
		return myMinResponseTime;
	}

	/**
	 * @return Maximum IPS response time. Inclusive.
	 */
	public double getMaxResponseTime() {
		return myMaxResponseTime;
	}

	/**
	 * @return a random IPS between the minimum response time and the maximum
	 *         response time.
	 */
	public double getRandomResponseTime() {
		return myMinResponseTime
				+ ((myMaxResponseTime - myMinResponseTime) * myRandom
						.nextDouble());
	}

	@Override
	public final String toString() {
		return "IPS [" + myMinResponseTime + " to " + myMaxResponseTime + "]";
	}

	@Override
	public final boolean equals(final Object iOtherObject) {
		if (iOtherObject instanceof IPSNode) {
			final IPSNode otherIpsNode = (IPSNode) iOtherObject;
			if ((myMinResponseTime == otherIpsNode.getMinResponseTime())
					&& (myMaxResponseTime == otherIpsNode.getMaxResponseTime())) {
				return true;
			}
		}

		return false;
	}
}
