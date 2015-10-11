package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.matrix;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Agent JAXB.
 * 
 * @author Andrew
 */
@XmlType(name = "agent")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "agent")
public class AgentJAXB extends MatrixBeingJAXB {

	/**
	 * Attack.
	 */
	@XmlElement(required = true, nillable = false)
	private int attack;

	/**
	 * Sleaze.
	 */
	@XmlElement(required = true, nillable = false)
	private int sleaze;

	public int getAttack() {
		return attack;
	}

	public void setAttack(final int attack) {
		this.attack = attack;
	}

	public int getSleaze() {
		return sleaze;
	}

	public void setSleaze(final int sleaze) {
		this.sleaze = sleaze;
	}
}
