package leetcode.w88;

public class Prob2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int maxDistToClosest(int[] seats) {
		int maxDist = 0;

		int currSeparation = 0;

		boolean foundFirstOne = false;

		for (int i : seats) {
			if (i == 1) {
				if (currSeparation != 0) {
					if (!foundFirstOne) {
						maxDist = currSeparation;
					} else {
						if (currSeparation % 2 == 0) {
							maxDist = Math.max(maxDist, currSeparation / 2);
						} else {
							maxDist = Math.max(maxDist, (currSeparation / 2) + 1);
						}
					}
				}

				foundFirstOne = true;
				currSeparation = 0;
			} else {
				currSeparation++;
			}
		}

		maxDist = Math.max(maxDist, currSeparation);

		return maxDist;
	}
}
