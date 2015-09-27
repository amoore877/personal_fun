package games.rolePlayingGames.shadowrun.util;

public final class ShadowrunCommonUtils {

	private ShadowrunCommonUtils() {
	}

	/**
	 * Compute maximum health based on given attribute (Body for physical,
	 * Willpower for Stun, Device Rating for Matrix).
	 * 
	 * Computation is always (ceiling(Att / 2) + 8), which is equivalent to (Att
	 * / 2) + (Att % 2) + 8.
	 * 
	 * @param iAtt
	 *            attribute to compute max health off of.
	 * @return related max health.
	 */
	public static int getMaximumHealth(final int iAtt) {
		return (iAtt / 2) + (iAtt % 2) + 8;
	}
}
