package games.rolePlayingGames.shadowrun.tracking.trackables.impl.spirit;

import games.rolePlayingGames.shadowrun.tracking.notes.ability.AbstractAbility;
import games.rolePlayingGames.shadowrun.tracking.notes.damage.spirit.AbstractSpiritDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.QualityNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.StatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.notes.quality.IShadowrunQualityNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.Armor;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.AbstractShadowrunItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.IShadowrunItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.living.AbstractSpirit;
import games.rolePlayingGames.shadowrun.tracking.trackables.living.INonPlayerLivingBeing;
import games.rolePlayingGames.shadowrun.util.ShadowrunCommonUtils;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 * An NPC spirit.
 * 
 * @author Andrew
 */
public final class NonPlayerSpirit extends AbstractSpirit implements
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
	 * @param iForce
	 *            force.
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
	 * @param iServices
	 *            services.
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
	public NonPlayerSpirit(final String iName, final int iForce,
			final int iBody, final int iWillpower,
			final ArrayList<StatusEffectNote> iStatusEffects,
			final ArrayList<AbstractShadowrunItem> iInventory,
			final ArrayList<QualityNote> iQualities, final int iServices,
			final int iAgility, final int iReaction, final int iStrength,
			final int iLogic, final int iIntuition, final int iCharisma,
			final int iInitDice, final ArrayList<AbstractAbility> iAbilities) {
		super(iName, iForce, iBody, iWillpower, iForce, iStatusEffects,
				iInventory, iQualities, iServices);

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
	public int getPhysicalLimit() {
		return ShadowrunCommonUtils.getPhysicalLimit(getStrength(), getBody(),
				getReaction());
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder();

		oResult.append("Name=" + getName());

		for (final AbstractSpiritDamageNote damageNote : getDamageNotes()) {
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
	public ArrayList<AbstractAbility> getAbilities() {
		return (ArrayList<AbstractAbility>) myAbilities.clone();
	}

	@Override
	public int getMentalLimit() {
		return ShadowrunCommonUtils.getMentalLimit(getLogic(), getIntuition(),
				getWillpower());
	}

	@Override
	public int getSocialLimit() {
		return ShadowrunCommonUtils.getSocialLimit(getCharisma(),
				getWillpower(), getEssence());
	}

	@Override
	public int getAgility() {
		return myAgility;
	}

	@Override
	public int getReaction() {
		return myReaction;
	}

	@Override
	public int getStrength() {
		return myStrength;
	}

	@Override
	public int getLogic() {
		return myLogic;
	}

	@Override
	public int getIntuition() {
		return myIntuition;
	}

	@Override
	public int getCharisma() {
		return myCharisma;
	}

	@Override
	public int getInitDice() {
		return myInitDice;
	}

	/**
	 * Go through inventory list and return total value of all armor.
	 */
	@Override
	public int getArmor() {
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
		initiativeTypeButtonGroup.add(astralButton);

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
