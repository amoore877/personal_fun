package games.setting.ambiance.music.selections;

/**
 * Music that can increase in intensity over some number of stages.
 * 
 * @author Andrew
 */
public interface IClimaxingMusicalSelection extends
		IVariableIntensityMusicalSelection {

	/**
	 * Increase intensity to the next stage.
	 */
	public void increaseIntensity();

	/**
	 * @return true if the intensity can be increased, false otherwise.
	 */
	public boolean isIntensityIncreasable();
}
