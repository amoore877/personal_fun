package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.matrix;

import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.AbilityJAXB;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Matrix beings JAXB.
 * 
 * @author Andrew
 *
 */
@XmlType(name = "matrixBeing")
@XmlSeeAlso({ AgentJAXB.class, AutoPilotDeviceJAXB.class })
public abstract class MatrixBeingJAXB {

	/**
	 * Name.
	 */
	private String name;

	/**
	 * Rating.
	 */
	private int rating;

	/**
	 * Data processing.
	 */
	private int dataProcessing;

	/**
	 * Firewall.
	 */
	private int firewall;

	/**
	 * Matrix abilities/programs.
	 */
	private ArrayList<AbilityJAXB> abilities;

	public String getName() {
		return name;
	}

	@XmlElement(required = true, nillable = false)
	public void setName(final String name) {
		this.name = name;
	}

	public int getRating() {
		return rating;
	}

	@XmlElement(required = true, nillable = false)
	public void setRating(final int rating) {
		this.rating = rating;
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

	public ArrayList<AbilityJAXB> getAbilities() {
		return abilities;
	}

	@XmlElementWrapper
	@XmlElement(required = true, nillable = false)
	public void setAbilities(final ArrayList<AbilityJAXB> abilities) {
		this.abilities = abilities;
	}
}
