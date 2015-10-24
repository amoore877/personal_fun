package games.rolePlayingGames.tracking.trackable.item;

import games.rolePlayingGames.tracking.UniqueObject;

/**
 * Abstract item.
 * 
 * @author Andrew
 */
public abstract class AbstractItem extends UniqueObject implements IItem {

	/**
	 * Name of the item.
	 */
	private String myName;

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name.
	 */
	public AbstractItem(final String iName) {
		super();

		if ((iName == null) || (iName.isEmpty())) {
			myName = "noName";
		} else {
			myName = iName;
		}
	}

	/**
	 * Set the name.
	 * 
	 * @param iName
	 *            new name.
	 */
	public final void setName(final String iName) {
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
	public String getName() {
		return myName;
	}

	@Override
	public abstract String toString();

}
