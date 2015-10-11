package games.rolePlayingGames.tracking.control;

import games.rolePlayingGames.tracking.trackable.ICombatTrackable;

import java.util.Collection;
import java.util.HashMap;

/**
 * Abstract tracking control.
 * 
 * @param <C>
 *            highest level combat trackable that is handled. Extends combat
 *            trackable.
 * 
 * @author Andrew
 */
public abstract class AbstractTrackingControl<C extends ICombatTrackable> {

	/**
	 * Currently in combat flag.
	 */
	private boolean myInCombat = false;

	/**
	 * All trackables (not just in current combat).
	 */
	private final HashMap<String, C> myAllTrackables = new HashMap<String, C>();

	/**
	 * Combat trackables. The trackables involved in the current combat.
	 */
	private final HashMap<String, C> myCombatTrackables = new HashMap<String, C>();

	/**
	 * Constructor.
	 */
	public AbstractTrackingControl() {
	}

	/**
	 * @return all trackables, from the map. Mirrors map.
	 */
	protected final Collection<C> getAllTrackables() {
		return myAllTrackables.values();
	}

	/**
	 * @return all combat trackables, from the map. Mirrors map.
	 */
	protected final Collection<C> getAllCombatTrackables() {
		return myCombatTrackables.values();
	}

	/**
	 * Remove the trackable from control entirely. Confirmation should be
	 * external to this.
	 * 
	 * @param iTrackable
	 *            trackable to remove.
	 */
	protected final void removeTrackable(final C iTrackable) {
		myAllTrackables.remove(iTrackable.getID());
		removeCombatTrackable(iTrackable);
	}

	/**
	 * Remove the trackable from the current combat tracking map. Confirmation
	 * should be external to this.
	 * 
	 * @param iTrackable
	 *            trackable to remove.
	 */
	protected final void removeCombatTrackable(final C iTrackable) {
		myCombatTrackables.remove(iTrackable.getID());
	}

	/**
	 * Add the trackable to control, if not already under control.
	 * 
	 * @param iTrackable
	 *            trackable to add.
	 */
	protected final void addTrackable(final C iTrackable) {
		if (myAllTrackables.get(iTrackable.getID()) == null) {
			myAllTrackables.put(iTrackable.getID(), iTrackable);
		}
	}

	/**
	 * Add the trackable to current combat control, if not already in combat. If
	 * added and if not in overall control, add there as well.
	 * 
	 * @param iTrackable
	 *            trackable to add.
	 */
	protected final void addCombatTrackable(final C iTrackable) {
		if (myCombatTrackables.get(iTrackable.getID()) == null) {
			myCombatTrackables.put(iTrackable.getID(), iTrackable);

			addTrackable(iTrackable);
		}
	}

	/**
	 * @return true if in combat, false otherwise.
	 */
	protected boolean isInCombat() {
		return myInCombat;
	}

	/**
	 * Set in combat.
	 * 
	 * @param iInCombat
	 *            true in combat, false otherwise.
	 */
	protected void setInCombat(final boolean iInCombat) {
		this.myInCombat = iInCombat;
	}

	/**
	 * Add a trackable.
	 */
	public abstract void addTrackable();

	/**
	 * Edit the selected.
	 */
	public abstract void editSelected();

	/**
	 * Start combat.
	 */
	public abstract void startCombat();

	/**
	 * End combat.
	 */
	public abstract void endCombat();

	/**
	 * Roll initiative for everyone.
	 */
	public abstract void rollInitiative();

	/**
	 * Advance the initiative to the next being.
	 */
	public abstract void advanceInitiative();

}
