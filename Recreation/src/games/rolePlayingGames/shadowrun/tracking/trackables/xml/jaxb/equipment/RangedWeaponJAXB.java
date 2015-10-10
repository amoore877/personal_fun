package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import games.rolePlayingGames.shadowrun.util.RangedWeaponType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB for ranged weapon.
 * 
 * @author Andrew
 */
@XmlType(name = "rangedWeapon")
@XmlSeeAlso({ ProjectileWeaponJAXB.class, FirearmWeaponJAXB.class })
public abstract class RangedWeaponJAXB extends WeaponJAXB {

	/**
	 * Type of this ranged weapon.
	 */
	private RangedWeaponType rangedWeaponType;

	public RangedWeaponType getRangedWeaponType() {
		return rangedWeaponType;
	}

	@XmlElement(required = true, nillable = false)
	public void setRangedWeaponType(final RangedWeaponType rangedWeaponType) {
		this.rangedWeaponType = rangedWeaponType;
	}
}
