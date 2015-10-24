package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Ability JAXB.
 * 
 * @author Andrew
 */
@XmlType(name = "ability")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ SkillJAXB.class, SpellJAXB.class })
public abstract class AbilityJAXB {

	/**
	 * The note's string.
	 */
	@XmlElement(required = true, nillable = false)
	private String fullDesc;

	public String getFullDesc() {
		return fullDesc;
	}

	public void setFullDesc(final String fullDesc) {
		this.fullDesc = fullDesc;
	}
}
