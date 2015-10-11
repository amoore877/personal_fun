package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import games.rolePlayingGames.shadowrun.util.DamageElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB for weapons.
 * 
 * @author Andrew
 */
@XmlType(name = "weapon")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ MeleeWeaponJAXB.class, RangedWeaponJAXB.class })
public abstract class WeaponJAXB extends EquipmentJAXB {

	/**
	 * Accuracy.
	 */
	@XmlElement(required = true, nillable = false)
	private int accuracy;

	/**
	 * Damage value.
	 */
	@XmlElement(required = true, nillable = false)
	private int damageValue;

	/**
	 * Armor piercing.
	 */
	@XmlElement(required = true, nillable = false)
	private int armorPiercing;

	/**
	 * Damage element.
	 */
	@XmlElement(required = true, nillable = false)
	private DamageElement damageElement;

	/**
	 * Benefits.
	 */
	private String benefits;

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(final int accuracy) {
		this.accuracy = accuracy;
	}

	public int getDamageValue() {
		return damageValue;
	}

	public void setDamageValue(final int damageValue) {
		this.damageValue = damageValue;
	}

	public int getArmorPiercing() {
		return armorPiercing;
	}

	public void setArmorPiercing(final int armorPiercing) {
		this.armorPiercing = armorPiercing;
	}

	public DamageElement getDamageElement() {
		return damageElement;
	}

	public void setDamageElement(final DamageElement damageElement) {
		this.damageElement = damageElement;
	}

	public String getBenefits() {
		return benefits;
	}

	public void setBenefits(final String benefits) {
		this.benefits = benefits;
	}
}
