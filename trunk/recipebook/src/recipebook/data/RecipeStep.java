package recipebook.data;

/**
 * A single step in a recipe.
 * 
 * @author Andrew
 *
 */
public class RecipeStep {

	/**
	 * Instructions in the step.
	 */
	private String myInstruct;

	/**
	 * Time for the step in minutes.
	 */
	private int myTime;

	/**
	 * Construct from save string.
	 * 
	 * @param input
	 */
	public RecipeStep(String input) {
		String[] inputParts = input.split(Separators.DATA_PART_SEPARATOR);
		// TODO element count

		// instructions
		myInstruct = inputParts[0];

		// time
		// TODO num format exception
		myTime = Integer.parseInt(inputParts[1]);
	}

	/**
	 * Output to a save string.
	 * 
	 * @return
	 */
	public String toSaveString() {
		StringBuilder saveString = new StringBuilder(myInstruct);
		saveString.append(Separators.DATA_PART_SEPARATOR);
		saveString.append(myTime);

		return saveString.toString();
	}

	/**
	 * Construct from object data.
	 * 
	 * @param iInstruct
	 * @param iTime
	 */
	public RecipeStep(String iInstruct, int iTime) {
		myInstruct = iInstruct;
		myTime = iTime;
	}

	public String getInstruct() {
		return myInstruct;
	}

	public void setInstruct(String iInstruct) {
		this.myInstruct = iInstruct;
	}

	public int getTime() {
		return myTime;
	}

	public void setTime(int iTime) {
		this.myTime = iTime;
	}

	/**
	 * To printing string.
	 */
	public String toPrintString() {
		StringBuilder out = new StringBuilder(myInstruct);
		out.append("\n");

		String timeToPrint;
		if (myTime > 60) {
			int minutes = myTime % 60;
			int hours = (myTime - minutes) / 60;

			timeToPrint = hours + "h " + minutes + "m";
		} else {
			timeToPrint = myTime + "m";
		}

		out.append(timeToPrint);

		return out.toString();
	}
}
