package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import games.rolePlayingGames.shadowrun.util.FiringMode;

import java.util.Set;

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
@XmlType(name = "firearm")
public class FirearmWeaponJAXB extends RangedWeaponJAXB {

	/**
	 * Firing modes for this weapon.
	 */
	private Set<FiringMode> firingModes;

	/**
	 * Recoil compensation.
	 */
	private int recoilCompensation;

	public Set<FiringMode> getFiringModes() {
		return firingModes;
	}

	@XmlElementWrapper
	@XmlElement(required = true, nillable = false)
	public void setFiringModes(final Set<FiringMode> firingModes) {
		this.firingModes = firingModes;
	}

	public int getRecoilCompensation() {
		return recoilCompensation;
	}

	@XmlElement(required = true, nillable = false)
	public void setRecoilCompensation(final int recoilCompensation) {
		this.recoilCompensation = recoilCompensation;
	}
}
