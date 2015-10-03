package games.rolePlayingGames.shadowrun.util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Firing Modes to Actions lookup.
 * 
 * @author Andrew
 */
public final class FiringModeToActionsTable {

	/**
	 * Lookup table.
	 */
	private static final HashMap<FiringMode, ArrayList<FiringAction>> firingModeToActionsTable = new HashMap<FiringMode, ArrayList<FiringAction>>();

	static {
		// SS mode
		final ArrayList<FiringAction> ssFiringActions = new ArrayList<FiringAction>();
		ssFiringActions.add(FiringAction.SS);
		firingModeToActionsTable.put(FiringMode.SS, ssFiringActions);

		// SA mode
		final ArrayList<FiringAction> saFiringActions = new ArrayList<FiringAction>();
		saFiringActions.add(FiringAction.SA);
		saFiringActions.add(FiringAction.SB);
		firingModeToActionsTable.put(FiringMode.SA, saFiringActions);

		// BF mode
		final ArrayList<FiringAction> bfFiringActions = new ArrayList<FiringAction>();
		bfFiringActions.add(FiringAction.BF);
		bfFiringActions.add(FiringAction.LB);
		firingModeToActionsTable.put(FiringMode.BF, bfFiringActions);

		// FA mode
		final ArrayList<FiringAction> faFiringActions = new ArrayList<FiringAction>();
		faFiringActions.add(FiringAction.FA_SIMPLE);
		faFiringActions.add(FiringAction.FA_COMPLEX);
		faFiringActions.add(FiringAction.SUPPRESS);
		firingModeToActionsTable.put(FiringMode.FA, faFiringActions);
	}

	private FiringModeToActionsTable() {
	}

	/**
	 * Get available firing actions for the given firing mode.
	 * 
	 * @param iFiringMode
	 *            firing mode to look up.
	 * @return clone of list of possible firing actions.
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<FiringAction> getActionsForMode(
			final FiringMode iFiringMode) {
		return (ArrayList<FiringAction>) (firingModeToActionsTable
				.get(iFiringMode).clone());
	}
}
