package games.rolePlayingGames.shadowrun.tracking.trackables.xml.adapter;

import games.rolePlayingGames.shadowrun.tracking.notes.ability.AbstractAbility;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.QualityNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.Skill;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.Spell;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.StatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.character.NonPlayerCharacter;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.character.NonPlayerHacker;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.character.NonPlayerTechnomancer;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.Armor;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.ChargedMeleeWeapon;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.Device;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.FirearmWeapon;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.MeleeWeapon;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.ProjectileWeapon;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.ShadowrunBasicItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.TimedItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.matrix.NonPlayerAgent;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.matrix.NonPlayerAutoPilotDevice;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.spirit.NonPlayerSpirit;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.AbstractShadowrunItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon.AbstractAmmoFedWeapon;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon.AbstractMeleeWeapon;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon.AbstractShadowrunWeapon;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.ArmorJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.BasicItemJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.ChargedMeleeWeaponJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.DeviceJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.EquipmentJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.FirearmWeaponJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.MeleeWeaponJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.ProjectileWeaponJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.RangedWeaponJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.TimedItemJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.WeaponJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.living.CharacterJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.living.HackerJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.living.SpiritJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.living.TechnomancerJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.matrix.AgentJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.matrix.AutoPilotDeviceJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.AbilityJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.QualityJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.SkillJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.SpellJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.StatusEffectJAXB;

import java.util.ArrayList;

/**
 * Utility class for JAXB class to regular Java class.
 * 
 * @author Andrew
 */
public final class JAXBtoJava {

	/**
	 * Constructor.
	 */
	private JAXBtoJava() {
	}

	/**
	 * Get some Shadowrun item from the Equipment JAXB.
	 * 
	 * @param iEquipment
	 *            EquipmentJAXB.
	 * @return some item/equipment, or null.
	 */
	public static AbstractShadowrunItem getItem(final EquipmentJAXB iEquipment) {
		if (iEquipment instanceof ArmorJAXB) {
			return getArmor((ArmorJAXB) iEquipment);
		} else if (iEquipment instanceof BasicItemJAXB) {
			return getBasicItem((BasicItemJAXB) iEquipment);
		} else if (iEquipment instanceof WeaponJAXB) {
			return getWeapon((WeaponJAXB) iEquipment);
		} else {
			// TODO add any additional extensions of Equipment here
			return null;
		}
	}

	/**
	 * Get some Shadowrun items from the Equipment JAXBs. Ignore nulls on
	 * conversion.
	 * 
	 * @param iEquipments
	 *            Equipment JAXBs.
	 * @return some items/equipments.
	 */
	public static ArrayList<AbstractShadowrunItem> getItems(
			final ArrayList<EquipmentJAXB> iEquipments) {
		final ArrayList<AbstractShadowrunItem> oResult = new ArrayList<AbstractShadowrunItem>();

		for (final EquipmentJAXB equipment : iEquipments) {
			final AbstractShadowrunItem convertedItem = getItem(equipment);
			if (convertedItem != null) {
				oResult.add(convertedItem);
			}
		}

		return oResult;
	}

	/**
	 * Get some Shadowrun weapon from the Weapon JAXB.
	 * 
	 * @param iWeapon
	 *            WeaponJAXB.
	 * @return some weapon, or null.
	 */
	public static AbstractShadowrunWeapon getWeapon(final WeaponJAXB iWeapon) {
		if (iWeapon instanceof RangedWeaponJAXB) {
			return getRangedWeapon((RangedWeaponJAXB) iWeapon);
		} else if (iWeapon instanceof MeleeWeaponJAXB) {
			return getMeleeWeapon((MeleeWeaponJAXB) iWeapon);
		} else {
			// TODO add any additional extensions of weapon here
			return null;
		}
	}

	/**
	 * Get some Shadowrun melee weapon from the Melee Weapon JAXB.
	 * 
	 * @param iWeapon
	 *            melee weapon JAXB.
	 * @return some melee weapon.
	 */
	public static AbstractMeleeWeapon getMeleeWeapon(
			final MeleeWeaponJAXB iWeapon) {
		if (iWeapon instanceof ChargedMeleeWeaponJAXB) {
			return getChargedMeleeWeapon((ChargedMeleeWeaponJAXB) iWeapon);
		} else {
			// TODO add any additional extensions of melee weapon here
			return new MeleeWeapon(iWeapon.getName(), iWeapon.getBenefits(),
					iWeapon.getAccuracy(), iWeapon.getDamageValue(),
					iWeapon.getArmorPiercing(), iWeapon.getDamageElement(),
					iWeapon.getReach());
		}
	}

