package games.rolePlayingGames.shadowrun.tracking.control;

import games.rolePlayingGames.shadowrun.tracking.trackables.INonPlayer;
import games.rolePlayingGames.shadowrun.tracking.trackables.IShadowrunCombatTrackable;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.TimedItem;
import games.rolePlayingGames.tracking.control.AbstractTrackingControl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

/**
 * Shadowrun tracking control.
 * 
 * @author Andrew
 *
 */
public final class ShadowrunTrackingControl extends
		AbstractTrackingControl<IShadowrunCombatTrackable> {

	/**
	 * Sorted initiative map.
	 */
	private final HashMap<Integer, Set<IShadowrunCombatTrackable>> myInitiativeMap = new HashMap<Integer, Set<IShadowrunCombatTrackable>>();

	/**
	 * Current initiative.
	 */
	private int myCurrentInitiative = 0;

	/**
	 * Initiative pass.
	 */
	private int myInitiativePass = 0;

	/**
	 * Constructor.
	 */
	public ShadowrunTrackingControl() {
	}

	@Override
	public void addTrackable() {
		// TODO display to select a trackable

		// TODO new or existing?

		// TODO NPC or PC controlled?

		// TODO PC controlled, only show PC-controlled types

		// TODO NPC, show loaded XMLs

		// TODO if not in combat add new trackable to all overall trackables

		// TODO if in combat, add new trackable to combat list, and get
		// initiative

	}

	@Override
	public void editSelected(final IShadowrunCombatTrackable iTrackable) {
		// edit the trackable
		iTrackable.edit();

		// TODO refresh table?
	}

	/**
	 * Start combat. Add all PCs to combat. Select tracked, alive NPCs to add to
	 * combat. Allowing adding further NPCs.
	 */
	@Override
	public void startCombat() {
		// TODO confirmation

		// TODO add all PCs to combat, present options to add any alive NPCs to
		// combat

		// set combat status to true
		setInCombat(true);

		// roll initiative for all in-combat
		rollInitiative();

		// TODO make table display only in-combat trackables now

		// TODO start combat button should be disabled, end combat enabled
	}

	/**
	 * End combat. Clear combat tracking list.
	 */
	@Override
	public void endCombat() {
		// TODO confirmation

		// TODO remove dead NPCs from game?

		// clear combat tracking list
		getAllCombatTrackables().clear();

		// set combat status to false
		setInCombat(false);

		// TODO make table display all trackables now

		// TODO end combat button should be disabled, start combat enabled
	}

	@Override
	protected void rollInitiative() {
		// clear initiative tracking
		myInitiativeMap.clear();

		// loop through all in-combat trackables and roll initiative
		for (final IShadowrunCombatTrackable trackable : getAllCombatTrackables()) {
			final int initiative;

			// TODO check if still alive/operational
			if (trackable instanceof INonPlayer) {
				// non-player trackable; program rolls initiative
				// dialog penalty input dialog
				int penalty = 0;
				boolean validEntry = false;
				while (!validEntry) {
					try {
						penalty = Integer.parseInt(JOptionPane.showInputDialog(
								null,
								"Initiative penalties for: "
										+ trackable.toFullString(), 0));
						validEntry = true;
					} catch (final Exception iException) {
						// TODO specific exceptions
					}
				}
				// calculate
				initiative = ((INonPlayer) trackable).rollInitiative(penalty);
			} else if (trackable instanceof TimedItem) {
				// timed item; maintains its initiative
				initiative = trackable.getInitiative();
			} else {
				// assumed player-controlled; prompt for initiative score
				// dialog input display
				int tempInitiative = 0;
				boolean validEntry = false;
				while (!validEntry) {
					try {
						tempInitiative = Integer.parseInt(JOptionPane
								.showInputDialog(null, "Initiative for: "
										+ trackable.toString(), 0));
						validEntry = true;
					} catch (final Exception iException) {
						// TODO specific exceptions
					}
				}

				initiative = tempInitiative;
			}

			// set initiative
			trackable.setInitiative(initiative);

			// set current initiative to the maximum of all rolled initiatives
			myCurrentInitiative = Math.max(myCurrentInitiative, initiative + 1);

			// add initiative to tracking
			final Set<IShadowrunCombatTrackable> trackablesForThisInitiative;
			if (myInitiativeMap.get(initiative) != null) {
				trackablesForThisInitiative = myInitiativeMap.get(initiative);
			} else {
				trackablesForThisInitiative = new HashSet<IShadowrunCombatTrackable>();
			}

			trackablesForThisInitiative.add(trackable);
			myInitiativeMap.put(initiative, trackablesForThisInitiative);
		}

		// first initiative pass
		myInitiativePass = 1;

		// TODO table should display new initiative scores
	}

	@Override
	public void advanceInitiative() {
		Set<IShadowrunCombatTrackable> trackables = null;

		while (trackables == null) {
			myCurrentInitiative--;
			if (myCurrentInitiative <= 0) {
				// end of initiative pass
				// new initiative pass
				newInitiativePass();
				// break from the method entirely
				return;
			} else {
				// get new trackables
				trackables = myInitiativeMap.get(myCurrentInitiative);
				// may be null; if so, looping will continue until not-null or
				// initiative is 0
			}
		}

		// we have a non-null set of trackables
		// TODO input display for all currently-acting trackables
	}

	/**
	 * Advance to the next initiative pass.
	 */
	private void newInitiativePass() {
		myInitiativePass++;

		// clear initiative map
		myInitiativeMap.clear();

		// for every trackable in combat, set initiative to 10 less than
		// current
		for (final IShadowrunCombatTrackable trackable : getAllCombatTrackables()) {
			final int initiative = trackable.getInitiative() - 10;

			// TODO check if still alive/operational
			// set initiative
			trackable.setInitiative(initiative);

			// set current initiative to the maximum of all rolled initiatives
			myCurrentInitiative = Math.max(myCurrentInitiative, initiative + 1);

			// add initiative to tracking
			final Set<IShadowrunCombatTrackable> trackablesForThisInitiative;
			if (myInitiativeMap.get(initiative) != null) {
				trackablesForThisInitiative = myInitiativeMap.get(initiative);
			} else {
				trackablesForThisInitiative = new HashSet<IShadowrunCombatTrackable>();
			}

			trackablesForThisInitiative.add(trackable);
			myInitiativeMap.put(initiative, trackablesForThisInitiative);
		}

		if (myCurrentInitiative > 0) {
			// TODO table should display new initiative scores

		} else {
			// if no one has initiative above 0, reroll initiative for new
			// combat turn
			rollInitiative();
		}
	}
}
