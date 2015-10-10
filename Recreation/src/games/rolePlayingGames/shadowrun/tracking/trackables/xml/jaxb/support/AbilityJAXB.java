package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Ability JAXB.
 * 
 * @author Andrew
 */
@XmlType(name = "ability")
@XmlSeeAlso({ SkillJAXB.class, SpellJAXB.class })
public abstract class AbilityJAXB {

	/**
	 * The note's string.
	 */
	private String fullDesc;

	public String getFullDesc() {
		return fullDesc;
	}

	@XmlElement(required = true, nillable = false)
	public void setFullDesc(final String fullDesc) {
		this.fullDesc = fullDesc;
	}
}
