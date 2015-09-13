package ruu.fuzzy.hw6.impl.speeding;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidParameterException;

import ruu.fuzzy.hw6.fuzzifier.category.AbstractCategory;
import ruu.fuzzy.hw6.fuzzifier.category.impl.InputCategory;
import ruu.fuzzy.hw6.fuzzifier.category.impl.OutputCategory;
import ruu.fuzzy.hw6.fuzzifier.division.AbstractDivision;
import ruu.fuzzy.hw6.fuzzifier.division.impl.TriangularDivision;

import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.XYLine;
import com.googlecode.charts4j.XYLineChart;

/**
 * Fuzzy speeding implementation.
 * 
 * Example of controlling a vehicle's speed.
 * 
 * Inputs:
 * 
 * Speed limit
 * 
 * Current speed
 * 
 * Rain conditions
 * 
 * Police presence
 * 
 * Outputs:
 * 
 * Acceleration
 * 
 * @author Andrew
 *
 */
public class FuzzySpeeding {

	/**
	 * Acceleration category.
	 */
	private final OutputCategory<TriangularDivision> myAcceleration;

	/**
	 * Police category.
	 */
	private final InputCategory<TriangularDivision> myPolicePresence;

	/**
	 * Rain category.
	 */
	private final InputCategory<TriangularDivision> myRainConditions;

	/**
	 * Current speed category.
	 */
	private final InputCategory<TriangularDivision> myCurrentSpeed;

	/**
	 * Speed limit category.
	 */
	private final InputCategory<TriangularDivision> mySpeedLimit;

	/**
	 * Constructor.
	 * 
	 * @throws InvalidParameterException
	 *             on invalid data.
	 */
	private FuzzySpeeding() throws InvalidParameterException {
		try {
			/**
			 * Inputs.
			 */
			// speed limit
			mySpeedLimit = addSpeedLimitData();

			// current speed
			myCurrentSpeed = addCurrentSpeedData();

			// rain conditions
			myRainConditions = addRainConditionsData();

			// police presence
			myPolicePresence = addPolicePresenceData();

			/**
			 * Output
			 */
			// acceleration
			myAcceleration = addAccelerationData();
		} catch (final InvalidParameterException iException) {
			iException.printStackTrace();
			throw iException;
		}
	}

	/**
	 * Main.
	 * 
	 * @param iArgs
	 *            no arguments used.
	 */
	public static void main(final String[] iArgs) {
		System.out
				.println("Note: To view graphs, you must have an internet connection.");
		new FuzzySpeeding().start();
	}

	/**
	 * Start application work.
	 */
	private void start() {
		System.out.println("Starting...");

		boolean running = true;
		final BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		while (running) {
			System.out
					.println("Enter domain value to get confidence for all categories under all divisions."
							+ "\nEnter 'quit' to quit.");

			try {
				final String domainValueString = reader.readLine();

				if (domainValueString.equalsIgnoreCase("quit")) {
					System.out.println("User requested an exit...");
					running = false;
				} else {
					final double domainValue = Double
							.parseDouble(domainValueString);

					final StringBuilder results = new StringBuilder();

					results.append("For domain value [" + domainValue + "]:");

					results.append("\n\tAcceleration:");
					for (final AbstractDivision division : myAcceleration
							.getDivisions()) {
						results.append("\n\t\t" + division.toString() + ": ["
								+ division.getConfidenceValue(domainValue)
								+ "]");
					}

					results.append("\n\tPolice Presence:");
					for (final AbstractDivision division : myPolicePresence
							.getDivisions()) {
						results.append("\n\t\t" + division.toString() + ": ["
								+ division.getConfidenceValue(domainValue)
								+ "]");
					}

					results.append("\n\tRain Conditions:");
					for (final AbstractDivision division : myRainConditions
							.getDivisions()) {
						results.append("\n\t\t" + division.toString() + ": ["
								+ division.getConfidenceValue(domainValue)
								+ "]");
					}

					results.append("\n\tCurrent Speed:");
					for (final AbstractDivision division : myCurrentSpeed
							.getDivisions()) {
						results.append("\n\t\t" + division.toString() + ": ["
								+ division.getConfidenceValue(domainValue)
								+ "]");
					}

					results.append("\n\tSpeed Limit:");
					for (final AbstractDivision division : mySpeedLimit
							.getDivisions()) {
						results.append("\n\t\t" + division.toString() + ": ["
								+ division.getConfidenceValue(domainValue)
								+ "]");
					}

					System.out.println(results.toString());
				}
			} catch (final IOException iException) {
				iException.printStackTrace();
			} catch (final NumberFormatException iException) {
				iException.printStackTrace();
			}
		}

		System.out.println("Exiting...");
	}

