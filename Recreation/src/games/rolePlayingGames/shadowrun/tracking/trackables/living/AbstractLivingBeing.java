package games.rolePlayingGames.shadowrun.tracking.trackables.living;

import games.rolePlayingGames.shadowrun.tracking.notes.damage.IShadowrunDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.StatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.notes.quality.IShadowrunQualityNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.IShadowrunItem;
import games.rolePlayingGames.shadowrun.util.ShadowrunCommonUtils;
import games.rolePlayingGames.tracking.UniqueObject;

import java.util.ArrayList;

/**
 * Some abstract living being in Shadowrun.
 * 
 * @author Andrew
 *
 * @param <D>
 *            highest level damage note the being uses.
 */
public abstract class AbstractLivingBeing<D extends IShadowrunDamageNote>
		extends UniqueObject implements ILivingBeing<D> {

	/**
	 * Name.
	 */
	private String myName;

	/**
	 * Initiative.
	 */
	private int myInitiative = 0;

	/**
	 * Essence.
	 */
	private int myEssence;

	/**
	 * Body.
	 */
	private int myBody;

	/**
	 * Willpower.
	 */
	private int myWillpower;

	/**
	 * Special attribute (magic/resonance).
	 */
	private int mySpecial;

	/**
	 * Damage notes.
	 */
	private final ArrayList<D> myDamageNotes = new ArrayList<D>();

	/**
	 * Status effects.
	 */
	private final ArrayList<StatusEffectNote> myStatusEffects;

	/**
	 * Inventory.
	 */
	private final ArrayList<IShadowrunItem> myInventory;

	/**
	 * Qualities.
	 */
	private final ArrayList<IShadowrunQualityNote> myQualities;

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name.
	 * @param iEssence
	 *            essence, rounded down.
	 * @param iBody
	 *            body.
	 * @param iWillpower
	 *            willpower.
	 * @param iSpecial
	 *            magic/resonance.
	 * @param iStatusEffects
	 *            status effects.
	 * @param iInventory
	 *            inventory.
	 * @param iQualities
	 *            qualities.
	 */
	public AbstractLivingBeing(final String iName, final int iEssence,
			final int iBody, final int iWillpower, final int iSpecial,
			final ArrayList<StatusEffectNote> iStatusEffects,
			final ArrayList<IShadowrunItem> iInventory,
			final ArrayList<IShadowrunQualityNote> iQualities) {
		if (iName == null || iName.isEmpty()) {
			myName = "noName";
		} else {
			myName = iName;
		}

		myEssence = iEssence;
		myBody = iBody;
		myWillpower = iWillpower;
		mySpecial = iSpecial;

		if (iStatusEffects == null) {
			myStatusEffects = new ArrayList<StatusEffectNote>();
		} else {
			myStatusEffects = iStatusEffects;
		}

		if (iInventory == null) {
			myInventory = new ArrayList<IShadowrunItem>();
		} else {
			myInventory = iInventory;
		}

		if (iQualities == null) {
			myQualities = new ArrayList<IShadowrunQualityNote>();
		} else {
			myQualities = iQualities;
		}
	}

	@Override
	public final String getName() {
		return myName;
	}

	/**
	 * Set name.
	 * 
	 * @param iName
	 *            new name.
	 */
	protected final void setName(final String iName) {
		myName = iName;
	}

	@Override
	public abstract String toFullString();

	@Override
	public abstract String toString();

	@Override
	public abstract void edit();

	@Override
	public final int getInitiative() {
		return myInitiative;
	}

	@Override
	public final void setInitiative(final int iInitiative) {
		myInitiative = iInitiative;
	}

	@Override
	public final int getBody() {
		return myBody;
	}

	/**
	 * Set body.
	 * 
	 * @param iBody
	 *            new body.
	 */
	protected final void setBody(final int iBody) {
		myBody = iBody;
	}

	@Override
	public final int getWillpower() {
		return myWillpower;
	}

	/**
	 * Set willpower.
	 * 
	 * @param iWillpower
	 *            new willpower.
	 */
	protected final void setWillpower(final int iWillpower) {
		myWillpower = iWillpower;
	}

	/**
	 * Maximum physical health.
	 */
	@Override
	public final int getMaximumHealth() {
		return ShadowrunCommonUtils.getMaximumHealth(getBody());
	}

	/**
	 * Total lethal damage.
	 */
	@Override
	public abstract int getTotalDamage();

	/**
	 * @return maximum stun health.
	 */
	@Override
	public final int getMaximumStunHealth() {
		return ShadowrunCommonUtils.getMaximumHealth(getWillpower());
	}

	/**
	 * @return total stun damage.
	 */
	@Override
	public abstract int getTotalStunDamage();

	@SuppressWarnings("unchecked")
	@Override
	public final ArrayList<D> getDamageNotes() {
		return (ArrayList<D>) myDamageNotes.clone();
	}

	@Override
	public final void removeDamageNote(final D iDamageNote) {
		if (iDamageNote != null) {
			myDamageNotes.remove(iDamageNote);
		}
	}

	@Override
	public final void addDamageNote(final D iDamageNote) {
		if (iDamageNote != null) {
			myDamageNotes.add(iDamageNote);
		}
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

	@SuppressWarnings("unchecked")
	@Override
	public final ArrayList<IShadowrunQualityNote> getTraitNotes() {
		return (ArrayList<IShadowrunQualityNote>) myQualities.clone();
	}

	@Override
	public final void removeTraitNote(final IShadowrunQualityNote iTraitNote) {
		if (iTraitNote != null) {
			myQualities.remove(iTraitNote);
		}
	}

	@Override
	public final void addTraitNote(final IShadowrunQualityNote iTraitNote) {
		if (iTraitNote != null) {
			myQualities.add(iTraitNote);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public final ArrayList<IShadowrunItem> getInventory() {
		return (ArrayList<IShadowrunItem>) myInventory.clone();
	}

	@Override
	public final int getEssence() {
		return myEssence;
	}

	/**
	 * Set essence.
	 * 
	 * @param iEssence
	 *            new essence.
	 */
	protected final void setEssence(final int iEssence) {
		myEssence = iEssence;
	}

	@Override
	public final int getSpecialAtt() {
		return mySpecial;
	}

	/**
	 * Set magic/resonance.
	 * 
	 * @param iSpecial
	 *            new magic/resonance.
	 */
	protected final void setSpecialAtt(final int iSpecial) {
		mySpecial = iSpecial;
	}
}
