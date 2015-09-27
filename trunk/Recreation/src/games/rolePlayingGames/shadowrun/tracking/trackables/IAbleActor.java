package games.rolePlayingGames.shadowrun.tracking.trackables;

import games.rolePlayingGames.shadowrun.tracking.notes.ability.AbstractAbility;

import java.util.ArrayList;

/**
 * A being of some sort that has skills/programs simulating skills/spells.
 * 
 * @author Andrew
 */
public interface IAbleActor extends IShadowrunTrackable {

	/**
	 * @return list of abilities for this actor.
	 */
	ArrayList<AbstractAbility> getAbilities();

}
