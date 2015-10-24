package games.rolePlayingGames.shadowrun.tracking.trackables.matrix.hacking;

import games.rolePlayingGames.shadowrun.tracking.notes.impl.DeviceMatrixDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.StatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.notes.status.IShadowrunStatusEffectNote;
import games.rolePlayingGames.shadowrun.util.ShadowrunCommonUtils;
import games.rolePlayingGames.tracking.UniqueObject;

import java.util.ArrayList;

/**
 * Some hacking being.
 * 
 * @author Andrew
 *
 */
public abstract class AbstractHackingBeing extends UniqueObject implements
		IHackingBeing {
	/**
	 * Name.
	 */
	private String myName;

	/**
	 * Initiative.
	 */
	private int myInitiative = 0;

	/**
	 * List of matrix damage notes.
	 */
	private final ArrayList<DeviceMatrixDamageNote> myMatrixDamageNotes = new ArrayList<DeviceMatrixDamageNote>();

	/**
	 * Rating of the device.
	 */
	private int myRating;

	/**
	 * Status effects.
	 */
	private final ArrayList<IShadowrunStatusEffectNote> myStatusEffects = new ArrayList<IShadowrunStatusEffectNote>();

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name.
	 * @param iRating
	 *            rating.
	 */
	public AbstractHackingBeing(final String iName, final int iRating) {
		super();
		myName = iName;
		myRating = iRating;
	}

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

	@Override
	public final String getName() {
		return myName;
	}

	/**
	 * Set name.
	 * 
	 * @param iName
	 *            name.
	 */
	public final void setName(final String iName) {
		myName = iName;
	}

	@Override
	public abstract String toFullString();

	@Override
	public abstract void edit();

	@Override
	public abstract String toString();

	@Override
	public int getInitiative() {
		return myInitiative;
	}

	@Override
	public final void setInitiative(final int iInitiative) {
		myInitiative = iInitiative;
	}

	@SuppressWarnings("unchecked")
	@Override
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
}
