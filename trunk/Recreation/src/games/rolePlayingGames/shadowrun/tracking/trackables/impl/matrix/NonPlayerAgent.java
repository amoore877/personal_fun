package games.rolePlayingGames.shadowrun.tracking.trackables.impl.matrix;

import games.rolePlayingGames.shadowrun.tracking.notes.ability.AbstractAbility;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.DeviceMatrixDamageNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.hacking.AbstractAgent;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.hacking.INonPlayerAgent;

import java.util.ArrayList;

/**
 * An agent/sprite/program/host not controlled by the player.
 * 
 * @author Andrew
 *
 */
public final class NonPlayerAgent extends AbstractAgent implements
		INonPlayerAgent {

	/**
	 * Attack.
	 */
	private final int myAttack;

	/**
	 * Sleaze.
	 */
	private final int mySleaze;

	/**
	 * Data processing.
	 */
	private final int myDataProcessing;

	/**
	 * Firewall.
	 */
	private final int myFirewall;

	/**
	 * Matrix abilities/programs.
	 */
	private final ArrayList<AbstractAbility> myAbilities;

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name.
	 * @param iRating
	 *            rating.
	 * @param iAttack
	 *            attack.
	 * @param iSleaze
	 *            sleaze.
	 * @param iDataProcessing
	 *            data processing.
	 * @param iFirewall
	 *            firewall.
	 * @param iAbilities
	 *            list of abilities.
	 */
	public NonPlayerAgent(final String iName, final int iRating,
			final int iAttack, final int iSleaze, final int iDataProcessing,
			final int iFirewall, final ArrayList<AbstractAbility> iAbilities) {
		super(iName, iRating);
		myAttack = iAttack;
		mySleaze = iSleaze;
		myDataProcessing = iDataProcessing;
		myFirewall = iFirewall;
		myAbilities = iAbilities;
	}

	@Override
	public int getAttack() {
		return myAttack;
	}

	@Override
	public int getSleaze() {
		return mySleaze;
	}

	@Override
	public int getFirewall() {
		return myFirewall;
	}

	@Override
	public int getDataProcessing() {
		return myDataProcessing;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AbstractAbility> getAbilities() {
		return (ArrayList<AbstractAbility>) myAbilities.clone();
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder();

		oResult.append("Name: " + getName() + " ");
		oResult.append("Rating: " + getRating() + " ");
		oResult.append("Attack: " + getAttack() + " ");
		oResult.append("Sleaze: " + getSleaze() + " ");
		oResult.append("Data Processing: " + getDataProcessing() + " ");
		oResult.append("Firewall: " + getFirewall() + " ");
		oResult.append("Initiative: " + getInitiative() + " ");

		for (final AbstractAbility ability : getAbilities()) {
			oResult.append(ability.toString() + ",");
		}

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
			oResult.append("Rating: " + getRating() + " ");
			oResult.append("Attack: " + getAttack() + " ");
			oResult.append("Sleaze: " + getSleaze() + " ");
			oResult.append("Data Processing: " + getDataProcessing() + " ");
			oResult.append("Firewall: " + getFirewall() + " ");
		}

		return oResult.toString();
	}

}
