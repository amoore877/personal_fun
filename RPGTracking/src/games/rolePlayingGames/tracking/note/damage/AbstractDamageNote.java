package games.rolePlayingGames.tracking.note.damage;

import games.rolePlayingGames.tracking.note.AbstractNote;

/**
 * Abstract note for damage.
 * 
 * @author Andrew
 */
public abstract class AbstractDamageNote extends AbstractNote implements
		IDamageNote {

	/**
	 * Damage amount.
	 */
	private int myDamage;

	/**
	 * Healed amount.
	 */
	private int myHealed;

	/**
	 * Constructor. Default healed amount to 0.
	 * 
	 * @param iDesc
	 *            note description.
	 * @param iDamage
	 *            damage amount.
	 */
	public AbstractDamageNote(final String iDesc, final int iDamage) {
		this(iDesc, iDamage, 0);
	}

	/**
	 * Constructor.
	 * 
	 * @param iDesc
	 *            note description.
	 * @param iDamage
	 *            damage amount.
	 * @param iHealed
	 *            healed amount.
	 */
	public AbstractDamageNote(final String iDesc, final int iDamage,
			final int iHealed) {
		super(iDesc);

		if (iDamage < 0) {
			System.out.println("Damage [" + iDamage
					+ "] is less than negative. Setting to 0.");
			myDamage = 0;
		} else {
			myDamage = iDamage;
		}

		if (iHealed < 0) {
			System.out.println("Healed amount [" + iHealed
					+ "] is negative. Setting to 0.");

			myHealed = 0;
		} else if (iHealed <= getDamage()) {
			myHealed = iHealed;
		} else {
			System.out.println("Healed amount [" + iHealed
					+ "] is greater than damage amount [" + getDamage()
					+ "]. Setting to damage amount.");

			myHealed = getDamage();
		}
	}

	@Override
	public final int getDamage() {
		return myDamage;
	}

	@Override
	public final int getHealed() {
		return myHealed;
	}

	/**
	 * Set the amount of damage.
	 * 
	 * @param iDamage
	 *            damage amount.
	 */
	protected final void setDamage(final int iDamage) {
		int newDamageAmount;

		if (iDamage < 0) {
			System.out.println("Damage [" + iDamage
					+ "] is less than negative. Setting to 0.");
			newDamageAmount = 0;
		} else {
			newDamageAmount = iDamage;
		}

		System.out.println("Damage changing from [" + myDamage + "] to ["
				+ newDamageAmount + "]");

		myDamage = newDamageAmount;
	}

	/**
	 * Set the amount healed.
	 * 
	 * @param iHealed
	 *            healed amount.
	 */
	protected final void setHealed(final int iHealed) {
		int newHealedAmount;

		if (iHealed < 0) {
			System.out.println("Healed amount [" + iHealed
					+ "] is negative. Setting to 0.");

			newHealedAmount = 0;
		} else if (iHealed <= getDamage()) {
			newHealedAmount = iHealed;
		} else {
			System.out.println("Healed amount [" + iHealed
					+ "] is greater than damage amount [" + getDamage()
					+ "]. Setting to damage amount.");

			newHealedAmount = getDamage();
		}

		System.out.println("Healed changing from [" + myHealed + "] to ["
				+ newHealedAmount + "]");

		myHealed = newHealedAmount;
	}

	/**
	 * Heal some amount of the damage.
	 * 
	 * @param iHeal
	 *            heal this amount.
	 */
	protected final void heal(final int iHeal) {
		setHealed(getHealed() + iHeal);
	}

	@Override
	public abstract String toString();
}
