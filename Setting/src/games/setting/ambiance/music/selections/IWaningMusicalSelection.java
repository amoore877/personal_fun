package games.setting.ambiance.music.selections;

/**
 * Music that can decrease in intensity over some number of stages.
 * 
 * @author Andrew
 */
public interface IWaningMusicalSelection extends
		IVariableIntensityMusicalSelection {

	/**
	 * Decreasing intensity to the previous stage.
	 */
	public void decreaseIntensity();

	/**
	 * @return true if the intensity can be decreased, false otherwise.
	 */
	public boolean isIntensityDecreasable();
}
