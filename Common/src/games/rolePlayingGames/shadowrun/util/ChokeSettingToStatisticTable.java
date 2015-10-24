package games.rolePlayingGames.shadowrun.util;

/**
 * Relates choke settings to statistics on those settings.
 * 
 * @author Andrew
 *
 */
public final class ChokeSettingToStatisticTable {

	private ChokeSettingToStatisticTable() {
	}

	/**
	 * Get dodge penalty for a choke setting.
	 * 
	 * @param iChokeSetting
	 *            choke setting to look up.
	 * @return dodge penalty.
	 */
	public static int getDodgePenaltyForChokeSetting(
			final ChokeSetting iChokeSetting) {
		switch (iChokeSetting) {
		case MEDIUM:
			return -3;
		case WIDE:
			return -5;
		case NARROW:
		default:
			return -1;
		}
	}

	/**
	 * Get damage penalty for a choke setting and range.
	 * 
	 * @param iChokeSetting
	 *            choke setting to look up.
	 * @param iFiringRange
	 *            firing range.
	 * @return damage penalty.
	 */
	public static int getDamagePenaltyForChokeSetting(
			final ChokeSetting iChokeSetting, final FiringRangeType iFiringRange) {
		switch (iChokeSetting) {
		case MEDIUM:
			switch (iFiringRange) {
			case EXTREME:
				return -7;
			case LONG:
				return -5;
			case MEDIUM:
				return -3;
			case SHORT:
				return -1;
			default:
				return -1;
			}
		case WIDE:
			switch (iFiringRange) {
			case EXTREME:
				return -9;
			case LONG:
				return -7;
			case MEDIUM:
				return -5;
			case SHORT:
				return -3;
			default:
				return -3;
			}
		case NARROW:
		default:
			return 0;
		}
	}

	/**
	 * Get accuracy for a flechette shotgun by range.
	 * 
	 * @param iFiringRange
	 *            firing range.
	 * @return damage penalty.
	 */
	public static int getAccuracyPenaltyForChokeSetting(
			final FiringRangeType iFiringRange) {
		switch (iFiringRange) {
		case EXTREME:
		case LONG:
			return -1;
		case MEDIUM:
		case SHORT:
		default:
			return 0;
		}
	}
}
