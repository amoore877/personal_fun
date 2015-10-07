package games.rolePlayingGames.shadowrun.tracking.notes.impl;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.tracking.note.AbstractNote;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Basic note for tracking. A simple string, and list of String IDs that are
 * related to the note.
 * 
 * @author Andrew
 */
public final class ShadowrunBasicNote extends AbstractNote {

	/**
	 * List of IDs that are related to this note.
	 */
	private final ArrayList<String> myRelatedIDs;

	/**
	 * Constructor.
	 * 
	 * @param iFullDesc
	 *            note description.
	 */
	public ShadowrunBasicNote(final String iFullDesc,
			final ArrayList<String> iRelatedIDs) {
		super(iFullDesc);

		if (iRelatedIDs == null) {
			myRelatedIDs = new ArrayList<String>();
		} else {
			myRelatedIDs = iRelatedIDs;
		}
	}

	@Override
	public void edit() {
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));

		final JTextField descriptionField = ShadowrunTrackingUtil
				.addStringField(editPanel, "Description", getFullDesc());

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this note", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			ShadowrunTrackingUtil.examineChangedString(descriptionField,
					"Desc", (s) -> setFullDesc(s), () -> getFullDesc());
		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	/**
	 * @return clone of list of IDs for this note.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getRelatedIDs() {
		return (ArrayList<String>) myRelatedIDs.clone();
	}

	@Override
	public String toString() {
		return getFullDesc();
	}

	@Override
	public String toFullString() {
		return getFullDesc();
	}
}
