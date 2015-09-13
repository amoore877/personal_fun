package games.setting.ambiance.music.selections.base;

import games.setting.ambiance.music.selections.IWaningMusicalSelection;

/**
 * Selection with multiple stages, only decreasing in intensity.
 * 
 * @author Andrew
 */
public abstract class WaningMusicalSelection implements IWaningMusicalSelection {

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

}
