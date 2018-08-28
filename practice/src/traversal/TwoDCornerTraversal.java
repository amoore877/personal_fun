package traversal;

public class TwoDCornerTraversal {

	public static void main(String[] args) {
		// int[][] x = new int[][] { { 1, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0,
		// 0 }, { 0, 0, 0, 1 } };
		// int[][] x = new int[][] { { 1, 0, }, { 0, 1 } };
		int[][] x = new int[][] { { 1, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 } };
		System.out.println(TwoDCornerTraversal.cornerTraverse(x));
	}

	public static int cornerTraverse(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				// check right node available
				if (j != arr[i].length - 1) {
					arr[i][j + 1] += arr[i][j];
				}
				// check lower node available
				if (i != arr.length - 1) {
					arr[i + 1][j] += arr[i][j];
				}
			}
		}

		return arr[arr.length - 1][arr[0].length - 1] - 1;
	}

}
