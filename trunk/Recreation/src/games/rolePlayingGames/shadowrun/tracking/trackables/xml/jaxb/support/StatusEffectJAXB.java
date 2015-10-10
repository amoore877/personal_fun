package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support;

import games.rolePlayingGames.shadowrun.tracking.notes.status.StatusEffectType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Status effect JAXB.
 * 
 * @author Andrew
 */
@XmlType(name = "statusEffect")
@XmlRootElement(name = "statusEffect")
public class StatusEffectJAXB {

	/**
	 * The note's string.
	 */
	private String fullDesc;

	/**
	 * Brief description.
	 */
	private String briefDesc;

	/**
	 * Combat status effect flag.
	 */
	private boolean combatStatusEffect;

	/**
	 * Status effect type.
	 */
	private StatusEffectType statusEffectType;

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

	public boolean isCombatStatusEffect() {
		return combatStatusEffect;
	}

	@XmlElement(required = true, nillable = false)
	public void setCombatStatusEffect(final boolean combatStatusEffect) {
		this.combatStatusEffect = combatStatusEffect;
	}

	public StatusEffectType getStatusEffectType() {
		return statusEffectType;
	}

	@XmlElement(required = true, nillable = false)
	public void setStatusEffectType(final StatusEffectType statusEffectType) {
		this.statusEffectType = statusEffectType;
	}
}
