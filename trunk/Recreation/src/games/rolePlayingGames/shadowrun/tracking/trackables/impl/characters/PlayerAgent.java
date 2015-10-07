package games.rolePlayingGames.shadowrun.tracking.trackables.impl.characters;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.hacking.AbstractAgent;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.hacking.IPlayerAgent;

import java.awt.GridLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		oResult.append("Rating: " + getRating());

		return oResult.toString();
	}
}
