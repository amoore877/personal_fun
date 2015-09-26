package games.rolePlayingGames.shadowrun.tracking.trackables;

/**
 * A Shadowrun device/persona/icon/being that has Attack/Sleaze.
 * 
 * @author Andrew
 */
public interface IHackingDevice extends IDevice {

	/**
	 * @return attack attribute.
	 */
	int getAttack();

	/**
	 * Set the attack attribute.
	 * 
	 * @param iAttack
	 *            new attack.
	 */
	void setAttack(int iAttack);

	/**
	 * @return sleaze attribute.
	 */
	int getSleaze();

	/**
	 * Set the sleaze attribute.
	 * 
	 * @param iSleaze
	 *            new sleaze.
	 */
	void setSleaze(int iSleaze);
}
