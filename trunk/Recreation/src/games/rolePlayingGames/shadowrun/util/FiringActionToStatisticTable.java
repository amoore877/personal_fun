package games.rolePlayingGames.shadowrun.util;

/**
 * Relates firing actions to statistics on those actions.
 * 
 * @author Andrew
 *
 */
public final class FiringActionToStatisticTable {

	private FiringActionToStatisticTable() {
	}

	/**
	 * Get bullet usage for a firing action.
	 * 
	 * @param iFiringAction
	 *            firing action to look up.
	 * @return bullet usage.
	 */
	public static int getBulletUsageForFiringAction(
			final FiringAction iFiringAction) {
		switch (iFiringAction) {
		case BF:
			return 3;
		case FA_COMPLEX:
			return 10;
		case FA_SIMPLE:
			return 6;
		case LB:
			return 6;
		case SA:
			return 1;
		case SB:
			return 3;
		case SS:
			return 1;
		case SUPPRESS:
			return 20;
		default:
			return 0;
		}
	}

	/**
	 * Get recoil for a firing action.
	 * 
	 * @param iFiringAction
	 *            firing action to look up.
	 * @return raw recoil.
	 */
	public static int getRecoilForFiringAction(final FiringAction iFiringAction) {
		switch (iFiringAction) {
		case BF:
			return 3;
		case FA_COMPLEX:
			return 10;
		case FA_SIMPLE:
			return 6;
		case LB:
			return 6;
		case SA:
			return 1;
		case SB:
			return 3;
		case SS:
			return 0;
		case SUPPRESS:
			return 0;
		default:
			return 0;
		}
	}

	/**
	 * Get dodge penalty for a firing action. Usually bullet usage -1.
	 * Suppression is a special case that should be handled.
	 * 
	 * @param iFiringAction
	 *            firing action to look up.
	 * @return dodge penalty.
	 */
	public static int getDodgePenaltyForFiringAction(
			final FiringAction iFiringAction) {
		switch (iFiringAction) {
		case BF:
			return -2;
		case FA_COMPLEX:
			return -9;
		case FA_SIMPLE:
			return -5;
		case LB:
			return -5;
		case SA:
			return 0;
		case SB:
			return -2;
		case SS:
			return 0;
		case SUPPRESS:
			return 0;
		default:
			return 0;
		}
	}
}