	/**
	 * Add acceleration data.
	 * 
	 * @return acceleration category.
	 */
	private OutputCategory<TriangularDivision> addAccelerationData() {
		final OutputCategory<TriangularDivision> acceleration = new OutputCategory<TriangularDivision>(
				0, 100, "Acceleration");
		final XYLine slamBrakesLine = addDivision(acceleration, "Slam Brakes",
				0, 15, Color.CRIMSON);
		final XYLine slowLotLine = addDivision(acceleration, "Slow a lot", 10,
				25, Color.ORANGE);
		final XYLine slowLittleLine = addDivision(acceleration,
				"Slow a little", 20, 30, Color.YELLOWGREEN);
		final XYLine maintainLine = addDivision(acceleration, "Maintain Speed",
				25, 50, Color.GREEN);
		final XYLine speedLittleLine = addDivision(acceleration,
				"Speed a little", 45, 70, Color.DARKGREEN);
		final XYLine speedLotLine = addDivision(acceleration, "Speed a lot",
				65, 90, Color.BLUE);
		final XYLine allTheWayLine = addDivision(acceleration, "All the way",
				85, 100, Color.BLUEVIOLET);
		final XYLineChart accelerationChart = GCharts.newXYLineChart(
				slamBrakesLine, slowLotLine, slowLittleLine, maintainLine,
				speedLittleLine, speedLotLine, allTheWayLine);
		accelerationChart.setTitle("Acceleration");
		accelerationChart.setSize(750, 400);
		accelerationChart.setMargins(0, 10, 0, 1);
		accelerationChart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(
				"Acceleration", "10", "20", "30", "40", "50", "60", "70", "80",
				"90", "100"));
		accelerationChart.addYAxisLabels(AxisLabelsFactory.newAxisLabels(
				"Confidence", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7",
				"0.8", "0.9", "1.0"));
		openWebpage(accelerationChart);

