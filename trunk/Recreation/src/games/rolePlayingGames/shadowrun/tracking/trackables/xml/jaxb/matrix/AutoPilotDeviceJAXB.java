package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.matrix;

import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.EquipmentJAXB;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Auto-piloted device JAXB.
 * 
 * @author Andrew
 */
@XmlType(name = "pilotDevice")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "pilotDevice")
public class AutoPilotDeviceJAXB extends MatrixBeingJAXB {

	/**
	 * Pilot.
	 */
	@XmlElement(required = true, nillable = false)
	private int pilot;

	/**
	 * Body.
	 */
	@XmlElement(required = true, nillable = false)
	private int body;

	/**
	 * Armor.
	 */
	@XmlElement(required = true, nillable = false)
	private int armor;

	/**
	 * Inventory.
	 */
	@XmlElementWrapper
	@XmlElement(required = true, nillable = false)
	private ArrayList<EquipmentJAXB> inventory;

	public int getPilot() {
		return pilot;
	}

	public void setPilot(final int pilot) {
		this.pilot = pilot;
	}

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

	public ArrayList<EquipmentJAXB> getInventory() {
		return inventory;
	}

	public void setInventory(final ArrayList<EquipmentJAXB> inventory) {
		this.inventory = inventory;
	}
}
