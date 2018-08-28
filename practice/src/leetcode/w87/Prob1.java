package leetcode.w87;

public class Prob1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean backspaceCompare(String S, String T) {
		String newS = "";
		String newT = "";

		int i = 0;
		int j = 0;

		while (i < S.length() || j < T.length()) {
			if (i < S.length()) {
				char s = S.charAt(i);
				if (s == '#') {
					newS = newS.substring(0, newS.length() == 0 ? 0 : newS.length() - 1);
				} else {
					newS += s;
				}
			}

			if (j < T.length()) {
				char t = T.charAt(j);
				if (t == '#') {
					newT = newT.substring(0, newT.length() == 0 ? 0 : newT.length() - 1);
				} else {
					newT += t;
				}
			}

			i++;
			j++;
		}

		return newS.equals(newT);
	}
}
