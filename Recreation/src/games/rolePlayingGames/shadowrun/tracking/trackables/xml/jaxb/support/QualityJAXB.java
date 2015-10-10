package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support;

import games.rolePlayingGames.shadowrun.tracking.notes.quality.QualityType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Quality JAXB.
 * 
 * @author Andrew
 */
@XmlType(name = "quality")
@XmlRootElement(name = "quality")
public class QualityJAXB {
	/**
	 * The note's string.
	 */
	private String fullDesc;

	/**
	 * Brief description.
	 */
	private String briefDesc;

	/**
	 * Combat quality flag.
	 */
	private boolean combatQuality;

	/**
	 * Quality type.
	 */
	private QualityType qualityType;

	public String getFullDesc() {
		return fullDesc;
	}

	@XmlElement(required = true, nillable = false)
	public void setFullDesc(final String fullDesc) {
		this.fullDesc = fullDesc;
	}

	public String getBriefDesc() {
		return briefDesc;
	}

	@XmlElement(required = true, nillable = false)
	public void setBriefDesc(final String briefDesc) {
		this.briefDesc = briefDesc;
	}

	public boolean isCombatQuality() {
		return combatQuality;
	}

	@XmlElement(required = true, nillable = false)
	public void setCombatQuality(final boolean combatQuality) {
		this.combatQuality = combatQuality;
	}

	public QualityType getQualityType() {
		return qualityType;
	}

	@XmlElement(required = true, nillable = false)
	public void setQualityType(final QualityType qualityType) {
		this.qualityType = qualityType;
	}
}
