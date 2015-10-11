package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.living;

import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.EquipmentJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.AbilityJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.QualityJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.StatusEffectJAXB;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ SpiritJAXB.class, CharacterJAXB.class })
public abstract class LivingBeingJAXB {

	/**
	 * Name.
	 */
	@XmlElement(required = true, nillable = false)
	private String name;

	/**
	 * Special attribute (magic/resonance/force).
	 */
	@XmlElement(required = true, nillable = false)
	private int special;

	/**
	 * Status effects.
	 */
	@XmlElementWrapper
	@XmlElement(required = true, nillable = false)
	private ArrayList<StatusEffectJAXB> statusEffects;

	/**
	 * Inventory.
	 */
	@XmlElementWrapper
	@XmlElement(required = true, nillable = false)
	private ArrayList<EquipmentJAXB> inventory;

	/**
	 * Qualities.
	 */
	@XmlElementWrapper
	@XmlElement(required = true, nillable = false)
	private ArrayList<QualityJAXB> qualities;

	/**
	 * Body.
	 */
	@XmlElement(required = true, nillable = false)
	private int body;

	/**
	 * Willpower.
	 */
	@XmlElement(required = true, nillable = false)
	private int willpower;

	/**
	 * Agility.
	 */
	@XmlElement(required = true, nillable = false)
	private int agility;

	/**
	 * Reaction.
	 */
	@XmlElement(required = true, nillable = false)
	private int reaction;

	/**
	 * Strength.
	 */
	@XmlElement(required = true, nillable = false)
	private int strength;

	/**
	 * Logic.
	 */
	@XmlElement(required = true, nillable = false)
	private int logic;

	/**
	 * Intuition.
	 */
	@XmlElement(required = true, nillable = false)
	private int intuition;

	/**
	 * Charisma.
	 */
	@XmlElement(required = true, nillable = false)
	private int charisma;

	/**
	 * Initiative dice.
	 */
	@XmlElement(required = true, nillable = false)
	private int initDice;

	/**
	 * Abilities.
	 */
	@XmlElementWrapper
	@XmlElement(required = true, nillable = false)
	private ArrayList<AbilityJAXB> abilities;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getSpecial() {
		return special;
	}

	public void setSpecial(final int special) {
		this.special = special;
	}

	public ArrayList<StatusEffectJAXB> getStatusEffects() {
		return statusEffects;
	}

	public void setStatusEffects(final ArrayList<StatusEffectJAXB> statusEffects) {
		this.statusEffects = statusEffects;
	}

	public ArrayList<EquipmentJAXB> getInventory() {
		return inventory;
	}

	public void setInventory(final ArrayList<EquipmentJAXB> inventory) {
		this.inventory = inventory;
	}

	public ArrayList<QualityJAXB> getQualities() {
		return qualities;
	}

	public void setQualities(final ArrayList<QualityJAXB> qualities) {
		this.qualities = qualities;
	}

	public int getBody() {
		return body;
	}

	public void setBody(final int body) {
		this.body = body;
	}

	public int getWillpower() {
		return willpower;
	}

	public void setWillpower(final int willpower) {
		this.willpower = willpower;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(final int agility) {
		this.agility = agility;
	}

	public int getReaction() {
		return reaction;
	}

	public void setReaction(final int reaction) {
		this.reaction = reaction;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(final int strength) {
		this.strength = strength;
	}

	public int getLogic() {
		return logic;
	}

	public void setLogic(final int logic) {
		this.logic = logic;
	}

	public int getIntuition() {
		return intuition;
	}

	public void setIntuition(final int intuition) {
		this.intuition = intuition;
	}

	public int getCharisma() {
		return charisma;
	}

	public void setCharisma(final int charisma) {
		this.charisma = charisma;
	}

	public int getInitDice() {
		return initDice;
	}

	public void setInitDice(final int initDice) {
		this.initDice = initDice;
	}

	public ArrayList<AbilityJAXB> getAbilities() {
		return abilities;
	}

	public void setAbilities(final ArrayList<AbilityJAXB> abilities) {
		this.abilities = abilities;
	}
}
