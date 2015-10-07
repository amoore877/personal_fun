package games.rolePlayingGames.shadowrun.tracking.trackables.matrix.hacking;

/**
 * Abstract agent/program/sprite.
 * 
 * @author Andrew
 *
 */
public abstract class AbstractAgent extends AbstractHackingBeing implements
		IAgent {

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name.
	 * @param iRating
	 *            rating.
	 */
	public AbstractAgent(final String iName, final int iRating) {
		super(iName, iRating);
	}

	@Override
	public abstract String toFullString();

	@Override
	public abstract void edit();

	@Override
	public abstract String toString();

}
