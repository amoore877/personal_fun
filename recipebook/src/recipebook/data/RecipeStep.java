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
	 * Time for the step.
	 */
	private int myTime;

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

}
