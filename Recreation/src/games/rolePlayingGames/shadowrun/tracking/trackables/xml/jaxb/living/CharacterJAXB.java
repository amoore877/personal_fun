package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.living;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Basic character JAXB. Not a hacker or technomancer.
 * 
 * @author Andrew
 *
 */
@XmlType(name = "character")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "character")
@XmlSeeAlso({ HackerJAXB.class, TechnomancerJAXB.class })
public class CharacterJAXB extends LivingBeingJAXB {

	/**
	 * Essence.
	 */
	@XmlElement(required = true, nillable = false)
	private int essence;

	public int getEssence() {
		return essence;
	}

	public void setEssence(final int essence) {
		this.essence = essence;
	}

	@Override
	public String toString() {
		return "Character-" + getName();
	}
}
