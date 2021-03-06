package games.rolePlayingGames.shadowrun.tracking.trackables.impl.character;

import games.rolePlayingGames.shadowrun.tracking.notes.ability.AbstractAbility;
import games.rolePlayingGames.shadowrun.tracking.notes.damage.character.AbstractCharacterDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.QualityNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.StatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.notes.quality.IShadowrunQualityNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.Armor;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.AbstractShadowrunItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.IShadowrunItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.living.AbstractCharacter;
import games.rolePlayingGames.shadowrun.tracking.trackables.living.INonPlayerLivingBeing;
import games.rolePlayingGames.shadowrun.util.ShadowrunCommonUtils;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 * An NPC. Not a hacker, rigger, or technomancer.
 * 
 * @author Andrew
 *
 */
public class NonPlayerCharacter extends AbstractCharacter implements
		INonPlayerLivingBeing {

	/**
	 * Agility.
	 */
	private final int myAgility;

	/**
	 * Reaction.
	 */
	private final int myReaction;

	/**
	 * Strength.
	 */
	private final int myStrength;

	/**
	 * Logic.
	 */
	private final int myLogic;

	/**
	 * Intuition.
	 */
	private final int myIntuition;

	/**
	 * Charisma.
	 */
	private final int myCharisma;

	/**
	 * Initiative dice.
	 */
	private final int myInitDice;

	/**
	 * Abilities.
	 */
	private final ArrayList<AbstractAbility> myAbilities;

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
	public NonPlayerCharacter(final String iName, final int iEssence,
			final int iBody, final int iWillpower, final int iSpecial,
			final ArrayList<StatusEffectNote> iStatusEffects,
			final ArrayList<AbstractShadowrunItem> iInventory,
			final ArrayList<QualityNote> iQualities, final int iAgility,
			final int iReaction, final int iStrength, final int iLogic,
			final int iIntuition, final int iCharisma, final int iInitDice,
			final ArrayList<AbstractAbility> iAbilities) {
		super(iName, iEssence, iBody, iWillpower, iSpecial, iStatusEffects,
				iInventory, iQualities);

		myAgility = iAgility;
		myReaction = iReaction;
		myStrength = iStrength;
		myLogic = iLogic;
		myIntuition = iIntuition;
		myCharisma = iCharisma;
		myInitDice = iInitDice;

		if (iAbilities == null) {
			myAbilities = new ArrayList<AbstractAbility>();
		} else {
			myAbilities = iAbilities;
		}
	}

	@Override
	public final int getPhysicalLimit() {
		return ShadowrunCommonUtils.getPhysicalLimit(getStrength(), getBody(),
				getReaction());
	}

	@Override
	public final String toFullString() {
		final StringBuilder oResult = new StringBuilder();

		oResult.append("Name=" + getName());

		for (final AbstractCharacterDamageNote damageNote : getDamageNotes()) {
			oResult.append(", Damage=" + damageNote.toString());
		}

		for (final IShadowrunQualityNote quality : getTraitNotes()) {
			oResult.append(", Quality=" + quality.getBriefDesc());
		}

		for (final IShadowrunItem item : getInventory()) {
			oResult.append(", Item=" + item.toString());
		}

		return oResult.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final ArrayList<AbstractAbility> getAbilities() {
		return (ArrayList<AbstractAbility>) myAbilities.clone();
	}

	@Override
	public final int getMentalLimit() {
		return ShadowrunCommonUtils.getMentalLimit(getLogic(), getIntuition(),
				getWillpower());
	}

	@Override
	public final int getSocialLimit() {
		return ShadowrunCommonUtils.getSocialLimit(getCharisma(),
				getWillpower(), getEssence());
	}

	@Override
	public final int getAgility() {
		return myAgility;
	}

	@Override
	public final int getReaction() {
		return myReaction;
	}

	@Override
	public final int getStrength() {
		return myStrength;
	}

	@Override
	public final int getLogic() {
		return myLogic;
	}

	@Override
	public final int getIntuition() {
		return myIntuition;
	}

	@Override
	public final int getCharisma() {
		return myCharisma;
	}

	@Override
	public final int getInitDice() {
		return myInitDice;
	}

	/**
	 * Go through inventory list and return total value of all armor.
	 */
	@Override
	public final int getArmor() {
		int oResult = 0;

		for (final IShadowrunItem item : getInventory()) {
			if (item instanceof Armor) {
				oResult += item.getArmor();
			}
		}

		return oResult;
	}

	@Override
	public int rollInitiative(final int iPenalties) {
		// prompt for initiative type
		final JRadioButton meatButton = new JRadioButton("Meat ");
		meatButton.setMnemonic(KeyEvent.VK_M);
		final JRadioButton astralButton = new JRadioButton("Astral ");
		astralButton.setMnemonic(KeyEvent.VK_A);
		final ButtonGroup initiativeTypeButtonGroup = new ButtonGroup();
		initiativeTypeButtonGroup.add(meatButton);
		if (getSpecialAtt() > 0) {
			initiativeTypeButtonGroup.add(astralButton);
		}

		meatButton.setSelected(true);

		JOptionPane.showConfirmDialog(null, initiativeTypeButtonGroup,
				"Initiative type for: " + getName(),
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (astralButton.isSelected()) {
			return ShadowrunCommonUtils.rollAstralInitiative(getIntuition(),
					getInitDice()) - iPenalties;
		} else {
			// assume meat
			return ShadowrunCommonUtils.rollInitiative(getIntuition(),
					getReaction(), getInitDice()) - iPenalties;
		}
	}
}
