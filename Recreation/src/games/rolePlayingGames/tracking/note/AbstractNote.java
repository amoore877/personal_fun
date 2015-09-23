package games.rolePlayingGames.tracking.note;

import games.rolePlayingGames.tracking.UniqueObject;

public abstract class AbstractNote extends UniqueObject implements
		IFullDescNote {

	/**
	 * The note's string.
	 */
	private String myBriefDesc;

	/**
	 * Constructor.
	 * 
	 * @param iFullDesc
	 *            note description.
	 */
	public AbstractNote(final String iFullDesc) {
		super();

		if (iFullDesc == null) {
			myBriefDesc = "";
		} else {
			myBriefDesc = iFullDesc;
		}
	}

	@Override
	public final String getFullDesc() {
		return myBriefDesc;
	}

	/**
	 * Set the note's description.
	 * 
	 * @param iFullDesc
	 *            new description.
	 */
	protected final void setFullDesc(final String iFullDesc) {
		System.out.println("Full Desc changing from [" + myBriefDesc + "] to ["
				+ iFullDesc + "]");

		if (iFullDesc == null) {
			myBriefDesc = "";
		} else {
			myBriefDesc = iFullDesc;
		}
	}

	@Override
	public abstract String toString();
}
