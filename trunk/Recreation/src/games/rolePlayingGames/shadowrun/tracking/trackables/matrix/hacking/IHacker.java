package games.rolePlayingGames.shadowrun.tracking.trackables.matrix.hacking;

import games.rolePlayingGames.shadowrun.tracking.notes.damage.character.AbstractCharacterDamageNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.living.ILivingBeing;

/**
 * A spider/decker/rigger. A hacking-capable (except rigger) person with a
 * Matrix presence. Marker class.
 * 
 * @author Andrew
 */
public interface IHacker extends IHackingBeing,
		ILivingBeing<AbstractCharacterDamageNote> {

}
