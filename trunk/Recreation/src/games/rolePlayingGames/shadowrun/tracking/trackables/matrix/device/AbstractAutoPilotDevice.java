package games.rolePlayingGames.shadowrun.tracking.trackables.matrix.device;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.StatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.notes.status.IShadowrunStatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.IShadowrunItem;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	public ArrayList<StatusEffectNote> getStatusEffectNotes() {
		return (ArrayList<StatusEffectNote>) myStatusEffects.clone();
	}

	@Override
	public void removeStatusEffectNote(final StatusEffectNote iStatusEffectNote) {
		if (iStatusEffectNote != null) {
			myStatusEffects.remove(iStatusEffectNote);
		}
	}

	@Override
	public void addStatusEffectNote(final StatusEffectNote iStatusEffectNote) {
		if (iStatusEffectNote != null) {
			myStatusEffects.add(iStatusEffectNote);
		}
	}

	@Override
	public abstract String toFullString();

	@Override
	public void edit() {
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));
		// name
		final JTextField nameField = ShadowrunTrackingUtil.addStringField(
				editPanel, "Name", getName());

		// initiative
		final JFormattedTextField initiativeField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Initiative", getInitiative());

		// current damage notes
		ShadowrunTrackingUtil.addDamageButtons(editPanel, this);

		// current matrix damage notes
		ShadowrunTrackingUtil.addMatrixDamageButtons(editPanel, this);

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this Drone", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// name
			ShadowrunTrackingUtil.examineChangedString(nameField, "Name",
					(s) -> setName(s), () -> getName());

			// initiative
			ShadowrunTrackingUtil.examineChangedInt(initiativeField,
					"Initiative", (i) -> setInitiative(i),
					() -> getInitiative());
		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	@Override
	public abstract String toString();
}