	/**
	 * Get some Shadowrun charged melee weapon from the Charged Melee Weapon
	 * JAXB.
	 * 
	 * @param iWeapon
	 *            charged melee weapon JAXB.
	 * @return some charged melee weapon.
	 */
	public static ChargedMeleeWeapon getChargedMeleeWeapon(
			final ChargedMeleeWeaponJAXB iWeapon) {
		// TODO spare clips prompt?
		return new ChargedMeleeWeapon(iWeapon.getName(), iWeapon.getBenefits(),
				iWeapon.getAccuracy(), iWeapon.getDamageValue(),
				iWeapon.getArmorPiercing(), iWeapon.getDamageElement(),
				iWeapon.getReach(), iWeapon.getClipCapacity(), 2);
	}

	/**
	 * Get some Shadowrun ammo-fed weapon from the Ranged Weapon JAXB.
	 * 
	 * @param iWeapon
	 *            ranged weapon JAXB.
	 * @return some ammo-fed weapon, or null.
	 */
	public static AbstractAmmoFedWeapon getRangedWeapon(
			final RangedWeaponJAXB iWeapon) {
		if (iWeapon instanceof ProjectileWeaponJAXB) {
			return getProjectileWeapon((ProjectileWeaponJAXB) iWeapon);
		} else if (iWeapon instanceof FirearmWeaponJAXB) {
			return getFirearmWeapon((FirearmWeaponJAXB) iWeapon);
		} else {
			// TODO add any additional extensions of ranged weapon here
			return null;
		}
	}

	/**
	 * Get some Shadowrun firearm from the Firearm Weapon JAXB.
	 * 
	 * @param iWeapon
	 *            firearm weapon JAXB.
	 * @return some firearm weapon.
	 */
	public static FirearmWeapon getFirearmWeapon(final FirearmWeaponJAXB iWeapon) {
		// TODO spare clips prompt?
		return new FirearmWeapon(iWeapon.getName(), iWeapon.getBenefits(),
				iWeapon.getAccuracy(), iWeapon.getDamageValue(),
				iWeapon.getArmorPiercing(), iWeapon.getDamageElement(),
				iWeapon.getClipCapacity(), 2, iWeapon.getRangedWeaponTypes(),
				iWeapon.getFiringModes(), iWeapon.getRecoilCompensation());
	}

	/**
	 * Get some Shadowrun projectile weapon from the Projectile Weapon JAXB.
	 * 
	 * @param iWeapon
	 *            projectile weapon JAXB.
	 * @return some projectile weapon.
	 */
	public static ProjectileWeapon getProjectileWeapon(
			final ProjectileWeaponJAXB iWeapon) {
		// TODO spare clips prompt?
		return new ProjectileWeapon(iWeapon.getName(), iWeapon.getBenefits(),
				iWeapon.getAccuracy(), iWeapon.getDamageValue(),
				iWeapon.getArmorPiercing(), iWeapon.getDamageElement(), 2,
				iWeapon.getRangedWeaponTypes());
	}

	/**
	 * Get an armor class.
	 * 
	 * @param iArmor
	 *            JAXB armor.
	 * @return Java armor.
	 */
	public static Armor getArmor(final ArmorJAXB iArmor) {
		return new Armor(iArmor.getName(), iArmor.getArmor(),
				iArmor.getBenefits());
	}

	/**
	 * Get some Shadowrun item from the Basic Item JAXB.
	 * 
	 * @param iItem
	 *            BasicItemJAXB.
	 * @return device, timed item, or basic item.
	 */
	public static AbstractShadowrunItem getBasicItem(final BasicItemJAXB iItem) {
		if (iItem instanceof DeviceJAXB) {
			return getDevice((DeviceJAXB) iItem);
		} else if (iItem instanceof TimedItemJAXB) {
			return getTimedItem((TimedItemJAXB) iItem);
		} else {
			// TODO add any additional extensions of BasicItem here
			return new ShadowrunBasicItem(iItem.getName(), iItem.getBody(),
					iItem.getArmor());
		}
	}

	/**
	 * Get some timed item from the JAXB version.
	 * 
	 * @param iTimedItem
	 *            JAXB version of timed item.
	 * @return timed item.
	 */
	public static AbstractShadowrunItem getTimedItem(
			final TimedItemJAXB iTimedItem) {
		return new TimedItem(iTimedItem.getName(), iTimedItem.getBody(),
				iTimedItem.getArmor(), 0, iTimedItem.getEffect());
	}

	/**
	 * Get some device from the JAXB version.
	 * 
	 * @param iDevice
	 *            JAXB version of device.
	 * @return device.
	 */
	public static Device getDevice(final DeviceJAXB iDevice) {
		return new Device(iDevice.getName(), iDevice.getBody(),
				iDevice.getArmor(), iDevice.getRating());
	}

