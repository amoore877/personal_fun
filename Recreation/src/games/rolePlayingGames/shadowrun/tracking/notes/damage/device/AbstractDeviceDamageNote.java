package games.rolePlayingGames.shadowrun.tracking.notes.damage.device;

import games.rolePlayingGames.tracking.note.damage.AbstractDamageNote;

/**
 * Shadowrun damage note.
 * 
 * In Shadowrun, device wounds are not really treated distinctly from each
 * other, but it makes for better notes to do so. In addition:
 * 
 * 1. Physical and Matrix drone wounds are healed with hardware tests.
 * 
 * @author Andrew
 */
public abstract class AbstractDeviceDamageNote extends AbstractDamageNote
		implements IDeviceWoundNote {

	/**
	 * Constructor.
	 * 
	 * @param iDesc
	 *            very brief note as to cause of damage (ex. "shot", "manaball",
	 *            "stab", etc.).
	 * @param iDamage
	 *            amount of damage.
	 */
	public AbstractDeviceDamageNote(final String iDesc, final int iDamage) {
		super(iDesc, iDamage);
	}

	@Override
	public abstract String toString();
}
