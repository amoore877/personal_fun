package games.rolePlayingGames.shadowrun.tracking.trackables.matrix.device;

import games.rolePlayingGames.shadowrun.tracking.trackables.item.IShadowrunItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.IMatrixBeing;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.IMatrixDamageableTrackable;

/**
 * A device in Shadowrun, which could be damaged on the Matrix and physically.
 * Marker class.
 * 
 * @author Andrew
 */
public interface IDevice extends IShadowrunItem, IMatrixDamageableTrackable,
		IMatrixBeing {

}
