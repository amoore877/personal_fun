package stringanalysis;

public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		StringBuilder ret = new StringBuilder();

		if (strs == null || strs.length == 0) {
			return ret.toString();
		}

		for (int l = 0; l < strs[0].length(); l++) {
			char candidate = strs[0].charAt(l);
			boolean noProb = true;
			for (int i = 1; i < strs.length && noProb; i++) {
				if (strs[i].length() < l + 1 || !(strs[i].charAt(l) == candidate)) {
					noProb = false;
				}
			}

			if (noProb) {
				ret.append(candidate);
			} else {
				return ret.toString();
			}
		}

		return ret.toString();
	}
}
