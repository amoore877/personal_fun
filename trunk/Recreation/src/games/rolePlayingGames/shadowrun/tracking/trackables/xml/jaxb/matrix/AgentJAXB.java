package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.matrix;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Agent JAXB.
 * 
 * @author Andrew
 */
@XmlType(name = "agent")
@XmlRootElement(name = "agent")
public class AgentJAXB extends MatrixBeingJAXB {

	/**
	 * Attack.
	 */
	private int attack;

	/**
	 * Sleaze.
	 */
	private int sleaze;

	public int getAttack() {
		return attack;
	}

	@XmlElement(required = true, nillable = false)
	public void setAttack(final int attack) {
		this.attack = attack;
	}

	public int getSleaze() {
		return sleaze;
	}

	@XmlElement(required = true, nillable = false)
	public void setSleaze(final int sleaze) {
		this.sleaze = sleaze;
	}
}
