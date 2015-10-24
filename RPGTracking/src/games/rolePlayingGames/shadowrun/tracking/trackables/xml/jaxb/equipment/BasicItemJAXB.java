package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Basic item JAXB.
 * 
 * @author Andrew
 */
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "item")
@XmlSeeAlso({ TimedItemJAXB.class, DeviceJAXB.class })
public class BasicItemJAXB extends EquipmentJAXB {

	@XmlElement(required = true, nillable = false)
	private int body;

	@XmlElement(required = true, nillable = false)
	private int armor;

	public int getBody() {
		return body;
	}

	public void setBody(final int body) {
		this.body = body;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(final int armor) {
		this.armor = armor;
	}

}
