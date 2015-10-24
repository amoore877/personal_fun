package games.rolePlayingGames.tracking;

/**
 * Interface for uniquely identifiable objects.
 * 
 * @author Andrew
 */
public interface IUniqueObject {

	/**
	 * @return unique ID.
	 */
	String getID();

	/**
	 * Equals (by unique ID only).
	 */
	@Override
	boolean equals(final Object iObject);
}
