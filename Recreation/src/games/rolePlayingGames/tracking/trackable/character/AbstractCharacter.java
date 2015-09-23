package games.rolePlayingGames.tracking.trackable.character;

import games.rolePlayingGames.tracking.UniqueObject;
import games.rolePlayingGames.tracking.note.damage.IDamageNote;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract character.
 * 
 * @param <D>
 *            most high-level damage that this trackable can hold.
 * 
 * @author Andrew
 */
public abstract class AbstractCharacter<D extends IDamageNote> extends
		UniqueObject implements ICharacter<D> {

	/**
	 * Name of this character.
	 */
	private String myName;

	/**
	 * Initiative.
	 */
	private int myInitiative;

	/**
	 * Maximum health.
	 */
	private int myMaximumHealth;

	/**
	 * List of damage notes.
	 */
	private final ArrayList<D> myDamageNotes = new ArrayList<D>();

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name.
	 */
	public AbstractCharacter(final String iName) {
		this(iName, 0);
	}

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name.
	 * @param iInitiative
	 *            initiative.
	 */
	public AbstractCharacter(final String iName, final int iInitiative) {
		this(iName, iInitiative, new ArrayList<D>());
	}

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name.
	 * @param iInitiative
	 *            initiative.
	 * @param iDamageNotes
	 *            current damage notes.
	 */
	public AbstractCharacter(final String iName, final int iInitiative,
			final List<D> iDamageNotes) {
		super();

		if ((iName == null) || (iName.isEmpty())) {
			myName = "noName";
		} else {
			myName = iName;
		}

		myInitiative = iInitiative;

		if (iDamageNotes != null) {
			myDamageNotes.addAll(iDamageNotes);
		}
	}

	@Override
	public final int getInitiative() {
		return myInitiative;
	}

	@Override
	public final void setInitiative(final int iInitiative) {
		System.out.println("Initiative changing from [" + myInitiative
				+ "] to [" + iInitiative + "].");

		myInitiative = iInitiative;
	}

	@Override
	public final String getName() {
		return myName;
	}

	/**
	 * Set the name.
	 * 
	 * @param iName
	 *            new name.
	 */
	protected final void setName(final String iName) {
		if ((iName == null) || (iName.isEmpty())) {
			System.err.println("Given name [" + iName + "]. Name [" + myName
					+ "] will be unchanged.");
		} else {
			System.out.println("Name changing from [" + myName + "] to ["
					+ iName + "].");

			myName = iName;
		}
	}

	@Override
	public abstract String toString();

	@Override
	public int getMaximumHealth() {
		return myMaximumHealth;
	}

	/**
	 * Set the maximum health.
	 * 
	 * @param iMaximumHealth
	 *            new maximum health.
	 */
	protected final void setMaximumHealth(final int iMaximumHealth) {
		if (iMaximumHealth < 1) {
			System.err.println("Given maximum health [" + iMaximumHealth
					+ "]. Maximum health [" + myMaximumHealth
					+ "] will be unchanged.");
		} else {
			System.out.println("Maximum health changing from ["
					+ myMaximumHealth + "] to [" + iMaximumHealth + "].");

			myMaximumHealth = iMaximumHealth;
		}
	}

	@Override
	public int getTotalDamage() {
		int oTotalDamage = 0;

		for (final D damageNote : myDamageNotes) {
			final int currentDamage = damageNote.getDamage();
			final int currentHealed = damageNote.getHealed();

			oTotalDamage += Math.max(0, (currentDamage - currentHealed));
		}

		return oTotalDamage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<D> getDamageNotes() {
		return (ArrayList<D>) myDamageNotes.clone();
	}

	@Override
	public void removeDamageNote(final D iDamageNote) {
		if (iDamageNote != null) {
			myDamageNotes.remove(iDamageNote);
		}
	}

	@Override
	public void addDamageNote(final D iDamageNote) {
		if (iDamageNote != null) {
			myDamageNotes.add(iDamageNote);
		}
	}
}
