package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support;

import games.rolePlayingGames.shadowrun.tracking.notes.quality.QualityType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Quality JAXB.
 * 
 * @author Andrew
 */
@XmlType(name = "quality")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "quality")
public class QualityJAXB {
	/**
	 * The note's string.
	 */
	@XmlElement(required = true, nillable = false)
	private String fullDesc;

	/**
	 * Brief description.
	 */
	@XmlElement(required = true, nillable = false)
	private String briefDesc;

	/**
	 * Combat quality flag.
	 */
	@XmlElement(required = true, nillable = false)
	private boolean combatQuality;

	/**
	 * Quality type.
	 */
	@XmlElement(required = true, nillable = false)
	private QualityType qualityType;

	public String getFullDesc() {
		return fullDesc;
	}

	public void setFullDesc(final String fullDesc) {
		this.fullDesc = fullDesc;
	}

	public String getBriefDesc() {
		return briefDesc;
	}

	public void setBriefDesc(final String briefDesc) {
		this.briefDesc = briefDesc;
	}

	public boolean isCombatQuality() {
		return combatQuality;
	}

	public void setCombatQuality(final boolean combatQuality) {
		this.combatQuality = combatQuality;
	}

	public QualityType getQualityType() {
		return qualityType;
	}

	public void setQualityType(final QualityType qualityType) {
		this.qualityType = qualityType;
	}
}
