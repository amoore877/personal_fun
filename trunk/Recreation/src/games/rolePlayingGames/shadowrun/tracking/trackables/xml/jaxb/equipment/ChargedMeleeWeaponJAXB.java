package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB for melee weapon that has some kind of charge/ammo.
 * 
 * @author Andrew
 */
@XmlRootElement(name = "chargedMeleeWeapon")
@XmlType(name = "chargedMeleeWeapon")
public class ChargedMeleeWeaponJAXB extends MeleeWeaponJAXB {

	/**
	 * Capacity of clip.
	 */
	private int clipCapacity;

	public int getClipCapacity() {
		return clipCapacity;
	}

	@XmlElement(required = true, nillable = false)
	public void setClipCapacity(final int clipCapacity) {
		this.clipCapacity = clipCapacity;
	}
}
