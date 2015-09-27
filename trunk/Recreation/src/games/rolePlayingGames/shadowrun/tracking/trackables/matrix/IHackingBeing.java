package games.rolePlayingGames.shadowrun.tracking.trackables.matrix;

import games.rolePlayingGames.tracking.trackable.ICombatTrackable;

/**
 * A Shadowrun device/persona/icon/being that has Attack/Sleaze. Can enter
 * combat.
 * 
 * @author Andrew
 */
public interface IHackingBeing extends IMatrixDamageableTrackable,
		ICombatTrackable {

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
