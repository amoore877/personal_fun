package games.rolePlayingGames.shadowrun.tracking.notes.ability;

import games.rolePlayingGames.tracking.note.AbstractNote;

/**
 * Some ability (character skill, device active soft, spell, etc.).
 * 
 * @author Andrew
 */
public abstract class AbstractAbility extends AbstractNote {

	/**
	 * Constructor.
	 * 
	 * @param iFullDesc
	 *            full description of ability.
	 */
	public AbstractAbility(final String iFullDesc) {
		super(iFullDesc);
	}

	@Override
	public abstract String toFullString();

	@Override
	public abstract void edit();

	@Override
	public abstract String toString();

}
