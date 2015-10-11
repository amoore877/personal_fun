package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Equipment JAXB.
 * 
 * @author Andrew
 */
@XmlType(name = "equipment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ ArmorJAXB.class, BasicItemJAXB.class, WeaponJAXB.class })
public abstract class EquipmentJAXB {

	@XmlElement(required = true, nillable = false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
}
