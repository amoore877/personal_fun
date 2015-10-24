package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB for devices.
 * 
 * @author Andrew
 */
@XmlRootElement(name = "device")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "device")
public class DeviceJAXB extends BasicItemJAXB {

	@XmlElement(required = true, nillable = false)
	private int rating;

	public int getRating() {
		return rating;
	}

	public void setRating(final int rating) {
		this.rating = rating;
	}
}
