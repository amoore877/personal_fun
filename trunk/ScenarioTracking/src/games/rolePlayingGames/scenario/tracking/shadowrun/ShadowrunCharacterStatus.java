package games.rolePlayingGames.scenario.tracking.shadowrun;

import java.awt.Color;

/**
 * Statuses for Shadowrun characters.
 * 
 * @author Andrew
 *
 */
public enum ShadowrunCharacterStatus {

	/**
	 * OK. Fine. Alive. Conscious.
	 */
	OK,
	/**
	 * Unconscious. Incapacitated. Asleep. Knocked out.
	 */
	ZZZ,
	/**
	 * Dying. At Death's door. Bleeding out.
	 */
	DYING,
	/**
	 * Dead.
	 */
	DEAD;

	/**
	 * @param iStatus
	 *            status.
	 * @return color for that status.
	 */
	public static Color getColorForStatus(final ShadowrunCharacterStatus iStatus) {
		switch (iStatus) {
		case OK:
			return Color.WHITE;
		case ZZZ:
			return Color.YELLOW;
		case DYING:
			return Color.PINK;
		case DEAD:
			return Color.RED;
		default:
			return Color.WHITE;
		}
	}
}
