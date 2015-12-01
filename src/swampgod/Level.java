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
	final public static int[] goodObjectReleaseFrequency = {204, 18, 12};
	final public static int[] badObjectReleaseFrequency = {215, 20, 12};
	final public static int[] speedMultiplier = {1,2,3};
	final public static int[] plantEatFrequency = {424, 34, 2};
	final public static int[] totalGoodObjects = {10, 15, 20};
	final public static int[] totalBadObjects = {15, 20, 30};
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
	
	public int getGoodObjectReleaseFrequency (int level) {
		return goodObjectReleaseFrequency[level];
	}
	
	public int getBadObjectReleaseFrequency (int level) {
		return badObjectReleaseFrequency[level];
	}
	
	public int getPlantEatFrequency (int level) {
		return plantEatFrequency[level];
	}
	
	public int getTotalGoodObjects (int level) {
		return totalGoodObjects[level];
	}
	
	public int getTotalBadObjects (int level) {
		return totalBadObjects[level];
	}
	
	public CubicCurve2D getStreamCurveByPercentage(int level){
		return streamCurves[level];
	}
}
