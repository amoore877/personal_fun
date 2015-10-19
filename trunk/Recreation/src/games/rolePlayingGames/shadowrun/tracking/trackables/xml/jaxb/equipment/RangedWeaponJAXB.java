package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import games.rolePlayingGames.shadowrun.util.RangedWeaponType;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB for ranged weapon.
 * 
 * @author Andrew
 */
@XmlType(name = "rangedWeapon")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ ProjectileWeaponJAXB.class, FirearmWeaponJAXB.class })
public abstract class RangedWeaponJAXB extends WeaponJAXB {

	/**
	 * Type of this ranged weapon.
	 */
	@XmlElementWrapper(required = true, nillable = false, name = "rangedWeaponTypes")
	@XmlElement(required = true, nillable = false, name = "rangedWeaponType")
	private Set<RangedWeaponType> rangedWeaponTypes;

	public Set<RangedWeaponType> getRangedWeaponTypes() {
		return rangedWeaponTypes;
	}

	public void setRangedWeaponTypes(
			final Set<RangedWeaponType> rangedWeaponTypes) {
		this.rangedWeaponTypes = rangedWeaponTypes;
	}
}
