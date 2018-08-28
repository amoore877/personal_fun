package leetcode.w86;

import java.util.HashSet;
import java.util.Set;

public class Prob1MagicSquares {
	public static void main(String[] args) {
	}

	public int numMagicSquaresInside(int[][] grid) {
		if (grid == null) {
			return 0;
		}

		int length = grid.length;
		if (length < 3) {
			return 0;
		} else {
			int count = 0;

			for (int i = 0; i <= length - 3; i++) {
				for (int j = 0; j <= length - 3; j++) {
					if (isMagicSquare(grid, i, j)) {
						count++;
					}
				}
			}

			return count;
		}
	}

	private boolean isMagicSquare(int[][] grid, int i, int j) {
		int sum = grid[i][j] + grid[i][j + 1] + grid[i][j + 2];

		// check rows
		for (int m = 1; m < 3; m++) {
			if (grid[m + i][j] + grid[m + i][j + 1] + grid[m + i][j + 2] != sum) {
				return false;
			}
		}

		// check columns
		for (int n = 0; n < 3; n++) {
			if (grid[i][n + j] + grid[i + 1][n + j] + grid[i + 2][n + j] != sum) {
				return false;
			}
		}

		// check diagonals
		if (grid[i][j] + grid[i + 1][j + 1] + grid[i + 2][j + 2] != sum
				|| grid[i + 2][j] + grid[i + 1][j + 1] + grid[i][j + 2] != sum) {
			return false;
		}

		// validate
		Set<Integer> nums = new HashSet<>();
		for (int m = 0; m < 3; m++) {
			for (int n = 0; n < 3; n++) {
				int num = grid[i + m][j + n];
				if (num < 1 || num > 9) {
					return false;
				}
				if (!nums.add(num)) {
					return false;
				}
			}
		}

		return true;
	}
}