	/**
	 * Get some ability from the JAXB version.
	 * 
	 * @param iAbility
	 *            JAXB version of ability.
	 * @return ability, or null.
	 */
	public static AbstractAbility getAbility(final AbilityJAXB iAbility) {
		if (iAbility instanceof SkillJAXB) {
			return getSkill((SkillJAXB) iAbility);
		} else if (iAbility instanceof SpellJAXB) {
			return getSpell((SpellJAXB) iAbility);
		} else {
			// TODO add additional extensions of Ability JAXB here
			return null;
		}
	}

	/**
	 * Convert JAXB abilities to Abstract Abilities. Ignore abilities that
	 * cannot be converted.
	 * 
	 * @param iAbilities
	 *            JAXB abilities.
	 * @return list of abilities.
	 */
	public static ArrayList<AbstractAbility> getAbilities(
			final ArrayList<AbilityJAXB> iAbilities) {
		final ArrayList<AbstractAbility> oResult = new ArrayList<AbstractAbility>();

		for (final AbilityJAXB ability : iAbilities) {
			final AbstractAbility convertedAbility = getAbility(ability);
			if (convertedAbility != null) {
				oResult.add(convertedAbility);
			}
		}

		return oResult;
	}

	/**
	 * Get some skill from the JAXB version.
	 * 
	 * @param iSkill
	 *            JAXB version of skill.
	 * @return skill.
	 */
	public static Skill getSkill(final SkillJAXB iSkill) {
		return new Skill(iSkill.getFullDesc(), iSkill.getLevel());
	}

	/**
	 * Get some spell from the JAXB version.
	 * 
	 * @param iSpell
	 *            JAXB version of spell.
	 * @return spell.
	 */
	public static Spell getSpell(final SpellJAXB iSpell) {
		return new Spell(iSpell.getFullDesc(), iSpell.getBriefDesc());
	}

	/**
	 * Get some quality from the JAXB version.
	 * 
	 * @param iQuality
	 *            JAXB version of quality.
	 * @return quality.
	 */
	public static QualityNote getQuality(final QualityJAXB iQuality) {
		return new QualityNote(iQuality.getFullDesc(), iQuality.getBriefDesc(),
				iQuality.isCombatQuality(), iQuality.getQualityType());
	}

	/**
	 * Get some qualities from the JAXB version. Ignore nulls on conversion.
	 * 
	 * @param iQualities
	 *            JAXB version of qualities.
	 * @return qualities.
	 */
	public static ArrayList<QualityNote> getQualities(
			final ArrayList<QualityJAXB> iQualities) {
		final ArrayList<QualityNote> oResult = new ArrayList<QualityNote>();

		for (final QualityJAXB quality : iQualities) {
			final QualityNote convertedQuality = getQuality(quality);
			if (convertedQuality != null) {
				oResult.add(convertedQuality);
			}
		}

		return oResult;
	}

	/**
	 * Get some status effect from the JAXB version.
	 * 
	 * @param iStatusEffect
	 *            JAXB version of status effect.
	 * @return status effect.
	 */
	public static StatusEffectNote getStatusEffect(
			final StatusEffectJAXB iStatusEffect) {
		return new StatusEffectNote(iStatusEffect.getFullDesc(),
				iStatusEffect.getBriefDesc(),
				iStatusEffect.isCombatStatusEffect(),
				iStatusEffect.getStatusEffectType());
	}

	/**
	 * Get some status effects from the JAXB version. Ignore nulls on
	 * conversion.
	 * 
	 * @param iStatusEffects
	 *            JAXB version of status effects.
	 * @return status effects.
	 */
	public static ArrayList<StatusEffectNote> getStatusEffects(
			final ArrayList<StatusEffectJAXB> iStatusEffects) {
		final ArrayList<StatusEffectNote> oResult = new ArrayList<StatusEffectNote>();

		for (final StatusEffectJAXB statusEffect : iStatusEffects) {
			final StatusEffectNote convertedStatusEffect = getStatusEffect(statusEffect);
			if (convertedStatusEffect != null) {
				oResult.add(convertedStatusEffect);
			}
		}

		return oResult;
	}

	/**
	 * Get some agent from the JAXB version.
	 * 
	 * @param iAgent
	 *            JAXB version of agent.
	 * @return agent.
	 */
	public static NonPlayerAgent getAgent(final AgentJAXB iAgent) {
		return new NonPlayerAgent(iAgent.getName(), iAgent.getRating(),
				iAgent.getAttack(), iAgent.getSleaze(),
				iAgent.getDataProcessing(), iAgent.getFirewall(),
				getAbilities(iAgent.getAbilities()));
	}

