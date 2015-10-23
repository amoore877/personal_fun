package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.living;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Spirit JAXB.
 * 
 * @author Andrew
 */
@XmlType(name = "spirit")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "spirit")
public class SpiritJAXB extends LivingBeingJAXB {

	@Override
	public String toString() {
		return "Spirit-" + getName();
	}
}
