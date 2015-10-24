package games.rolePlayingGames.shadowrun.tracking.trackables;

/**
 * Shadowrun NPC of some sort. Need skill details.
 * 
 * @author Andrew
 *
 */
public interface INonPlayer extends IAbleActor {

	/**
	 * @return number of initiative dice.
	 */
	int getInitDice();

	/**
	 * Roll initiative with given penalties.
	 * 
	 * @param iPenalties
	 *            penalties.
	 * @return initiative rolled.
	 */
	int rollInitiative(int iPenalties);
}
