package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB for armor.
 * 
 * @author Andrew
 */
@XmlType(name = "armorEquipment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "armorEquipment")
public class ArmorJAXB extends EquipmentJAXB {

	@XmlElement(required = true, nillable = false)
	private int armor;

	@XmlElement(required = true, nillable = false)
	private String benefits;

	public int getArmor() {
		return armor;
	}

	public void setArmor(final int armor) {
		this.armor = armor;
	}

	public String getBenefits() {
		return benefits;
	}

	public void setBenefits(final String benefits) {
		this.benefits = benefits;
	}
}
