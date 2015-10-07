package games.rolePlayingGames.shadowrun.tracking.trackables.matrix.device;

import games.rolePlayingGames.shadowrun.tracking.notes.status.IShadowrunStatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.IShadowrunItem;

import java.util.ArrayList;

/**
 * Abstract auto-pilot device.
 * 
 * @author Andrew
 */
public abstract class AbstractAutoPilotDevice extends AbstractDevice implements
		IAutoPilotDevice {

	/**
	 * Initiative.
	 */
	private int myInitiative = 0;

	/**
	 * Inventory.
	 */
	private final ArrayList<IShadowrunItem> myInventory;

	/**
	 * Status effects.
	 */
	private final ArrayList<IShadowrunStatusEffectNote> myStatusEffects = new ArrayList<IShadowrunStatusEffectNote>();

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name of device.
	 * @param iBody
	 *            body.
	 * @param iArmor
	 *            armor.
	 * @param iRating
	 *            rating.
	 * @param iInventory
	 *            inventory
	 */
	public AbstractAutoPilotDevice(final String iName, final int iBody,
			final int iArmor, final int iRating,
			final ArrayList<IShadowrunItem> iInventory) {
		super(iName, iBody, iArmor, iRating);

		if (iInventory == null) {
			myInventory = new ArrayList<IShadowrunItem>();
		} else {
			myInventory = iInventory;
		}
	}

	@Override
	public int getInitiative() {
		return myInitiative;
	}

	@Override
	public void setInitiative(final int iInitiative) {
		myInitiative = iInitiative;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<IShadowrunItem> getInventory() {
		return (ArrayList<IShadowrunItem>) myInventory.clone();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<IShadowrunStatusEffectNote> getStatusEffectNotes() {
		return (ArrayList<IShadowrunStatusEffectNote>) myStatusEffects.clone();
	}

	@Override
	public void removeStatusEffectNote(
			final IShadowrunStatusEffectNote iStatusEffectNote) {
		if (iStatusEffectNote != null) {
			myStatusEffects.remove(iStatusEffectNote);
		}
	}

	@Override
	public void addStatusEffectNote(
			final IShadowrunStatusEffectNote iStatusEffectNote) {
		if (iStatusEffectNote != null) {
			myStatusEffects.add(iStatusEffectNote);
		}
	}

	@Override
	public abstract String toFullString();

	@Override
	public abstract void edit();

	@Override
	public abstract String toString();
}
