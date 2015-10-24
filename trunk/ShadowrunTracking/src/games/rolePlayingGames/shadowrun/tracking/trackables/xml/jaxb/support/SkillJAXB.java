package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Skill JAXB.
 * 
 * @author Andrew
 */
@XmlRootElement(name = "skill")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "skill")
public class SkillJAXB extends AbilityJAXB {

	/**
	 * Level of the skill.
	 */
	@XmlElement(required = true, nillable = false)
	private int level;

	public int getLevel() {
		return level;
	}

	public void setLevel(final int level) {
		this.level = level;
	}
}
