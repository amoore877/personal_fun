package leetcode.w88;

public class Prob1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println((int) 'a');
		System.out.println((int) 'z');
	}

	public String shiftingLetters(String S, int[] shifts) {
		StringBuilder ret = new StringBuilder();

		for (int i = 0; i < shifts.length; i++) {
			char c = S.charAt(i);

			int shiftAmt = 0;
			for (int j = i; j < shifts.length; j++) {
				shiftAmt = (shiftAmt + (shifts[j] % 26)) % 26;
			}
			ret.append(shift(shiftAmt, c));
		}

		return ret.toString();
	}

	public char shift(int shiftAmt, char c) {
		c += shiftAmt;

		if (c > 122) {
			c -= 26;
		}

		return c;
	}
}
