package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB for a melee weapon.
 * 
 * @author Andrew
 */
@XmlRootElement(name = "meleeWeapon")
@XmlType(name = "meleeWeapon")
@XmlSeeAlso({ ChargedMeleeWeaponJAXB.class })
public class MeleeWeaponJAXB extends WeaponJAXB {

	/**
	 * Reach.
	 */
	private int reach;

	public int getReach() {
		return reach;
	}

	@XmlElement(required = true, nillable = false)
	public void setReach(final int reach) {
		this.reach = reach;
	}
}
