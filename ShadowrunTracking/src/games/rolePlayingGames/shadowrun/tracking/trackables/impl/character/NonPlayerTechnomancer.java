package games.rolePlayingGames.shadowrun.tracking.trackables.impl.character;

import games.rolePlayingGames.shadowrun.tracking.notes.ability.AbstractAbility;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.DeviceMatrixDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.QualityNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.StatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.AbstractShadowrunItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.hacking.INonPlayerHackingBeing;
import games.rolePlayingGames.shadowrun.util.ShadowrunCommonUtils;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 * NPC technomancer.
 * 
 * @author Andrew
 */
public final class NonPlayerTechnomancer extends NonPlayerCharacter implements
		INonPlayerHackingBeing {

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
	 * @param iAgility
	 *            agility.
	 * @param iReaction
	 *            reaction.
	 * @param iStrength
	 *            strength.
	 * @param iLogic
	 *            logic.
	 * @param iIntuition
	 *            intuition.
	 * @param iCharisma
	 *            charisma.
	 * @param iInitDice
	 *            initiative dice.
	 * @param iAbilities
	 *            abilities.
	 */
	public NonPlayerTechnomancer(final String iName, final int iEssence,
			final int iBody, final int iWillpower, final int iSpecial,
			final ArrayList<StatusEffectNote> iStatusEffects,
			final ArrayList<AbstractShadowrunItem> iInventory,
			final ArrayList<QualityNote> iQualities, final int iAgility,
			final int iReaction, final int iStrength, final int iLogic,
			final int iIntuition, final int iCharisma, final int iInitDice,
			final ArrayList<AbstractAbility> iAbilities) {
		super(iName, iEssence, iBody, iWillpower, iSpecial, iStatusEffects,
				iInventory, iQualities, iAgility, iReaction, iStrength, iLogic,
				iIntuition, iCharisma, iInitDice, iAbilities);
	}

	/**
	 * Unsupported for technomancer.
	 */
	@Override
	public int getMaximumMatrixHealth() {
		System.err.println("Unsupported for technomancer.");
		return 0;
	}

	/**
	 * Unsupported for technomancer.
	 */
	@Override
	public int getTotalMatrixDamage() {
		System.err.println("Unsupported for technomancer.");
		return 0;
	}

	/**
	 * Unsupported for technomancer.
	 */
	@Override
	public ArrayList<DeviceMatrixDamageNote> getMatrixDamageNotes() {
		System.err.println("Unsupported for technomancer.");
		return new ArrayList<DeviceMatrixDamageNote>();
	}

	/**
	 * Unsupported for technomancer.
	 */
	@Override
	public void removeMatrixDamageNote(final DeviceMatrixDamageNote iDamageNote) {
		System.err.println("Unsupported for technomancer.");
	}

	/**
	 * Unsupported for technomancer.
	 */
	@Override
	public void addDamageNote(final DeviceMatrixDamageNote iDamageNote) {
		System.err.println("Unsupported for technomancer.");
	}

	/**
	 * Equal to resonance rating.
	 */
	@Override
	public int getRating() {
		return getSpecialAtt();
	}

	/**
	 * Equal to willpower.
	 */
	@Override
	public int getFirewall() {
		return getWillpower();
	}

	/**
	 * Equal to logic.
	 */
	@Override
	public int getDataProcessing() {
		return getLogic();
	}

	/**
	 * Equal to charisma.
	 */
	@Override
	public int getAttack() {
		return getCharisma();
	}

	/**
	 * Equal to intuition.
	 */
	@Override
	public int getSleaze() {
		return getIntuition();
	}

	@Override
	public int rollInitiative(final int iPenalties) {
		// prompt for initiative type
		final JRadioButton meatButton = new JRadioButton("Meat ");
		meatButton.setMnemonic(KeyEvent.VK_M);
		final JRadioButton arButton = new JRadioButton("AR ");
		arButton.setMnemonic(KeyEvent.VK_R);
		final JRadioButton hotVRButton = new JRadioButton("Hot VR ");
		hotVRButton.setMnemonic(KeyEvent.VK_H);
		final ButtonGroup initiativeTypeButtonGroup = new ButtonGroup();
		initiativeTypeButtonGroup.add(meatButton);
		initiativeTypeButtonGroup.add(arButton);
		initiativeTypeButtonGroup.add(hotVRButton);

		meatButton.setSelected(true);

		JOptionPane.showConfirmDialog(null, initiativeTypeButtonGroup,
				"Initiative type for: " + getName(),
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (arButton.isSelected()) {
			return ShadowrunCommonUtils.rollARInitiative(getIntuition(),
					getReaction(), getInitDice()) - iPenalties;
		} else if (hotVRButton.isSelected()) {
			return ShadowrunCommonUtils.rollHotVRInitiative(
					getDataProcessing(), getIntuition(), getInitDice())
					- iPenalties;
		} else {
			// assume meat
			return ShadowrunCommonUtils.rollInitiative(getIntuition(),
					getReaction(), getInitDice()) - iPenalties;
		}
	}
}