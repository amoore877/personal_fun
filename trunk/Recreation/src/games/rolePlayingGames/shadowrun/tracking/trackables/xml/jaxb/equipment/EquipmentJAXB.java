package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Equipment JAXB.
 * 
 * @author Andrew
 */
@XmlType(name = "equipment")
@XmlSeeAlso({ ArmorJAXB.class, BasicItemJAXB.class, WeaponJAXB.class })
public abstract class EquipmentJAXB {

	private String name;

	public String getName() {
		return name;
	}

	@XmlElement(required = true, nillable = false)
	public void setName(final String name) {
		this.name = name;
	}
}
