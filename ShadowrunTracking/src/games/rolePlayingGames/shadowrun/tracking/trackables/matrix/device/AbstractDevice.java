package games.rolePlayingGames.shadowrun.tracking.trackables.matrix.device;

import games.rolePlayingGames.shadowrun.tracking.notes.impl.DeviceMatrixDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.StatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.AbstractShadowrunItem;
import games.rolePlayingGames.shadowrun.util.ShadowrunCommonUtils;

import java.util.ArrayList;

/**
 * Abstract Shadowrun device.
 * 
 * @author Andrew
 *
 */
public abstract class AbstractDevice extends AbstractShadowrunItem implements
		IDevice {

	/**
	 * List of matrix damage notes.
	 */
	private final ArrayList<DeviceMatrixDamageNote> myMatrixDamageNotes = new ArrayList<DeviceMatrixDamageNote>();

	/**
	 * Status effects.
	 */
	private final ArrayList<StatusEffectNote> myStatusEffects = new ArrayList<StatusEffectNote>();

	/**
	 * Rating of the device.
	 */
	private int myRating;

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
	 */
	public AbstractDevice(final String iName, final int iBody,
			final int iArmor, final int iRating) {
		super(iName, iBody, iArmor);
		myRating = iRating;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final ArrayList<StatusEffectNote> getStatusEffectNotes() {
		return (ArrayList<StatusEffectNote>) myStatusEffects.clone();
	}

	@Override
	public final void removeStatusEffectNote(
			final StatusEffectNote iStatusEffectNote) {
		if (iStatusEffectNote != null) {
			myStatusEffects.remove(iStatusEffectNote);
		}
	}

	@Override
	public final void addStatusEffectNote(
			final StatusEffectNote iStatusEffectNote) {
		if (iStatusEffectNote != null) {
			myStatusEffects.add(iStatusEffectNote);
		}
	}

	@Override
	public abstract String toFullString();

	@Override
	public abstract void edit();

	@Override
	public final int getMaximumMatrixHealth() {
		return ShadowrunCommonUtils.getMaximumHealth(getRating());
	}

	@Override
	public final int getTotalMatrixDamage() {
		int oTotalDamage = 0;

		for (final DeviceMatrixDamageNote damageNote : myMatrixDamageNotes) {
			final int currentDamage = damageNote.getDamage();
			final int currentHealed = damageNote.getHealed();

			oTotalDamage += Math.max(0, (currentDamage - currentHealed));
		}

		return oTotalDamage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final ArrayList<DeviceMatrixDamageNote> getMatrixDamageNotes() {
		return (ArrayList<DeviceMatrixDamageNote>) myMatrixDamageNotes.clone();
	}

	@Override
	public final void removeMatrixDamageNote(
			final DeviceMatrixDamageNote iDamageNote) {
		if (iDamageNote != null) {
			myMatrixDamageNotes.remove(iDamageNote);
		}
	}

	@Override
	public final void addDamageNote(final DeviceMatrixDamageNote iDamageNote) {
		if (iDamageNote != null) {
			myMatrixDamageNotes.add(iDamageNote);
		}
	}

	@Override
	public abstract String toString();

	@Override
	public final int getRating() {
		return myRating;
	}

	/**
	 * Set rating.
	 * 
	 * @param iRating
	 *            new rating.
	 */
	protected final void setRating(final int iRating) {
		myRating = iRating;
	}
}
