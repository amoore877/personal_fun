package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB for a timed item.
 * 
 * @author Andrew
 */
@XmlRootElement(name = "timedItem")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "timedItem")
public class TimedItemJAXB extends BasicItemJAXB {

	/**
	 * Effect of the item.
	 */
	@XmlElement(required = true, nillable = false)
	private String effect;

	public String getEffect() {
		return effect;
	}

	public void setEffect(final String effect) {
		this.effect = effect;
	}
}
