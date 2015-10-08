package games.rolePlayingGames.shadowrun.tracking.trackables.impl.characters;

import games.rolePlayingGames.shadowrun.tracking.notes.impl.DeviceMatrixDamageNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.hacking.AbstractAgent;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.hacking.IPlayerAgent;

/**
 * An agent/sprite/program controlled by the player.
 * 
 * @author Andrew
 *
 */
public final class PlayerAgent extends AbstractAgent implements IPlayerAgent {

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name.
	 * @param iRating
	 *            rating.
	 */
	public PlayerAgent(final String iName, final int iRating) {
		super(iName, iRating);
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder();

		oResult.append("Name: " + getName() + " ");
		oResult.append("Rating: " + getRating() + " ");
		oResult.append("Initiative: " + getInitiative());

		for (final DeviceMatrixDamageNote damage : getMatrixDamageNotes()) {
			oResult.append(", Matrix Damage=" + damage.toString());
		}

		return oResult.toString();
	}

	@Override
	public String toString() {
		final StringBuilder oResult = new StringBuilder();

		oResult.append("Name: " + getName() + " ");

		if (getTotalMatrixDamage() >= getMaximumMatrixHealth()) {
			oResult.append("CRASHED");
		} else {
			oResult.append("Rating: " + getRating());
		}

		return oResult.toString();
	}
}
