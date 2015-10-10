package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import games.rolePlayingGames.shadowrun.util.DamageElement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB for weapons.
 * 
 * @author Andrew
 */
@XmlType(name = "weapon")
@XmlSeeAlso({ MeleeWeaponJAXB.class, RangedWeaponJAXB.class })
public abstract class WeaponJAXB extends EquipmentJAXB {

	/**
	 * Accuracy.
	 */
	private int accuracy;

	/**
	 * Damage value.
	 */
	private int damageValue;

	/**
	 * Armor piercing.
	 */
	private int armorPiercing;

	/**
	 * Damage element.
	 */
	private DamageElement damageElement;

	/**
	 * Benefits.
	 */
	private String benefits;

	public int getAccuracy() {
		return accuracy;
	}

	@XmlElement(required = true, nillable = false)
	public void setAccuracy(final int accuracy) {
		this.accuracy = accuracy;
	}

	public int getDamageValue() {
		return damageValue;
	}

	@XmlElement(required = true, nillable = false)
	public void setDamageValue(final int damageValue) {
		this.damageValue = damageValue;
	}

	public int getArmorPiercing() {
		return armorPiercing;
	}

	@XmlElement(required = true, nillable = false)
	public void setArmorPiercing(final int armorPiercing) {
		this.armorPiercing = armorPiercing;
	}

	public DamageElement getDamageElement() {
		return damageElement;
	}

	@XmlElement(required = true, nillable = false)
	public void setDamageElement(final DamageElement damageElement) {
		this.damageElement = damageElement;
	}

	public String getBenefits() {
		return benefits;
	}

	@XmlElement(required = true, nillable = false)
	public void setBenefits(final String benefits) {
		this.benefits = benefits;
	}
}
