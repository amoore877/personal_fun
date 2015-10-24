package games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.DeviceMatrixDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.ItemPhysicalDamageNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.device.AbstractDevice;

import java.awt.GridLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Some Shadowrun device.
 * 
 * @author Andrew
 */
public final class Device extends AbstractDevice {

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
	 */
	public Device(final String iName, final int iBody, final int iArmor,
			final int iRating) {
		super(iName, iBody, iArmor, iRating);
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder();

		oResult.append("Name: " + getName());

		oResult.append("Rating: " + getRating());

		for (final ItemPhysicalDamageNote damageNote : getDamageNotes()) {
			oResult.append(", Damage-" + damageNote.toString());
		}
		for (final DeviceMatrixDamageNote damageNote : getMatrixDamageNotes()) {
			oResult.append(", Matrix Damage-" + damageNote.toString());
		}

		return oResult.toString();
	}

	@Override
	public void edit() {
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));

		// name
		final JTextField nameField = ShadowrunTrackingUtil.addStringField(
				editPanel, "Name", getName());

		// rating
		final JFormattedTextField ratingField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Rating", getRating());

		// current damage notes
		ShadowrunTrackingUtil.addDamageButtons(editPanel, this);

		// current matrix damage notes
		ShadowrunTrackingUtil.addMatrixDamageButtons(editPanel, this);

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this armor", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// name
			ShadowrunTrackingUtil.examineChangedString(nameField, "Name",
					(s) -> setName(s), () -> getName());

			// rating
			ShadowrunTrackingUtil.examineChangedInt(ratingField, "Rating",
					(i) -> setRating(i), () -> getRating());
		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	@Override
	public String toString() {
		final StringBuilder oResult = new StringBuilder();

		oResult.append("Name: " + getName());

		if (getTotalDamage() > 0) {
			oResult.append("DAMAGED");
		} else if (getTotalMatrixDamage() >= getMaximumMatrixHealth()) {
			oResult.append("CRASHED");
		} else {
			oResult.append("Rating: " + getRating());
		}

		return oResult.toString();
	}

}
