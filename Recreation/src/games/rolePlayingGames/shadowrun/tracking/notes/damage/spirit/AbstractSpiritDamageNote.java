package games.rolePlayingGames.shadowrun.tracking.notes.damage.spirit;

import games.rolePlayingGames.tracking.note.damage.AbstractDamageNote;

/**
 * Shadowrun damage note.
 * 
 * In Shadowrun, spirit wounds are treated distinctly from each other. In
 * addition:
 * 
 * 1. A wound can only be healed magically, once.
 * 
 * 2. Wounds from drain (and some other sources) cannot be healed except through
 * natural processes.
 * 
 * @author Andrew
 */
public abstract class AbstractSpiritDamageNote extends AbstractDamageNote
		implements ISpiritWoundNote {

	/**
	 * Magically healed flag.
	 */
	private boolean myMagicallyHealed = false;

	/**
	 * Natural healing only flag.
	 */
	private boolean myNaturalOnly;

	/**
	 * Constructor.
	 * 
	 * @param iDesc
	 *            very brief note as to cause of damage (ex. "shot", "manaball",
	 *            "stab", etc.).
	 * @param iDamage
	 *            amount of damage.
	 * @param iNaturalOnly
	 *            true if the wound can only be healed naturally, false
	 *            otherwise.
	 */
	public AbstractSpiritDamageNote(final String iDesc, final int iDamage,
			final boolean iNaturalOnly) {
		super(iDesc, iDamage);
		myNaturalOnly = iNaturalOnly;
	}

	@Override
	public boolean isMagicallyHealed() {
		return myMagicallyHealed;
	}

	/**
	 * Set healed.
	 * 
	 * @param iMagicallyHealed
	 *            true if partially healed, false otherwise.
	 */
	protected final void setMagicallyHealed(final boolean iMagicallyHealed) {
		this.myMagicallyHealed = iMagicallyHealed;
	}

	@Override
	public boolean isNaturalOnly() {
		return myNaturalOnly;
	}

	/**
	 * Set natural healing only.
	 * 
	 * @param iNaturalOnly
	 *            true if the wound can only be healed naturally, false
	 *            otherwise.
	 */
	protected final void setNaturalOnly(final boolean iNaturalOnly) {
		myNaturalOnly = iNaturalOnly;
	}

	@Override
	public abstract String toString();
}
