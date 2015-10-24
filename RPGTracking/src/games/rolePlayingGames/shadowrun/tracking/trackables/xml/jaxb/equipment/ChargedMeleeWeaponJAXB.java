package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB for melee weapon that has some kind of charge/ammo.
 * 
 * @author Andrew
 */
@XmlRootElement(name = "chargedMeleeWeapon")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chargedMeleeWeapon")
public class ChargedMeleeWeaponJAXB extends MeleeWeaponJAXB {

	/**
	 * Capacity of clip.
	 */
	@XmlElement(required = true, nillable = false)
	private int clipCapacity;

	public int getClipCapacity() {
		return clipCapacity;
	}

	public void setClipCapacity(final int clipCapacity) {
		this.clipCapacity = clipCapacity;
	}
}
