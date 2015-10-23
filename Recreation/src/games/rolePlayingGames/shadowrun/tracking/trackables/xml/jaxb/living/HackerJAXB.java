package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.living;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB for hackers/riggers. Not technomancers.
 * 
 * @author Andrew
 */
@XmlType(name = "hacker")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "hacker")
public class HackerJAXB extends CharacterJAXB {
	/**
	 * Rating of cyberdeck/rig.
	 */
	@XmlElement(required = true, nillable = false)
	private int rating;

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

	/**
	 * Data processing.
	 */
	@XmlElement(required = true, nillable = false)
	private int dataProcessing;

	/**
	 * Firewall.
	 */
	@XmlElement(required = true, nillable = false)
	private int firewall;

	public int getRating() {
		return rating;
	}

	public void setRating(final int rating) {
		this.rating = rating;
	}

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

	public int getDataProcessing() {
		return dataProcessing;
	}

	public void setDataProcessing(final int dataProcessing) {
		this.dataProcessing = dataProcessing;
	}

	public int getFirewall() {
		return firewall;
	}

	public void setFirewall(final int firewall) {
		this.firewall = firewall;
	}

	@Override
	public String toString() {
		return "Hacker-" + getName();
	}
}
