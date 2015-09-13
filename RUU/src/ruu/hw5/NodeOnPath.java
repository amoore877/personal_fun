package ruu.hw5;

public class NodeOnPath {

	/**
	 * Transition type to get to this node.
	 */
	private final TransitionType transitionType;

	/**
	 * INode number.
	 */
	private final int nodeNumber;

	/**
	 * Construct.
	 * 
	 * @param iNodeNumber
	 * @param iTransitionType
	 */
	public NodeOnPath(int iNodeNumber, TransitionType iTransitionType) {
		nodeNumber = iNodeNumber;
		transitionType = iTransitionType;
	}

	public TransitionType getTransitionType() {
		return transitionType;
	}

	public int getNodeNumber() {
		return nodeNumber;
	}
}
