package swampgod;

import java.awt.geom.CubicCurve2D;

public final class Level {
	
	/**
	 * Constant datatypes to use in different levels.
	 * Each position in the array is a different level
	 * 
	 * i.e. int[3] foo = {23, 5, 12} has three levels such that:
	 *      level 1  : foo = 23
	 *      level 2  : foo = 5
	 *      level 12 : foo = 12
	 */
	final public static int[] goodObjectReleaseFrequency = {140, 150, 144};
	final public static int[] badObjectReleaseFrequency = {75, 62, 45};
	final public static int[] speedMultiplier = {1,2,3};
	final public static int[] plantEatFrequency = {424, 34, 2};
	final public static int[] totalGoodObjects = {10, 15, 20};
	final public static int[] totalBadObjects = {20, 30, 50};
	/**
	 * Float parameters represents location to percentage of space from the origin.
	 * i.e. a stream with bounds (0, 0, 100, 100) with CubicCurve(x, y, ... )
	 *     x = 1 gives 100
	 *     x = 0 gives 0
	 *     x = .5 gives 5
	 */ 
	final public static CubicCurve2D[] streamCurves = {
			new CubicCurve2D.Double(0, 0, 20, 35, 75, 70, 80, 100),
			new CubicCurve2D.Double(20, 0, 60, 30, 40, 65, 90, 100),
			new CubicCurve2D.Double(90, 0, 15, 25, 60, 60, 20, 100)};
	
	/**
	 * @param level - the game waveNumber
	 * gets the goodObject release frequency for param level
	 * @return - an int, based on the attribute
	 */
	public int getGoodObjectReleaseFrequency (int level) {
		return goodObjectReleaseFrequency[level];
	}
	
	/**
	 * @param level - the game waveNumber
	 * gets the badObject release frequency for param level
	 * @return - an int, based on the attribute
	 */
	public int getBadObjectReleaseFrequency (int level) {
		return badObjectReleaseFrequency[level];
	}
	
	/**
	 * @param level - the game waveNumber
	 * gets the plantEat release frequency for int param level
	 * @return - an int, based on the attribute
	 */
	public int getPlantEatFrequency (int level) {
		return plantEatFrequency[level];
	}
	
	/**
	 * @param level - the game waveNumber
	 * gets the totalGoodObjects for int param level
	 * @return - an int, based on the attribute
	 */
	public int getTotalGoodObjects (int level) {
		return totalGoodObjects[level];
	}
	
	/**
	 * @param level - the game waveNumber
	 * gets the totalBadObjects for int param level
	 * @return - an int, based on the attribute
	 */
	public int getTotalBadObjects (int level) {
		return totalBadObjects[level];
	}
	
	/**
	 * @param level - the game waveNumber
	 * gets the streamCurve for the int param
	 * @return - a CubicCurve2D, based on the level
	 */
	public CubicCurve2D getStreamCurveByPercentage(int level){
		return streamCurves[level];
	}
}
