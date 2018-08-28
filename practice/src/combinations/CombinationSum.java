package combinations;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

	public static void main(String[] args) {
		System.out.println(new CombinationSum().combinationSum(new int[] { 2, 3, 6, 7 }, 7));

	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> ret = new ArrayList<>();

		for (int i = 0; i < candidates.length; i++) {
			List<Integer> currList = new ArrayList<>();
			currList.add(candidates[i]);
			if (candidates[i] == target) {
				ret.add(currList);
			} else {
				combinationSum(candidates, target, ret, currList, candidates[i], i);
			}
		}

		return ret;
	}

	private void combinationSum(int[] candidates, int target, List<List<Integer>> ret, List<Integer> currList,
			int currSum, int currBaseIndex) {
		for (int i = currBaseIndex; i < candidates.length; i++) {
			int currInt = candidates[i];
			int newSum = currSum + currInt;
			if (newSum == target) {
				List<Integer> newAns = new ArrayList<>(currList);
				newAns.add(currInt);
				ret.add(newAns);
			} else if (newSum < target) {
				List<Integer> newAns = new ArrayList<>(currList);
				newAns.add(currInt);
				combinationSum(candidates, target, ret, newAns, newSum, i);
			}
		}
	}
}
