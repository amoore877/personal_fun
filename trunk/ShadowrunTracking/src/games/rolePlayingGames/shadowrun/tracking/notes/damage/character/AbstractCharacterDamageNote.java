package games.rolePlayingGames.shadowrun.tracking.notes.damage.character;

import games.rolePlayingGames.tracking.note.damage.AbstractDamageNote;

/**
 * Shadowrun damage note.
 * 
 * In Shadowrun, wounds are treated distinctly from each other. In addition:
 * 
 * 1. A wound can only be healed physically once.
 * 
 * 2. A wound can only be healed magically once.
 * 
 * 3. Once a wound has been healed magically, it cannot be healed physically.
 * 
 * 4. Wounds from drain (and some other sources) cannot be healed except through
 * natural processes.
 * 
 * @author Andrew
 */
public abstract class AbstractCharacterDamageNote extends AbstractDamageNote
		implements ICharacterWoundNote {

	/**
	 * Physically healed flag.
	 */
	private boolean myPhysicallyHealed = false;

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
	public AbstractCharacterDamageNote(final String iDesc, final int iDamage,
			final boolean iNaturalOnly) {
		super(iDesc, iDamage);
		myNaturalOnly = iNaturalOnly;
	}

	/**
	 * @return true if physically healed, false otherwise.
	 */
	@Override
	public boolean isPhysicallyHealed() {
		return myPhysicallyHealed;
	}

	/**
	 * Set physically healed.
	 * 
	 * @param iPhysicallyHealed
	 *            true if physically healed, false otherwise.
	 */
	protected void setPhysicallyHealed(final boolean iPhysicallyHealed) {
		this.myPhysicallyHealed = iPhysicallyHealed;
	}

	@Override
	public boolean isMagicallyHealed() {
		return myMagicallyHealed;
	}

	/**
	 * Set magically healed.
	 * 
	 * @param iMagicallyHealed
	 *            true if magically healed, false otherwise.
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
