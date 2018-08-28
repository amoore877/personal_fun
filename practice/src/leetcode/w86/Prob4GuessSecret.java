package leetcode.w86;

import java.util.ArrayList;
import java.util.List;

public class Prob4GuessSecret {
	public static void main(String[] args) {
		String[] wordlist = new String[] { "wichbx", "oahwep", "tpulot", "eqznzs", "vvmplb", "eywinm", "dqefpt",
				"kmjmxr", "ihkovg", "trbzyb", "xqulhc", "bcsbfw", "rwzslk", "abpjhw", "mpubps", "viyzbc", "kodlta",
				"ckfzjh", "phuepp", "rokoro", "nxcwmo", "awvqlr", "uooeon", "hhfuzz", "sajxgr", "oxgaix", "fnugyu",
				"lkxwru", "mhtrvb", "xxonmg", "tqxlbr", "euxtzg", "tjwvad", "uslult", "rtjosi", "hsygda", "vyuica",
				"mbnagm", "uinqur", "pikenp", "szgupv", "qpxmsw", "vunxdn", "jahhfn", "kmbeok", "biywow", "yvgwho",
				"hwzodo", "loffxk", "xavzqd", "vwzpfe", "uairjw", "itufkt", "kaklud", "jjinfa", "kqbttl", "zocgux",
				"ucwjig", "meesxb", "uysfyc", "kdfvtw", "vizxrv", "rpbdjh", "wynohw", "lhqxvx", "kaadty", "dxxwut",
				"vjtskm", "yrdswc", "byzjxm", "jeomdc", "saevda", "himevi", "ydltnu", "wrrpoc", "khuopg", "ooxarg",
				"vcvfry", "thaawc", "bssybb", "ccoyyo", "ajcwbj", "arwfnl", "nafmtm", "xoaumd", "vbejda", "kaefne",
				"swcrkh", "reeyhj", "vmcwaf", "chxitv", "qkwjna", "vklpkp", "xfnayl", "ktgmfn", "xrmzzm", "fgtuki",
				"zcffuv", "srxuus", "pydgmq" };
		new Prob4GuessSecret().findSecretWord(wordlist, new Master("ccoyyo", wordlist));
	}

	public void findSecretWord(String[] wordlist, Master master) {
		GuessManager manager = new GuessManager();

		// not really robust, but we are guessing...
		if (wordlist.length > 30) {
			int i = 0;
			while (i < 3) {
				int indexAttempt = (int) (Math.random() * (wordlist.length));
				String word = wordlist[indexAttempt];
				boolean attempt = manager.isWorthAttempt(word);
				if (attempt) {
					int matches = master.guess(word);
					if (matches == 6) {
						return;
					} else {
						manager.guesses.add(new Guess(word, matches));
					}
				}
			}
		}

		for (String word : wordlist) {
			boolean attempt = manager.isWorthAttempt(word);

			if (attempt) {
				int matches = master.guess(word);
				if (matches == 6) {
					return;
				} else {
					manager.guesses.add(new Guess(word, matches));
				}
			}
		}
	}

	public static class GuessManager {
		public List<Guess> guesses = new ArrayList<>();

		public boolean isWorthAttempt(String possGuess) {
			for (Guess pastGuess : guesses) {
				if (commonIndices(possGuess, pastGuess.guess) != pastGuess.correct) {
					return false;
				}
			}

			return true;
		}

		public static int commonIndices(String s1, String s2) {
			int count = 0;
			for (int i = 0; i < s1.length(); i++) {
				if (s1.charAt(i) == s2.charAt(i)) {
					count++;
				}
			}
			return count;
		}
	}

	public static class Guess {
		public final String guess;
		public final int correct;

		public Guess(String guess, int correct) {
			this.guess = guess;
			this.correct = correct;
		}
	}

	public static class Master {
		private final String secret;
		private final String[] words;

		public Master(String secret, String[] words) {
			this.secret = secret;
			this.words = words;
		}

		public int guess(String word) {
			boolean found = false;
			for (String valid : words) {
				if (valid.equals(word)) {
					found = true;
					break;
				}
			}
			if (!found) {
				return -1;
			}

			return GuessManager.commonIndices(word, secret);
		}
	}
}
