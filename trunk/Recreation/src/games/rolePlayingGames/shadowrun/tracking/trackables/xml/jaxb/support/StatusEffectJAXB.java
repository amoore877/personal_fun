package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support;

import games.rolePlayingGames.shadowrun.tracking.notes.status.StatusEffectType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Status effect JAXB.
 * 
 * @author Andrew
 */
@XmlType(name = "statusEffect")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "statusEffect")
public class StatusEffectJAXB {

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
	 * Combat status effect flag.
	 */
	@XmlElement(required = true, nillable = false)
	private boolean combatStatusEffect;

	/**
	 * Status effect type.
	 */
	@XmlElement(required = true, nillable = false)
	private StatusEffectType statusEffectType;

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

	public boolean isCombatStatusEffect() {
		return combatStatusEffect;
	}

	public void setCombatStatusEffect(final boolean combatStatusEffect) {
		this.combatStatusEffect = combatStatusEffect;
	}

	public StatusEffectType getStatusEffectType() {
		return statusEffectType;
	}

	public void setStatusEffectType(final StatusEffectType statusEffectType) {
		this.statusEffectType = statusEffectType;
	}
}
