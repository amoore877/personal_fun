package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import games.rolePlayingGames.shadowrun.util.FiringMode;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB for a firearm.
 * 
 * @author Andrew
 */
@XmlRootElement(name = "firearm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "firearm")
public class FirearmWeaponJAXB extends RangedWeaponJAXB {

	/**
	 * Firing modes for this weapon.
	 */
	@XmlElementWrapper(required = true, nillable = false, name = "firingModes")
	@XmlElement(required = true, nillable = false, name = "firingMode")
	private Set<FiringMode> firingModes;

	/**
	 * Clip capacity.
	 */
	@XmlElement(required = true, nillable = false)
	private int clipCapacity;

	/**
	 * Recoil compensation.
	 */
	@XmlElement(required = true, nillable = false)
	private int recoilCompensation;

	public Set<FiringMode> getFiringModes() {
		return firingModes;
	}

	public void setFiringModes(final Set<FiringMode> firingModes) {
		this.firingModes = firingModes;
	}

	public int getRecoilCompensation() {
		return recoilCompensation;
	}

	public void setRecoilCompensation(final int recoilCompensation) {
		this.recoilCompensation = recoilCompensation;
	}

	public int getClipCapacity() {
		return clipCapacity;
	}

	public void setClipCapacity(final int clipCapacity) {
		this.clipCapacity = clipCapacity;
	}
}
