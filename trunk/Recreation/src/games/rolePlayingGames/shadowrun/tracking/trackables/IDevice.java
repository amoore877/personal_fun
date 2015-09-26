package games.rolePlayingGames.shadowrun.tracking.trackables;

import games.rolePlayingGames.tracking.trackable.item.IItem;

/**
 * A device in Shadowrun.
 * 
 * @author Andrew
 */
public interface IDevice extends IItem, IMatrixDamageableTrackable {

	/**
	 * @return firewall attribute.
	 */
	int getFirewall();

	/**
	 * Set firewall attribute.
	 * 
	 * @param iFirewall
	 *            new firewall.
	 */
	void setFirewall(int iFirewall);

	/**
	 * @return data processing attribute.
	 */
	int getDataProcessing();

	/**
	 * Set data processing attribute.
	 * 
	 * @param iDataProcessing
	 *            new data processing attribute.
	 */
	void setDataProcessing(int iDataProcessing);
}
