package games.rolePlayingGames.shadowrun.tracking.control;

import games.rolePlayingGames.shadowrun.tracking.trackables.INonPlayer;
import games.rolePlayingGames.shadowrun.tracking.trackables.IPlayer;
import games.rolePlayingGames.shadowrun.tracking.trackables.IShadowrunCombatTrackable;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.TimedItem;
import games.rolePlayingGames.tracking.control.AbstractTrackingControl;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Shadowrun tracking control.
 * 
 * @author Andrew
 *
 */
public final class ShadowrunTrackingControl extends
		AbstractTrackingControl<IShadowrunCombatTrackable> {
	/**
	 * Theoretical maximum (all 6's on 5 dice + 12 on both initiative-related
	 * attributes = 54).
	 */
	private static final int THEORETICAL_MAX_INITIATIVE = 54;

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
		// first question if should be new or existing trackable
		// display to select a trackable
		final JPanel dialogPanel = new JPanel(new GridLayout(0, 1));

		// new or existing?
		final JPanel newOrExistingPanel = new JPanel(new GridLayout(1, 0));
		final JRadioButton newButton = new JRadioButton("New");
		final JRadioButton existingButton = new JRadioButton("Existing");
		final ButtonGroup newOrExistingGroup = new ButtonGroup();
		newOrExistingGroup.add(newButton);
		newOrExistingPanel.add(newButton);
		newOrExistingGroup.add(existingButton);
		newOrExistingPanel.add(existingButton);
		dialogPanel.add(newOrExistingPanel);

		final ArrayList<IShadowrunCombatTrackable> existingTrackables = new ArrayList<IShadowrunCombatTrackable>();
		for (final IShadowrunCombatTrackable existingTrackable : getAllTrackables()) {
			if (!getAllCombatTrackables().contains(existingTrackable)) {
				existingTrackables.add(existingTrackable);
			}
		}

		if (!isInCombat() || existingTrackables.isEmpty()) {
			existingButton.setEnabled(false);
		}

		// set default to new trackable
		newButton.setSelected(true);

		final int result = JOptionPane.showConfirmDialog(null, dialogPanel,
				"Add Trackable, new or existing?",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			ArrayList<IShadowrunCombatTrackable> trackablesToAdd;
			if (existingButton.isSelected()) {
				// load an existing trackable
				trackablesToAdd = new ArrayList<IShadowrunCombatTrackable>();
				trackablesToAdd.add(addExistingTrackable(existingTrackables));
			} else {
				// load a new trackable
				trackablesToAdd = addNewTrackable();
			}

			if (trackablesToAdd != null && !trackablesToAdd.isEmpty()) {
				if (!isInCombat()) {
					// if not in combat add new trackables to all overall
					// trackables only
					for (final IShadowrunCombatTrackable trackableToAdd : trackablesToAdd) {
						if (trackableToAdd != null) {
							addTrackable(trackableToAdd);
						} else {
							System.err.println("Null trackable detected.");
						}
					}
				} else {
					// if in combat, add new trackables to combat list, and
					// get initiative, add to initiative map, and, if needed,
					// act
					for (final IShadowrunCombatTrackable trackableToAdd : trackablesToAdd) {
						if (trackableToAdd != null) {
							// add to tracking
							addCombatTrackable(trackableToAdd);
							// roll initiative
							rollInitiative(trackableToAdd);
							// if initiative is greater than current
							// initiative, this trackable needs to act
							if (trackableToAdd.getInitiative() > myCurrentInitiative) {
								displayActingForTrackable(trackableToAdd);
							}
						} else {
							System.err.println("Null trackable detected.");
						}
					}
				}
			} else {
				System.out.println("Empty or null trackables list to add");
			}

		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected");
		} else {
			System.err.println("Unknown option");
		}

		// TODO refresh table?
	}

	/**
	 * Get a new trackable(s) from loaded XMLs (if NPC controlled) or input data
	 * (PC controlled).
	 * 
	 * @return trackable(s) to be added.
	 */
	private ArrayList<IShadowrunCombatTrackable> addNewTrackable() {
		final JPanel dialogPanel = new JPanel(new GridLayout(0, 1));

		// New: NPC or PC controlled?
		final JPanel npcOrPCPanel = new JPanel(new GridLayout(1, 0));
		final JRadioButton npcButton = new JRadioButton("NPC");
		final JRadioButton pcButton = new JRadioButton("PC");
		final ButtonGroup npcOrPCGroup = new ButtonGroup();
		npcOrPCGroup.add(npcButton);
		npcOrPCPanel.add(npcButton);
		npcOrPCGroup.add(pcButton);
		npcOrPCPanel.add(pcButton);
		dialogPanel.add(npcOrPCPanel);

		// set default to NPC
		npcButton.setSelected(true);

		final int result = JOptionPane.showConfirmDialog(null, dialogPanel,
				"Add Trackable, NPC or PC controlled?",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		ArrayList<IShadowrunCombatTrackable> oTrackablesToAdd = null;

		if (result == JOptionPane.OK_OPTION) {
			if (pcButton.isSelected()) {
				// New, PC controlled: combox, only show PC-controlled types
				oTrackablesToAdd = new ArrayList<IShadowrunCombatTrackable>();
				oTrackablesToAdd.add(addNewPCTrackable());
			} else {
				// New, NPC: combox, show loaded XMLs
				oTrackablesToAdd = addNewNPCTrackable();
			}
		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected");
		} else {
			System.err.println("Unknown option");
		}

		return oTrackablesToAdd;
	}

	/**
	 * Get new NPC trackable(s) from XMLs to add.
	 * 
	 * @return NPC trackable(s) to add.
	 */
	private ArrayList<IShadowrunCombatTrackable> addNewNPCTrackable() {
		// TODO New, NPC: combox, show loaded XMLs
		final JComboBox<Object> npcCombox = new JComboBox<Object>();
		return null;
	}

	/**
	 * Get new PC trackable from input data.
	 * 
	 * @return PC trackable to add.
	 */
	private IShadowrunCombatTrackable addNewPCTrackable() {
		// TODO New, PC controlled: combox, only show PC-controlled types
		return null;
	}

	/**
	 * Get an existing trackable from the given list for addition to combat.
	 * 
	 * @param iExistingTrackables
	 *            existing trackables not currently in combat.
	 * @return trackable to be added to combat.
	 */
	private IShadowrunCombatTrackable addExistingTrackable(
			final ArrayList<IShadowrunCombatTrackable> iExistingTrackables) {
		final JPanel dialogPanel = new JPanel(new GridLayout(0, 1));

		// Existing: list of trackables not in combat
		final JComboBox<IShadowrunCombatTrackable> existingTrackablesCombox = new JComboBox<IShadowrunCombatTrackable>(
				(IShadowrunCombatTrackable[]) iExistingTrackables.toArray());

		dialogPanel.add(existingTrackablesCombox);

		final int result = JOptionPane.showConfirmDialog(null, dialogPanel,
				"Existing trackable to add?", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		IShadowrunCombatTrackable oTrackableToAdd = null;

		if (result == JOptionPane.OK_OPTION) {
			oTrackableToAdd = (IShadowrunCombatTrackable) existingTrackablesCombox
					.getSelectedItem();
		} else if (result == JOptionPane.CLOSED_OPTION) {
			System.out.println("Cancel selected");
		} else {
			System.err.println("Unknown option");
		}

		// return selection
		return oTrackableToAdd;
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
		// confirmation
		final int result = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to start combat?", "Start combat?",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// add all PCs to combat
			for (final IShadowrunCombatTrackable trackable : getAllTrackables()) {
				if (trackable instanceof IPlayer) {
					addCombatTrackable(trackable);
				}
			}

			// present options to add any NPCs to combat
			final JPanel npcAdditionPanel = new JPanel(new GridLayout(0, 1));

			final HashMap<JCheckBox, INonPlayer> nonPlayerCheckBoxMap = new HashMap<JCheckBox, INonPlayer>();

			for (final IShadowrunCombatTrackable trackable : getAllCombatTrackables()) {
				if (trackable instanceof INonPlayer) {
					final JCheckBox npcCheckbox = new JCheckBox(
							trackable.toString(), true);
					npcAdditionPanel.add(npcCheckbox);
					nonPlayerCheckBoxMap.put(npcCheckbox,
							(INonPlayer) trackable);
				}
			}
			if (!nonPlayerCheckBoxMap.isEmpty()) {
				final int addResult = JOptionPane
						.showConfirmDialog(null, npcAdditionPanel,
								"Which NPCs should be added?",
								JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.PLAIN_MESSAGE);

				if (addResult == JOptionPane.OK_OPTION) {
					for (final Entry<JCheckBox, INonPlayer> entry : nonPlayerCheckBoxMap
							.entrySet()) {
						final JCheckBox checkbox = entry.getKey();
						if (checkbox.isSelected()) {
							addCombatTrackable(entry.getValue());
						}
					}
				}
			}

			// set combat status to true
			setInCombat(true);

			// roll initiative for all in-combat
			rollInitiative();

			// TODO make table display only in-combat trackables now

			// TODO start combat button should be disabled, end combat enabled
		}
	}

	/**
	 * End combat. Clear combat tracking list.
	 */
	@Override
	public void endCombat() {
		// confirmation
		final int result = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to end combat?", "End combat?",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// remove NPCs from game?
			final JPanel npcRemovalPanel = new JPanel(new GridLayout(0, 1));

			final HashMap<JCheckBox, INonPlayer> nonPlayerCheckBoxMap = new HashMap<JCheckBox, INonPlayer>();

			for (final IShadowrunCombatTrackable trackable : getAllCombatTrackables()) {
				if (trackable instanceof INonPlayer) {
					final JCheckBox npcCheckbox = new JCheckBox(
							trackable.toString(), true);
					npcRemovalPanel.add(npcCheckbox);
					nonPlayerCheckBoxMap.put(npcCheckbox,
							(INonPlayer) trackable);
				}
			}
			if (!nonPlayerCheckBoxMap.isEmpty()) {
				final int removeResult = JOptionPane
						.showConfirmDialog(null, npcRemovalPanel,
								"Which NPCs should be removed?",
								JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.PLAIN_MESSAGE);

				if (removeResult == JOptionPane.OK_OPTION) {
					for (final Entry<JCheckBox, INonPlayer> entry : nonPlayerCheckBoxMap
							.entrySet()) {
						final JCheckBox checkbox = entry.getKey();
						if (checkbox.isSelected()) {
							removeTrackable(entry.getValue());
						}
					}
				}
			}

			// clear combat tracking list
			getAllCombatTrackables().clear();

			// set combat status to false
			setInCombat(false);

			// TODO make table display all trackables now

			// TODO end combat button should be disabled, start combat enabled
		}
	}

	@Override
	protected void rollInitiative() {
		// clear initiative tracking
		myInitiativeMap.clear();

		// loop through all in-combat trackables and roll initiative
		for (final IShadowrunCombatTrackable trackable : getAllCombatTrackables()) {
			rollInitiative(trackable);
		}

		// first initiative pass
		myInitiativePass = 1;
		// set current initiative to theoretical maximum
		myCurrentInitiative = THEORETICAL_MAX_INITIATIVE;

		// TODO table should display new initiative scores
	}

	/**
	 * Roll initiative for the given trackable, adding to initiative map.
	 * 
	 * @param iTrackable
	 *            trackable to roll initiative for.
	 */
	private void rollInitiative(final IShadowrunCombatTrackable iTrackable) {
		final int initiative;

		if (iTrackable instanceof INonPlayer) {
			// non-player trackable; program rolls initiative
			// dialog penalty input dialog
			int penalty = 0;
			boolean validEntry = false;
			while (!validEntry) {
				try {
					penalty = Integer.parseInt(JOptionPane.showInputDialog(
							null,
							"Initiative penalties for: "
									+ iTrackable.toFullString(), 0));
					validEntry = true;
				} catch (final Exception iException) {
					// TODO specific exceptions
				}
			}
			// calculate
			initiative = ((INonPlayer) iTrackable).rollInitiative(penalty);
		} else if (iTrackable instanceof TimedItem) {
			// timed item; usually maintains its initiative
			int tempInitiative = iTrackable.getInitiative();
			boolean validEntry = false;
			while (!validEntry) {
				try {
					tempInitiative = Integer.parseInt(JOptionPane
							.showInputDialog(
									null,
									"Initiative for timed item: "
											+ iTrackable.toString(),
									tempInitiative));
					validEntry = true;
				} catch (final Exception iException) {
					// TODO specific exceptions
				}
			}

			initiative = tempInitiative;
		} else {
			// assumed player-controlled; prompt for initiative score
			// dialog input display
			int tempInitiative = 0;
			boolean validEntry = false;
			while (!validEntry) {
				try {
					tempInitiative = Integer.parseInt(JOptionPane
							.showInputDialog(null, "Initiative for: "
									+ iTrackable.toString(), 0));
					validEntry = true;
				} catch (final Exception iException) {
					// TODO specific exceptions
				}
			}

			initiative = tempInitiative;
		}

		// set initiative
		iTrackable.setInitiative(initiative);

		// add initiative to tracking
		final Set<IShadowrunCombatTrackable> trackablesForThisInitiative;
		if (myInitiativeMap.get(initiative) != null) {
			trackablesForThisInitiative = myInitiativeMap.get(initiative);
		} else {
			trackablesForThisInitiative = new HashSet<IShadowrunCombatTrackable>();
		}

		trackablesForThisInitiative.add(iTrackable);
		myInitiativeMap.put(initiative, trackablesForThisInitiative);
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
		// input display for all currently-acting trackables
		displayActingForTrackables(trackables);
	}

	/**
	 * Display action dialog for the given trackables.
	 * 
	 * @param iTrackables
	 *            trackables to act.
	 */
	private void displayActingForTrackables(
			final Collection<IShadowrunCombatTrackable> iTrackables) {
		for (final IShadowrunCombatTrackable trackable : iTrackables) {
			displayActingForTrackable(trackable);
		}
	}

	/**
	 * Display action dialog for the given trackable. Non-modal.
	 * 
	 * @param iTrackable
	 *            trackable to act.
	 */
	private void displayActingForTrackable(
			final IShadowrunCombatTrackable iTrackable) {
		// TODO Auto-generated method stub
		// TODO non-modal
	}

	/**
	 * Advance to the next initiative pass.
	 */
	private void newInitiativePass() {
		myInitiativePass++;

		// clear initiative map
		myInitiativeMap.clear();

		int highestInitiative = 0;

		// for every trackable in combat, set initiative to 10 less than
		// current, except for timed items
		for (final IShadowrunCombatTrackable trackable : getAllCombatTrackables()) {
			final int initiative;
			if (trackable instanceof TimedItem) {
				initiative = trackable.getInitiative();
			} else {
				initiative = trackable.getInitiative() - 10;
			}

			// TODO check if still alive/operational
			// set initiative
			trackable.setInitiative(initiative);

			// set current initiative to the maximum of all rolled initiatives
			highestInitiative = Math.max(highestInitiative, initiative + 1);

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

		myCurrentInitiative = THEORETICAL_MAX_INITIATIVE;

		if (highestInitiative > 0) {
			// TODO table should display new initiative scores

		} else {
			// if no one has initiative above 0, reroll initiative for new
			// combat turn
			rollInitiative();
		}
	}
}
