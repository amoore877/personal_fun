package games.rolePlayingGames.tracking.note.status.trait;

import games.rolePlayingGames.tracking.note.AbstractNote;

/**
 * Abstract trait note.
 * 
 * @author Andrew
 */
public abstract class AbstractTraitNote extends AbstractNote implements
		ITraitNote {

	/**
	 * Brief description.
	 */
	private String myBriefDesc;

	/**
	 * Constructor.
	 * 
	 * @param iFullDesc
	 *            note description.
	 * @param iBriefDesc
	 *            brief (one or two short words) description of effect.
	 */
	public AbstractTraitNote(final String iFullDesc, final String iBriefDesc) {
		super(iFullDesc);

		if (iBriefDesc == null) {
			myBriefDesc = "";
		} else {
			myBriefDesc = iBriefDesc;
		}
	}

	/**
	 * Set the note's description.
	 * 
	 * @param iBriefDesc
	 *            new description.
	 */
	protected final void setBriefDesc(final String iBriefDesc) {
		System.out.println("Brief Desc changing from [" + myBriefDesc
				+ "] to [" + iBriefDesc + "]");

		if (iBriefDesc == null) {
			myBriefDesc = "";
		} else {
			myBriefDesc = iBriefDesc;
		}
	}

	@Override
	public final String getBriefDesc() {
		return myBriefDesc;
	}

	@Override
	public abstract String toString();
}
