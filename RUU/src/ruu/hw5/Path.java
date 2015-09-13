package ruu.hw5;

import java.util.ArrayList;
import java.util.List;

public class Path {

	private final List<NodeOnPath> myNodesOnPath;

	private boolean myReachedDestination;

	private boolean myBlocked;

	public Path(final List<NodeOnPath> iNodesOnPath,
			final boolean iReachedDestination, final boolean iBlocked) {
		myNodesOnPath = iNodesOnPath;
		myReachedDestination = iReachedDestination;
		myBlocked = iBlocked;
	}

	public boolean isReachedDestination() {
		return myReachedDestination;
	}

	public void setReachedDestination(final boolean myReachedDestination) {
		this.myReachedDestination = myReachedDestination;
	}

	public boolean isBlocked() {
		return myBlocked;
	}

	public void setBlocked(final boolean myBlocked) {
		this.myBlocked = myBlocked;
	}

	public List<NodeOnPath> getNodesOnPath() {
		return myNodesOnPath;
	}

	public void addNodeOnPath(final NodeOnPath iNodeOnPath) {
		myNodesOnPath.add(iNodeOnPath);
	}

	@Override
	public Path clone() {
		final List<NodeOnPath> nodesOnPath = new ArrayList<NodeOnPath>();
		for (final NodeOnPath nodeOnPath : myNodesOnPath) {
			nodesOnPath.add(nodeOnPath);
		}
		return new Path(nodesOnPath, myReachedDestination, myBlocked);
	}
}
