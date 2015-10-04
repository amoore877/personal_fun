package games.rolePlayingGames.shadowrun.tracking.trackables.living;

import games.rolePlayingGames.shadowrun.tracking.trackables.INonPlayer;

/**
 * A Shadowrun NPC living being. Need details.
 * 
 * @author Andrew
 */
public interface INonPlayerLivingBeing extends INonPlayer {

	/**
	 * @return physical limit.
	 */
	int getPhysicalLimit();

	/**
	 * @return mental limit.
	 */
	int getMentalLimit();

	/**
	 * @return social limit.
	 */
	int getSocialLimit();

	/**
	 * @return body attribute.
	 */
	int getBody();

	/**
	 * @return agility attribute.
	 */
	int getAgility();

	/**
	 * @return reaction attribute.
	 */
	int getReaction();

	/**
	 * @return strength attribute.
	 */
	int getStrength();

	/**
	 * @return willpower attribute.
	 */
	int getWillpower();

	/**
	 * @return logic attribute.
	 */
	int getLogic();

	/**
	 * @return intuition attribute.
	 */
	int getIntuition();

	/**
	 * @return charisma attribute.
	 */
	int getCharisma();

	/**
	 * @return number of initiative dice.
	 */
	int getInitDice();
}
