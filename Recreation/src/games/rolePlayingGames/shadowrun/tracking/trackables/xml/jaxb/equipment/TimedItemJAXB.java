package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB for a timed item.
 * 
 * @author Andrew
 */
@XmlRootElement(name = "timedItem")
@XmlType(name = "timedItem")
public class TimedItemJAXB extends BasicItemJAXB {

	/**
	 * Effect of the item.
	 */
	private String effect;

	public String getEffect() {
		return effect;
	}

	@XmlElement(required = true, nillable = false)
	public void setEffect(final String effect) {
		this.effect = effect;
	}
}