	/**
	 * Get some autopilot device from JAXB version.
	 * 
	 * @param iAutoPilotDevice
	 *            JAXB version of autopilot device.
	 * @return autopilot device.
	 */
	public static NonPlayerAutoPilotDevice getAutoPilotDevice(
			final AutoPilotDeviceJAXB iAutoPilotDevice) {
		return new NonPlayerAutoPilotDevice(iAutoPilotDevice.getName(),
				iAutoPilotDevice.getBody(), iAutoPilotDevice.getArmor(),
				iAutoPilotDevice.getRating(),
				getItems(iAutoPilotDevice.getInventory()),
				getAbilities(iAutoPilotDevice.getAbilities()),
				iAutoPilotDevice.getDataProcessing(),
				iAutoPilotDevice.getFirewall(), iAutoPilotDevice.getPilot());
	}

	/**
	 * Get some spirit from JAXB version.
	 * 
	 * @param iSpirit
	 *            JAXB version of spirit.
	 * @return spirit.
	 */
	public static NonPlayerSpirit getSpirit(final SpiritJAXB iSpirit) {
		// TODO prompt for services?
		return new NonPlayerSpirit(iSpirit.getName(), iSpirit.getSpecial(),
				iSpirit.getBody(), iSpirit.getWillpower(),
				getStatusEffects(iSpirit.getStatusEffects()),
				getItems(iSpirit.getInventory()),
				getQualities(iSpirit.getQualities()), 4, iSpirit.getAgility(),
				iSpirit.getReaction(), iSpirit.getStrength(),
				iSpirit.getLogic(), iSpirit.getIntuition(),
				iSpirit.getCharisma(), iSpirit.getInitDice(),
				getAbilities(iSpirit.getAbilities()));
	}

	/**
	 * Get some character from JAXB version.
	 * 
	 * @param iCharacter
	 *            JAXB version of character.
	 * @return character.
	 */
	public static NonPlayerCharacter getCharacter(final CharacterJAXB iCharacter) {
		if (iCharacter instanceof HackerJAXB) {
			return getHacker((HackerJAXB) iCharacter);
		} else if (iCharacter instanceof TechnomancerJAXB) {
			return getTechnomancer((TechnomancerJAXB) iCharacter);
		} else {
			// TODO add additional extensions of Character JAXB here
			return new NonPlayerCharacter(iCharacter.getName(),
					iCharacter.getEssence(), iCharacter.getBody(),
					iCharacter.getWillpower(), iCharacter.getSpecial(),
					getStatusEffects(iCharacter.getStatusEffects()),
					getItems(iCharacter.getInventory()),
					getQualities(iCharacter.getQualities()),
					iCharacter.getAgility(), iCharacter.getReaction(),
					iCharacter.getStrength(), iCharacter.getLogic(),
					iCharacter.getIntuition(), iCharacter.getCharisma(),
					iCharacter.getInitDice(),
					getAbilities(iCharacter.getAbilities()));
		}
	}

	/**
	 * Get some technomancer from JAXB version.
	 * 
	 * @param iTechnomancer
	 *            JAXB version of technomancer.
	 * @return technomancer.
	 */
	public static NonPlayerTechnomancer getTechnomancer(
			final TechnomancerJAXB iTechnomancer) {
		return new NonPlayerTechnomancer(iTechnomancer.getName(),
				iTechnomancer.getEssence(), iTechnomancer.getBody(),
				iTechnomancer.getWillpower(), iTechnomancer.getSpecial(),
				getStatusEffects(iTechnomancer.getStatusEffects()),
				getItems(iTechnomancer.getInventory()),
				getQualities(iTechnomancer.getQualities()),
				iTechnomancer.getAgility(), iTechnomancer.getReaction(),
				iTechnomancer.getStrength(), iTechnomancer.getLogic(),
				iTechnomancer.getIntuition(), iTechnomancer.getCharisma(),
				iTechnomancer.getInitDice(),
				getAbilities(iTechnomancer.getAbilities()));
	}

	/**
	 * Get some hacker from JAXB version.
	 * 
	 * @param iHacker
	 *            JAXB version of hacker.
	 * @return hacker.
	 */
	public static NonPlayerHacker getHacker(final HackerJAXB iHacker) {
		return new NonPlayerHacker(iHacker.getName(), iHacker.getEssence(),
				iHacker.getBody(), iHacker.getWillpower(),
				iHacker.getSpecial(),
				getStatusEffects(iHacker.getStatusEffects()),
				getItems(iHacker.getInventory()),
				getQualities(iHacker.getQualities()), iHacker.getAgility(),
				iHacker.getReaction(), iHacker.getStrength(),
				iHacker.getLogic(), iHacker.getIntuition(),
				iHacker.getCharisma(), iHacker.getInitDice(),
				getAbilities(iHacker.getAbilities()), iHacker.getRating(),
				iHacker.getAttack(), iHacker.getSleaze(),
				iHacker.getDataProcessing(), iHacker.getFirewall());
	}
}
