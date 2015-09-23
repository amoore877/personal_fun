package games.rolePlayingGames.tracking;

import java.util.UUID;

/**
 * A unique object (has some UUID).
 * 
 * @author Andrew
 */
public abstract class UniqueObject implements IUniqueObject {

	/**
	 * ID.
	 */
	private final String myID;

	/**
	 * Constructor.
	 */
	public UniqueObject() {
		myID = UUID.randomUUID().toString();
	}

	@Override
	public final String getID() {
		return myID;
	}

	@Override
	public final boolean equals(final Object iObject) {
		if (iObject instanceof UniqueObject) {
			return ((UniqueObject) iObject).getID().equals(myID);
		} else {
			return false;
		}
	}
}
