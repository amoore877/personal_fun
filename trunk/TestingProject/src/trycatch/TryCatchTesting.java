package trycatch;

public class TryCatchTesting {

	public static void main(final String[] args) {
		try {
			System.out.println(1);
			return;
		} catch (final Exception e) {
			System.err.println(2);
		} finally {
			System.out.println(3);
		}
	}

}
