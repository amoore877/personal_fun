package trees;

import java.util.ArrayList;
import java.util.List;

public class OrXorTreeBuilding {
	public static void main(String args[]) throws Exception {
		int[] vals = new int[] { 13, 6, 3, 7, 5, 9, 10, 4 };
		System.out.println(buildTree(vals));
		// vals = new int[]{1,0};
		// System.out.println(buildTree(vals));
		// vals = new int[]{1,0,1,0};
		// System.out.println(buildTree(vals));
		// vals = new int[]{13,6,3,7,5,9,10,4};
		// System.out.println(buildTree(vals));
	}

	public static TreeNode root;

	public static int buildTree(int[] leafs) {
		List<TreeNode> nodes = new ArrayList<>();

		for (int leaf : leafs) {
			nodes.add(new TreeNode(leaf));
		}

		// true or, false xor
		boolean orXor = true;
		while (nodes.size() != 1) {
			nodes = buildTreeSupport(nodes, orXor);
			orXor = !orXor;
		}

		// nodes contains root
		root = nodes.get(0);
		return root.val;
	}

	public static List<TreeNode> buildTreeSupport(List<TreeNode> nodes, boolean orXor) {
		List<TreeNode> newLayer = new ArrayList<TreeNode>();

		for (int i = 0; i < nodes.size() - 1; i += 2) {
			TreeNode left = nodes.get(i);
			TreeNode right = nodes.get(i + 1);

			int val;
			if (orXor) {
				val = left.val | right.val;
				System.out.println(left.val + " OR " + right.val + "=" + val);
			} else {
				val = left.val ^ right.val;
				System.out.println(left.val + " XOR " + right.val + "=" + val);
			}

			TreeNode newNode = new TreeNode(val);
			newNode.left = left;
			newNode.right = right;
			newLayer.add(newNode);
		}

		return newLayer;
	}

	public static class TreeNode {
		public int val;
		public TreeNode right;
		public TreeNode left;

		public TreeNode(int val) {
			this.val = val;
		}
	}
}