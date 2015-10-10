package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.matrix;

import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.EquipmentJAXB;

import java.util.ArrayList;

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
@XmlRootElement(name = "pilotDevice")
public class AutoPilotDeviceJAXB extends MatrixBeingJAXB {

	/**
	 * Pilot.
	 */
	private int pilot;

	/**
	 * Body.
	 */
	private int body;

	/**
	 * Armor.
	 */
	private int armor;

	/**
	 * Inventory.
	 */
	private ArrayList<EquipmentJAXB> inventory;

	public int getPilot() {
		return pilot;
	}

	@XmlElement(required = true, nillable = false)
	public void setPilot(final int pilot) {
		this.pilot = pilot;
	}

	public int getBody() {
		return body;
	}

	@XmlElement(required = true, nillable = false)
	public void setBody(final int body) {
		this.body = body;
	}

	public int getArmor() {
		return armor;
	}

	@XmlElement(required = true, nillable = false)
	public void setArmor(final int armor) {
		this.armor = armor;
	}

	public ArrayList<EquipmentJAXB> getInventory() {
		return inventory;
	}

	@XmlElementWrapper
	@XmlElement(required = true, nillable = false)
	public void setInventory(final ArrayList<EquipmentJAXB> inventory) {
		this.inventory = inventory;
	}
}
