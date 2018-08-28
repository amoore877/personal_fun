package mutations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProteinMutationsOverTime {
	/*
	 * Complete the pmix function below.
	 */
	static String pmix(String s, int k) {
		/*
		 * Write your code here.
		 */
		List<String> mutations = new ArrayList<>();
		System.out.println("Original, index 0: " + s);
		mutations.add(s);
		int matchIndex = -1;
		for (int i = 1; i <= k && matchIndex == -1; i++) {
			s = mutateSet(s);
			System.out.println("Mutation " + i + ":" + s);
			if ((matchIndex = mutations.indexOf(s)) != -1) {
				System.out.println("Already in list");
			} else {
				System.out.println("Adding to list");
				mutations.add(s);
			}
		}
		if (matchIndex == -1) {
			System.out.println("No loops found. Returning: " + s);
			return s;
		} else {
			int loopSize = mutations.size() - matchIndex;

			System.out.println("For list: " + mutations + "\nFound match index of " + matchIndex + ", loop size of "
					+ loopSize + ". Will return element at index: " + (((k - matchIndex) % loopSize) + matchIndex));

			return mutations.get(((k - matchIndex) % loopSize) + matchIndex);
		}

	}

	static String mutateSet(String s) {
		StringBuilder ret = new StringBuilder();

		for (int i = 0; i < s.length() - 1; i++) {
			ret.append(mutate(s.charAt(i), s.charAt(i + 1)));
		}
		ret.append(mutate(s.charAt(s.length() - 1), s.charAt(0)));

		return ret.toString();
	}

	static char[][] MUTATION_TABLE = new char[][] { { 'A', 'B', 'C', 'D' }, { 'B', 'A', 'D', 'C' },
			{ 'C', 'D', 'A', 'B' }, { 'D', 'C', 'B', 'A' } };

	static char mutate(char c1, char c2) {
		// A B C D
		// _ _ _ _
		// A| A B C D
		// B| B A D C
		// C| C D A B
		// D| D C B A
		return MUTATION_TABLE[c1 - 65][c2 - 65];
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		// BufferedWriter bufferedWriter = new BufferedWriter(new
		// FileWriter(System.getenv("OUTPUT_PATH")));

		// String[] nk = scanner.nextLine().split(" ");
		//
		// int n = Integer.parseInt(nk[0].trim());
		//
		// int k = Integer.parseInt(nk[1].trim());
		//
		// String s = scanner.nextLine();
		// String s = "CBC";
		// int k = 10;
		String s = "CBACBCCBCACA";
		int k = 100;
		String result = pmix(s, k);

		System.out.println(result);
		// bufferedWriter.newLine();

		// bufferedWriter.close();
	}
}
