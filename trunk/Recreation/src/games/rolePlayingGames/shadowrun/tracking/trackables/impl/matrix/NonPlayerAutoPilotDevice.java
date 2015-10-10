package games.rolePlayingGames.shadowrun.tracking.trackables.impl.matrix;

import games.rolePlayingGames.shadowrun.tracking.notes.ability.AbstractAbility;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.DeviceMatrixDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.ItemPhysicalDamageNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.IShadowrunItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.device.AbstractAutoPilotDevice;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.device.INonPlayerAutoPilotDevice;

import java.util.ArrayList;

/**
 * Non-player auto-pilot device/drone.
 * 
 * @author Andrew
 *
 */
public final class NonPlayerAutoPilotDevice extends AbstractAutoPilotDevice
		implements INonPlayerAutoPilotDevice {

	/**
	 * Data processing.
	 */
	private final int myDataProcessing;

	/**
	 * Firewall.
	 */
	private final int myFirewall;

	/**
	 * Pilot.
	 */
	private final int myPilot;

	/**
	 * Abilities.
	 */
	private final ArrayList<AbstractAbility> myAbilities;

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name.
	 * @param iBody
	 *            body.
	 * @param iArmor
	 *            armor.
	 * @param iRating
	 *            rating.
	 * @param iInventory
	 *            inventory.
	 * @param iAbilities
	 *            abilities.
	 * @param iDataProcessing
	 *            data processing.
	 * @param iFirewall
	 *            firewall.
	 */
	public NonPlayerAutoPilotDevice(final String iName, final int iBody,
			final int iArmor, final int iRating,
			final ArrayList<IShadowrunItem> iInventory,
			final ArrayList<AbstractAbility> iAbilities,
			final int iDataProcessing, final int iFirewall, final int iPilot) {
		super(iName, iBody, iArmor, iRating, iInventory);

		myDataProcessing = iDataProcessing;
		myFirewall = iFirewall;
		myPilot = iPilot;

		if (iAbilities == null) {
			myAbilities = new ArrayList<AbstractAbility>();
		} else {
			myAbilities = iAbilities;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AbstractAbility> getAbilities() {
		return (ArrayList<AbstractAbility>) myAbilities.clone();
	}

	@Override
	public int getFirewall() {
		return myFirewall;
	}

	@Override
	public int getDataProcessing() {
		return myDataProcessing;
	}

	@Override
	public int getPilot() {
		return myPilot;
	}

	@Override
	public String toFullString() {
		// TODO
		final StringBuilder oResult = new StringBuilder();

		oResult.append("Name: " + getName() + " ");
		oResult.append("Rating: " + getRating() + " ");
		oResult.append("Pilot: " + getPilot() + " ");
		oResult.append("Initiative: " + getInitiative());

		for (final ItemPhysicalDamageNote damage : getDamageNotes()) {
			oResult.append(", Damage=" + damage.toString());
		}

		for (final DeviceMatrixDamageNote damage : getMatrixDamageNotes()) {
			oResult.append(", Matrix Damage=" + damage.toString());
		}

		for (final AbstractAbility ability : getAbilities()) {
			oResult.append(", Ability=" + ability.toString());
		}

		for (final IShadowrunItem item : getInventory()) {
			oResult.append(", Item=" + item.toString());
		}

		return oResult.toString();
	}

	@Override
	public String toString() {
		final StringBuilder oResult = new StringBuilder();

		oResult.append("Name: " + getName() + " ");
		if (getTotalDamage() >= getMaximumHealth()) {
			oResult.append("BUSTED");
		} else if (getTotalMatrixDamage() >= getMaximumMatrixHealth()) {
			oResult.append("CRASHED");
		} else {
			oResult.append("Rating: " + getRating() + " ");
			oResult.append("Pilot: " + getPilot() + " ");
			oResult.append("Initiative: " + getInitiative());
		}

		return oResult.toString();
	}

}
