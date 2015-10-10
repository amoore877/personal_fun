package games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.living;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Basic character JAXB. Not a hacker or technomancer.
 * 
 * @author Andrew
 *
 */
@XmlType(name = "character")
@XmlRootElement(name = "character")
@XmlSeeAlso({ HackerJAXB.class, TechnomancerJAXB.class })
public class CharacterJAXB extends LivingBeingJAXB {

}
