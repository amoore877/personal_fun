package dicts;

public class DictBreakCheck {
	public static void main(String args[]) throws Exception {
		System.out.println(breaks("CatMatat"));
	}

	public static String[] dict = new String[] { "Cat", "Mat", "Ca", "tM", "C", "Dog", "og", "Do" };

	public static int breaks(String word) {
		int breaks = Integer.MAX_VALUE;

		if (inDict(word)) {
			return 0;
		}

		for (int i = 1; i <= word.length(); i++) {
			if (inDict(word.substring(0, i))) {
				String subword = word.substring(i);
				if (!subword.isEmpty()) {
					int subbreaks = breaks(subword);

					if (subbreaks == -1) {
						continue;
					}
					breaks = Math.min(1 + subbreaks, breaks);
				} else {
					return 0;
				}
			}
		}

		if (breaks == Integer.MAX_VALUE) {
			return -1;
		} else {
			return breaks;
		}
	}

	public static boolean inDict(String word) {
		for (String dictWord : dict) {
			if (word.equals(dictWord)) {
				System.out.println(word + " is a word");
				return true;
			}
		}
		System.out.println(word + " is not a word");
		return false;
	}
}
