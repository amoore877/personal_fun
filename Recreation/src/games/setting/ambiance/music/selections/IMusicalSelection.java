package games.setting.ambiance.music.selections;

/**
 * A playable musical selection.
 * 
 * @author Andrew
 */
public interface IMusicalSelection {

	/**
	 * Play music in initial state.
	 */
	public void play();

	/**
	 * Fade out and stop music.
	 */
	public void fadeOutAndStop();

	/**
	 * Stop music.
	 */
	public void stop();

	/**
	 * Pause music.
	 */
	public void pause();

	@Override
	public String toString();
}
