package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.living;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB for hackers/riggers. Not technomancers.
 * 
 * @author Andrew
 */
@XmlType(name = "hacker")
@XmlRootElement(name = "hacker")
public class HackerJAXB extends CharacterJAXB {
	/**
	 * Rating of cyberdeck/rig.
	 */
	private int rating;

	/**
	 * Attack.
	 */
	private int attack;

	/**
	 * Sleaze.
	 */
	private int sleaze;

	/**
	 * Data processing.
	 */
	private int dataProcessing;

	/**
	 * Firewall.
	 */
	private int firewall;

	public int getRating() {
		return rating;
	}

	@XmlElement(required = true, nillable = false)
	public void setRating(final int rating) {
		this.rating = rating;
	}

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

	public int getDataProcessing() {
		return dataProcessing;
	}

	@XmlElement(required = true, nillable = false)
	public void setDataProcessing(final int dataProcessing) {
		this.dataProcessing = dataProcessing;
	}

	public int getFirewall() {
		return firewall;
	}

	@XmlElement(required = true, nillable = false)
	public void setFirewall(final int firewall) {
		this.firewall = firewall;
	}
}
