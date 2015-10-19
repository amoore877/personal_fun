package tools;

import java.util.UUID;

/**
 * Generate a bunch of IDs.
 * 
 * @author Andrew
 *
 */
public class GenerateIDs {

	public static void main(final String[] args) {
		for (int i = 0; i < 50; i++) {
			System.out.println(UUID.randomUUID().toString());
		}
	}

}
