package games.rolePlayingGames.tracking.note;

import games.rolePlayingGames.tracking.UniqueObject;

public abstract class AbstractNote extends UniqueObject implements
		IFullDescNote {

	/**
	 * The note's string.
	 */
	private String myFullDesc;

	/**
	 * Constructor.
	 * 
	 * @param iFullDesc
	 *            note description.
	 */
	public AbstractNote(final String iFullDesc) {
		super();

		if (iFullDesc == null) {
			myFullDesc = "";
		} else {
			myFullDesc = iFullDesc;
		}
	}

	@Override
	public final String getFullDesc() {
		return myFullDesc;
	}

	/**
	 * Set the note's description.
	 * 
	 * @param iFullDesc
	 *            new description.
	 */
	protected final void setFullDesc(final String iFullDesc) {
		System.out.println("Full Desc changing from [" + myFullDesc + "] to ["
				+ iFullDesc + "]");

		if (iFullDesc == null) {
			myFullDesc = "";
		} else {
			myFullDesc = iFullDesc;
		}
	}

	@Override
	public abstract String toString();
}
