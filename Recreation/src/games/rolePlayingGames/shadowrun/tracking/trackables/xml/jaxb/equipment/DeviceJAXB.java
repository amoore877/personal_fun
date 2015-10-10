package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB for devices.
 * 
 * @author Andrew
 */
@XmlRootElement(name = "device")
@XmlType(name = "device")
public class DeviceJAXB extends BasicItemJAXB {

	private int rating;

	public int getRating() {
		return rating;
	}

	@XmlElement(required = true, nillable = false)
	public void setRating(final int rating) {
		this.rating = rating;
	}
}
