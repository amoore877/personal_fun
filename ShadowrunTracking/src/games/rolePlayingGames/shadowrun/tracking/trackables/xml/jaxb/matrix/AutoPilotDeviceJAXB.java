package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.matrix;

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
	@XmlElementWrapper(required = true, nillable = false, name = "inventories")
	@XmlElement(required = true, nillable = false, name = "inventoryID")
	private ArrayList<String> inventory;

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

	public ArrayList<String> getInventory() {
		return inventory;
	}

	public void setInventory(final ArrayList<String> inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		return "AutoPilot Device-" + getName();
	}
}
