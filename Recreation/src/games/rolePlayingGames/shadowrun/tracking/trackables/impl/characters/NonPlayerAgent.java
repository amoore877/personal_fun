package games.rolePlayingGames.shadowrun.tracking.trackables.impl.characters;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.notes.ability.AbstractAbility;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.hacking.AbstractAgent;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.hacking.INonPlayerAgent;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * An agent/sprite/program not controlled by the player.
 * 
 * @author Andrew
 *
 */
public final class NonPlayerAgent extends AbstractAgent implements
		INonPlayerAgent {

	/**
	 * Attack.
	 */
	private int myAttack;

	/**
	 * Sleaze.
	 */
	private int mySleaze;

	/**
	 * Data processing.
	 */
	private int myDataProcessing;

	/**
	 * Firewall.
	 */
	private int myFirewall;

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

	/**
	 * Set attack.
	 * 
	 * @param iAttack
	 *            new attack.
	 */
	private void setAttack(final int iAttack) {
		myAttack = iAttack;
	}

	@Override
	public int getSleaze() {
		return mySleaze;
	}

	/**
	 * Set sleaze.
	 * 
	 * @param iSleaze
	 *            new sleaze.
	 */
	private void setSleaze(final int iSleaze) {
		mySleaze = iSleaze;
	}

	@Override
	public int getFirewall() {
		return myFirewall;
	}

	/**
	 * Set firewall.
	 * 
	 * @param iFirewall
	 *            new firewall.
	 */
	private void setFirewall(final int iFirewall) {
		myFirewall = iFirewall;
	}

	@Override
	public int getDataProcessing() {
		return myDataProcessing;
	}

	/**
	 * Set data processing.
	 * 
	 * @param iDataProcessing
	 *            new data processing.
	 */
	private void setDataProcessing(final int iDataProcessing) {
		myDataProcessing = iDataProcessing;
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

		return oResult.toString();
	}

	@Override
	public void edit() {
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));
		// name
		final JTextField nameField = ShadowrunTrackingUtil.addStringField(
				editPanel, "Name", getName());

		// initiative
		final JFormattedTextField initiativeField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Initiative", getInitiative());

		// rating
		final JFormattedTextField ratingField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Rating", getRating());

		// attack
		final JFormattedTextField attackField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Attack", getAttack());

		// sleaze
		final JFormattedTextField sleazeField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Sleaze", getSleaze());

		// data processing
		final JFormattedTextField dataField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Data Processing", getDataProcessing());

		// firewall
		final JFormattedTextField firewallField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Firewall", getFirewall());

		// current matrix damage notes
		ShadowrunTrackingUtil.addMatrixDamageButtons(editPanel, this);

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this Agent", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// name
			ShadowrunTrackingUtil.examineChangedString(nameField, "Name",
					(s) -> setName(s), () -> getName());

			// initiative
			ShadowrunTrackingUtil.examineChangedInt(initiativeField,
					"Initiative", (i) -> setInitiative(i),
					() -> getInitiative());

			// rating
			ShadowrunTrackingUtil.examineChangedInt(ratingField, "Rating",
					(i) -> setRating(i), () -> getRating());

			// attack
			ShadowrunTrackingUtil.examineChangedInt(attackField, "Attack",
					(i) -> setAttack(i), () -> getAttack());

			// sleaze
			ShadowrunTrackingUtil.examineChangedInt(sleazeField, "Sleaze",
					(i) -> setSleaze(i), () -> getSleaze());

			// data processing
			ShadowrunTrackingUtil.examineChangedInt(dataField,
					"Data Processing", (i) -> setDataProcessing(i),
					() -> getDataProcessing());

			// firewall
			ShadowrunTrackingUtil.examineChangedInt(firewallField, "Firewall",
					(i) -> setFirewall(i), () -> getFirewall());
		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	@Override
	public String toString() {
		final StringBuilder oResult = new StringBuilder();

		oResult.append("Name: " + getName() + " ");
		oResult.append("Rating: " + getRating() + " ");
		oResult.append("Attack: " + getAttack() + " ");
		oResult.append("Sleaze: " + getSleaze() + " ");
		oResult.append("Data Processing: " + getDataProcessing() + " ");
		oResult.append("Firewall: " + getFirewall() + " ");

		return oResult.toString();
	}

}
