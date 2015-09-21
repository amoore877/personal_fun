package games.rolePlayingGames.tracking.note.status;

import games.rolePlayingGames.tracking.note.AbstractNote;

/**
 * Abstract status effect note.
 * 
 * @author Andrew
 */
public abstract class AbstractStatusEffectNote extends AbstractNote implements
		IStatusEffectNote {

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
	public AbstractStatusEffectNote(final String iFullDesc,
			final String iBriefDesc) {
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
	public abstract String toFullString();

	@Override
	public abstract String toString();

	@Override
	public abstract void edit();

}
