package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Spell JAXB.
 * 
 * @author Andrew
 */
@XmlType(name = "spell")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "spell")
public class SpellJAXB extends AbilityJAXB {

	/**
	 * Brief description of the ability.
	 */
	@XmlElement(required = true, nillable = false)
	private String briefDesc;

	public String getBriefDesc() {
		return briefDesc;
	}

	public void setBriefDesc(final String briefDesc) {
		this.briefDesc = briefDesc;
	}
}
