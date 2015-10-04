package games.rolePlayingGames.shadowrun.tracking.trackables.matrix.hacking;

import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.IMatrixBeing;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.IMatrixDamageableTrackable;
import games.rolePlayingGames.tracking.trackable.ICombatTrackable;

/**
 * A Shadowrun device/persona/icon/being that has Attack/Sleaze. Can enter
 * combat. Do not track attack/sleaze for player-controlled things. Marker
 * class.
 * 
 * @author Andrew
 */
public interface IHackingBeing extends IMatrixDamageableTrackable,
		ICombatTrackable, IMatrixBeing {

}
