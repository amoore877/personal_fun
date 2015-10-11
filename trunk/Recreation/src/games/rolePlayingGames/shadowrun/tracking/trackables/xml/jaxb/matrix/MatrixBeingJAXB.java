package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.matrix;

import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.AbilityJAXB;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ AgentJAXB.class, AutoPilotDeviceJAXB.class })
public abstract class MatrixBeingJAXB {

	/**
	 * Name.
	 */
	@XmlElement(required = true, nillable = false)
	private String name;

	/**
	 * Rating.
	 */
	@XmlElement(required = true, nillable = false)
	private int rating;

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

	/**
	 * Matrix abilities/programs.
	 */
	@XmlElementWrapper
	@XmlElement(required = true, nillable = false)
	private ArrayList<AbilityJAXB> abilities;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(final int rating) {
		this.rating = rating;
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

	public ArrayList<AbilityJAXB> getAbilities() {
		return abilities;
	}

	public void setAbilities(final ArrayList<AbilityJAXB> abilities) {
		this.abilities = abilities;
	}
}