		return acceleration;
	}

	/**
	 * Add police presence data.
	 * 
	 * @return police category.
	 */
	private InputCategory<TriangularDivision> addPolicePresenceData() {
		final InputCategory<TriangularDivision> policePresence = new InputCategory<TriangularDivision>(
				0, 100, "Police Presence");
		final XYLine noneLine = addDivision(policePresence, "None", 0, 10,
				Color.LIGHTBLUE);
		final XYLine veryLittleLine = addDivision(policePresence,
				"Very little", 5, 20, Color.LIGHTGREEN);
		final XYLine littleLine = addDivision(policePresence, "Little", 10, 40,
				Color.GREEN);
		final XYLine moderateLine = addDivision(policePresence, "Moderate", 25,
				60, Color.YELLOWGREEN);
		final XYLine heavyLine = addDivision(policePresence, "Heavy", 50, 80,
				Color.ORANGE);
		final XYLine lockdownLine = addDivision(policePresence, "Lockdown", 60,
				100, Color.RED);
		final XYLineChart policeChart = GCharts.newXYLineChart(noneLine,
				veryLittleLine, littleLine, moderateLine, heavyLine,
				lockdownLine);
		policeChart.setTitle("Police Presence");
		policeChart.setSize(750, 400);
		policeChart.setMargins(0, 10, 0, 1);
		policeChart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(
				"Police Presence", "10", "20", "30", "40", "50", "60", "70",
				"80", "90", "100"));
		policeChart.addYAxisLabels(AxisLabelsFactory.newAxisLabels(
				"Confidence", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7",
				"0.8", "0.9", "1.0"));
		openWebpage(policeChart);

		return policePresence;
	}

	/**
	 * Add rain conditions data.
	 * 
	 * @return rain category.
	 */
	private InputCategory<TriangularDivision> addRainConditionsData() {
		final InputCategory<TriangularDivision> rainConditions = new InputCategory<TriangularDivision>(
				0, 100, "Rain Conditions");
		final XYLine noRainLine = addDivision(rainConditions, "No Rain", 0, 20,
				Color.DARKGREEN);
		final XYLine drizzleLine = addDivision(rainConditions, "Drizzle", 10,
				30, Color.DARKMAGENTA);
		final XYLine lightLine = addDivision(rainConditions, "Light", 20, 50,
				Color.TOMATO);
		final XYLine moderateLine = addDivision(rainConditions, "Moderate", 40,
				80, Color.DEEPPINK);
		final XYLine heavyLine = addDivision(rainConditions, "Heavy", 60, 90,
				Color.DARKSALMON);
		final XYLine torrentialLine = addDivision(rainConditions, "Torrential",
				70, 100, Color.GOLDENROD);
		final XYLineChart rainChart = GCharts
				.newXYLineChart(noRainLine, drizzleLine, lightLine,
						moderateLine, heavyLine, torrentialLine);
		rainChart.setTitle("Rain Conditions");
		rainChart.setSize(750, 400);
		rainChart.setMargins(0, 10, 0, 1);
		rainChart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(
				"Rain Conditions", "10", "20", "30", "40", "50", "60", "70",
				"80", "90", "100"));
		rainChart.addYAxisLabels(AxisLabelsFactory.newAxisLabels("Confidence",
				"0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9",
				"1.0"));
		openWebpage(rainChart);

		return rainConditions;
	}

	/**
	 * Add current speed data.
	 * 
	 * @return current speed category.
	 */
	private InputCategory<TriangularDivision> addCurrentSpeedData() {
		final InputCategory<TriangularDivision> currentSpeed = new InputCategory<TriangularDivision>(
				0, 100, "Current Speed (mph)");
		final XYLine verySlowLine = addDivision(currentSpeed, "Very Slow", 0,
				15, Color.BLUE);
		final XYLine slowLine = addDivision(currentSpeed, "Slow", 10, 25,
				Color.BLACK);
		final XYLine moderateLine = addDivision(currentSpeed, "Moderate", 20,
				40, Color.CHOCOLATE);
		final XYLine fastLine = addDivision(currentSpeed, "Fast", 35, 55,
				Color.CRIMSON);
		final XYLine veryFastLine = addDivision(currentSpeed, "Very Fast", 45,
				75, Color.DARKSALMON);
		final XYLine tooFastLine = addDivision(currentSpeed, "Too Fast", 65,
				100, Color.BROWN);
		final XYLineChart currentSpeedChart = GCharts.newXYLineChart(
				verySlowLine, slowLine, moderateLine, fastLine, veryFastLine,
				tooFastLine);
		currentSpeedChart.setTitle("Current Speed");
		currentSpeedChart.setSize(750, 400);
		currentSpeedChart.setMargins(0, 100, 0, 1);
		currentSpeedChart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(
				"Current Speed", "10", "20", "30", "40", "50", "60", "70",
				"80", "90", "100"));
		currentSpeedChart.addYAxisLabels(AxisLabelsFactory.newAxisLabels(
				"Confidence", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7",
				"0.8", "0.9", "1.0"));
		openWebpage(currentSpeedChart);

		return currentSpeed;
	}

	/**
	 * Add speed limit data.
	 * 
	 * @return speed limit category.
	 */
	private InputCategory<TriangularDivision> addSpeedLimitData() {
		final InputCategory<TriangularDivision> speedLimit = new InputCategory<TriangularDivision>(
				5, 100, "Speed Limit (mph)");
		final XYLine hazardLine = addDivision(speedLimit, "Hazard", 5, 15,
				Color.AQUA);
		final XYLine residentialLine = addDivision(speedLimit, "Residential",
				10, 30, Color.BISQUE);
		final XYLine sideStreetLine = addDivision(speedLimit, "Side Street",
				25, 40, Color.BLUEVIOLET);
		final XYLine mainStreetLine = addDivision(speedLimit, "Main Street",
				35, 55, Color.CHOCOLATE);
		final XYLine highwayLine = addDivision(speedLimit, "Highway", 50, 100,
				Color.CRIMSON);
		final XYLineChart speedLimitChart = GCharts.newXYLineChart(hazardLine,
				residentialLine, sideStreetLine, mainStreetLine, highwayLine);
		speedLimitChart.setTitle("Speed Limit");
		speedLimitChart.setSize(750, 400);
		speedLimitChart.setMargins(0, 100, 0, 1);
		speedLimitChart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(
				"Speed Limit", "10", "20", "30", "40", "50", "60", "70", "80",
				"90", "100"));
		speedLimitChart.addYAxisLabels(AxisLabelsFactory.newAxisLabels(
				"Confidence", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7",
				"0.8", "0.9", "1.0"));
		openWebpage(speedLimitChart);

		return speedLimit;
	}

	/**
	 * Add a division to the category.
	 * 
	 * @param iCategory
	 *            the category.
	 * @param iName
	 *            name of the division.
	 * @param iMin
	 *            max of the division.
	 * @param iMax
	 *            min of the division.
	 * @param iColor
	 *            color for the returned line.
	 * @return XYLine for the division.
	 */
	private static XYLine addDivision(
			final AbstractCategory<TriangularDivision> iCategory,
			final String iName, final double iMin, final double iMax,
			final Color iColor) {
		final TriangularDivision division = new TriangularDivision(iMin, iMax,
				iName);
		iCategory.addDivision(division);
		final Data xData = new Data(new double[] {
				division.getMinDomainValue(), division.getMidpoint(),
				division.getMaxDomainValue() });
		final Data yData = DataUtil.scale(new double[] { 0, 1, 0 });
		final XYLine xyLine = Plots.newXYLine(xData, yData);
		xyLine.setColor(iColor);
		xyLine.setLegend(iName);
		xyLine.addTextMarker(iName, iColor, 8, 1);
		return xyLine;
	}

	/**
	 * Open a webpage.
	 * 
	 * @param iUri
	 *            URI.
	 */
	public static void openWebpage(final URI iUri) {
		final Desktop desktop = Desktop.isDesktopSupported() ? Desktop
				.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(iUri);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Open a webpage.
	 * 
	 * @param iUrl
	 *            URL.
	 */
	public static void openWebpage(final URL iUrl) {
		try {
			openWebpage(iUrl.toURI());
		} catch (final URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open a webpage.
	 * 
	 * @param iUrl
	 *            URL.
	 */
	public static void openWebpage(final String iUrl) {
		try {
			openWebpage(new URI(iUrl));
		} catch (final URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static void openWebpage(final XYLineChart iChart) {
		openWebpage(iChart.toURLString().replaceAll(",", "%2C")
				.replaceAll("\\|", "%7C"));
	}
}
