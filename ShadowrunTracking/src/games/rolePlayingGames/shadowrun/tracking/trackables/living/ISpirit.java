package games.rolePlayingGames.shadowrun.tracking.trackables.living;

import games.rolePlayingGames.shadowrun.tracking.notes.damage.spirit.AbstractSpiritDamageNote;

/**
 * A living, breathing, spirit in Shadowrun.
 * 
 * @author Andrew
 *
 */
public interface ISpirit extends ILivingBeing<AbstractSpiritDamageNote> {

	/**
	 * @return number of services.
	 */
	int getServices();

	/**
	 * Use some number of services, either through actual use or banishment.
	 * 
	 * @param iServices
	 *            services to use.
	 */
	void useServices(int iServices);
}