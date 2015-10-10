package games.rolePlayingGames.shadowrun.tracking.trackables.impl.matrix;

import games.rolePlayingGames.shadowrun.tracking.notes.impl.DeviceMatrixDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.ItemPhysicalDamageNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.IShadowrunItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.device.AbstractAutoPilotDevice;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.device.IPlayerAutoPilotDevice;

import java.util.ArrayList;

/**
 * A player-controlled auto-pilot device/drone.
 * 
 * @author Andrew
 *
 */
public final class PlayerAutoPilotDevice extends AbstractAutoPilotDevice
		implements IPlayerAutoPilotDevice {

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
	 *            inventory the device/drone has.
	 */
	public PlayerAutoPilotDevice(final String iName, final int iBody,
			final int iArmor, final int iRating,
			final ArrayList<IShadowrunItem> iInventory) {
		super(iName, iBody, iArmor, iRating, iInventory);
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder();

		oResult.append("Name: " + getName() + " ");
		oResult.append("Rating: " + getRating() + " ");
		oResult.append("Initiative: " + getInitiative());

		for (final ItemPhysicalDamageNote damage : getDamageNotes()) {
			oResult.append(", Damage=" + damage.toString());
		}

		for (final DeviceMatrixDamageNote damage : getMatrixDamageNotes()) {
			oResult.append(", Matrix Damage=" + damage.toString());
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
			oResult.append("Initiative: " + getInitiative());
		}

		return oResult.toString();
	}

}
