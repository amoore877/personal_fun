package games.setting.ambiance.music.selections.base;

import games.setting.ambiance.music.selections.IClimaxingMusicalSelection;
import games.setting.ambiance.music.selections.IWaningMusicalSelection;

/**
 * Selection with multiple stages, that can be altered for increased or
 * decreased intensity at various points.
 * 
 * @author Andrew
 */
public abstract class MultiIntensityMusicalSelection implements
		IClimaxingMusicalSelection, IWaningMusicalSelection {

	// TODO constructor

	@Override
	public void play() {
		// TODO Auto-generated method stub

	}

	@Override
	public void fadeOutAndStop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void decreaseIntensity() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isIntensityDecreasable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void increaseIntensity() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isIntensityIncreasable() {
		// TODO Auto-generated method stub
		return false;
	}

}
