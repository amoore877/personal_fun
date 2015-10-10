package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.living;

import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.EquipmentJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.AbilityJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.QualityJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.StatusEffectJAXB;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Living being JAXB.
 * 
 * @author Andrew
 */
@XmlType(name = "livingBeing")
@XmlSeeAlso({ SpiritJAXB.class, CharacterJAXB.class })
public abstract class LivingBeingJAXB {

	/**
	 * Name.
	 */
	private String name;

	/**
	 * Essence.
	 */
	private int essence;

	/**
	 * Special attribute (magic/resonance).
	 */
	private int special;

	/**
	 * Status effects.
	 */
	private ArrayList<StatusEffectJAXB> statusEffects;

	/**
	 * Inventory.
	 */
	private ArrayList<EquipmentJAXB> inventory;

	/**
	 * Qualities.
	 */
	private ArrayList<QualityJAXB> qualities;

	/**
	 * Body.
	 */
	private int body;

	/**
	 * Willpower.
	 */
	private int willpower;

	/**
	 * Agility.
	 */
	private int agility;

	/**
	 * Reaction.
	 */
	private int reaction;

	/**
	 * Strength.
	 */
	private int strength;

	/**
	 * Logic.
	 */
	private int logic;

	/**
	 * Intuition.
	 */
	private int intuition;

	/**
	 * Charisma.
	 */
	private int charisma;

	/**
	 * Initiative dice.
	 */
	private int initDice;

	/**
	 * Abilities.
	 */
	private ArrayList<AbilityJAXB> abilities;

	public String getName() {
		return name;
	}

	@XmlElement(required = true, nillable = false)
	public void setName(final String name) {
		this.name = name;
	}

	public int getEssence() {
		return essence;
	}

	@XmlElement(required = true, nillable = false)
	public void setEssence(final int essence) {
		this.essence = essence;
	}

	public int getSpecial() {
		return special;
	}

	@XmlElement(required = true, nillable = false)
	public void setSpecial(final int special) {
		this.special = special;
	}

	public ArrayList<StatusEffectJAXB> getStatusEffects() {
		return statusEffects;
	}

	@XmlElementWrapper
	@XmlElement(required = true, nillable = false)
	public void setStatusEffects(final ArrayList<StatusEffectJAXB> statusEffects) {
		this.statusEffects = statusEffects;
	}

	public ArrayList<EquipmentJAXB> getInventory() {
		return inventory;
	}

	@XmlElementWrapper
	@XmlElement(required = true, nillable = false)
	public void setInventory(final ArrayList<EquipmentJAXB> inventory) {
		this.inventory = inventory;
	}

	public ArrayList<QualityJAXB> getQualities() {
		return qualities;
	}

	@XmlElementWrapper
	@XmlElement(required = true, nillable = false)
	public void setQualities(final ArrayList<QualityJAXB> qualities) {
		this.qualities = qualities;
	}

	public int getBody() {
		return body;
	}

	@XmlElement(required = true, nillable = false)
	public void setBody(final int body) {
		this.body = body;
	}

	public int getWillpower() {
		return willpower;
	}

	@XmlElement(required = true, nillable = false)
	public void setWillpower(final int willpower) {
		this.willpower = willpower;
	}

	public int getAgility() {
		return agility;
	}

	@XmlElement(required = true, nillable = false)
	public void setAgility(final int agility) {
		this.agility = agility;
	}

	public int getReaction() {
		return reaction;
	}

	@XmlElement(required = true, nillable = false)
	public void setReaction(final int reaction) {
		this.reaction = reaction;
	}

	public int getStrength() {
		return strength;
	}

	@XmlElement(required = true, nillable = false)
	public void setStrength(final int strength) {
		this.strength = strength;
	}

	public int getLogic() {
		return logic;
	}

	@XmlElement(required = true, nillable = false)
	public void setLogic(final int logic) {
		this.logic = logic;
	}

	public int getIntuition() {
		return intuition;
	}

	@XmlElement(required = true, nillable = false)
	public void setIntuition(final int intuition) {
		this.intuition = intuition;
	}

	public int getCharisma() {
		return charisma;
	}

	@XmlElement(required = true, nillable = false)
	public void setCharisma(final int charisma) {
		this.charisma = charisma;
	}

	public int getInitDice() {
		return initDice;
	}

	@XmlElement(required = true, nillable = false)
	public void setInitDice(final int initDice) {
		this.initDice = initDice;
	}

	public ArrayList<AbilityJAXB> getAbilities() {
		return abilities;
	}

	@XmlElementWrapper
	@XmlElement(required = true, nillable = false)
	public void setAbilities(final ArrayList<AbilityJAXB> abilities) {
		this.abilities = abilities;
	}
}
