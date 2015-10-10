package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB for armor.
 * 
 * @author Andrew
 */
@XmlType(name = "armorEquipment")
@XmlRootElement(name = "armorEquipment")
public class ArmorJAXB extends EquipmentJAXB {

	private int armor;

	private String benefits;

	public int getArmor() {
		return armor;
	}

	@XmlElement(required = true, nillable = false)
	public void setArmor(final int armor) {
		this.armor = armor;
	}

	public String getBenefits() {
		return benefits;
	}

	@XmlElement(required = true, nillable = false)
	public void setBenefits(final String benefits) {
		this.benefits = benefits;
	}
}
