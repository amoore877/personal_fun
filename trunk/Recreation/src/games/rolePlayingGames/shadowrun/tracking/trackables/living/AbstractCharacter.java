package games.rolePlayingGames.shadowrun.tracking.trackables.living;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.notes.damage.character.AbstractCharacterDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.CharacterPhysicalDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.CharacterStunDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.StatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.notes.quality.IShadowrunQualityNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.IShadowrunItem;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Abstract Shadowrun character.
 * 
 * @author Andrew
 *
 */
public abstract class AbstractCharacter extends
		AbstractLivingBeing<AbstractCharacterDamageNote> implements
		IShadowrunCharacter {

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name.
	 * @param iEssence
	 *            essence, rounded down.
	 * @param iBody
	 *            body.
	 * @param iWillpower
	 *            willpower.
	 * @param iSpecial
	 *            magic/resonance.
	 * @param iStatusEffects
	 *            status effects.
	 * @param iInventory
	 *            inventory.
	 * @param iQualities
	 *            qualities.
	 */
	public AbstractCharacter(final String iName, final int iEssence,
			final int iBody, final int iWillpower, final int iSpecial,
			final ArrayList<StatusEffectNote> iStatusEffects,
			final ArrayList<IShadowrunItem> iInventory,
			final ArrayList<IShadowrunQualityNote> iQualities) {
		super(iName, iEssence, iBody, iWillpower, iSpecial, iStatusEffects,
				iInventory, iQualities);
	}

	@Override
	public final int getTotalStunDamage() {
		int oResult = 0;

		for (final AbstractCharacterDamageNote damageNote : getDamageNotes()) {
			if (damageNote instanceof CharacterStunDamageNote) {
				final int currentDamage = damageNote.getDamage();
				final int currentHealed = damageNote.getHealed();

				oResult += Math.max(0, currentDamage - currentHealed);
			}
		}

		return oResult;
	}

	@Override
	public final String toString() {
		final StringBuilder oResult = new StringBuilder();

		oResult.append("Name=" + getName());
		if (getTotalDamage() >= getMaximumHealth()) {
			oResult.append("DYING");
		} else if (getTotalStunDamage() >= getMaximumStunHealth()) {
			oResult.append("STUNNED");
		}

		return oResult.toString();
	}

	@Override
	public abstract String toFullString();

	@Override
	public final void edit() {
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));
		// name
		final JTextField nameField = ShadowrunTrackingUtil.addStringField(
				editPanel, "Name", getName());

		// initiative
		final JFormattedTextField initiativeField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Initiative", getInitiative());

		// current damage notes
		ShadowrunTrackingUtil.addDamageButtons(editPanel, this);

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this Spirit", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// name
			ShadowrunTrackingUtil.examineChangedString(nameField, "Name",
					(s) -> setName(s), () -> getName());

			// initiative
			ShadowrunTrackingUtil.examineChangedInt(initiativeField,
					"Initiative", (i) -> setInitiative(i),
					() -> getInitiative());

		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	@Override
	public final int getTotalDamage() {
		int oResult = 0;

		for (final AbstractCharacterDamageNote damageNote : getDamageNotes()) {
			if (damageNote instanceof CharacterPhysicalDamageNote) {
				final int currentDamage = damageNote.getDamage();
				final int currentHealed = damageNote.getHealed();

				oResult += Math.max(0, currentDamage - currentHealed);
			}
		}

		return oResult;
	}

}
