package swampgod;

public class Level {
	
	/**
	 * Declare variables to use in differenct levels.
	 * Each position in the array is a different level
	 * 
	 * i.e. int[3] foo = {23, 5, 12} has three levels such that:
	 *      level 1  : foo = 23
	 *      level 2  : foo = 5
	 *      level 12 : foo = 12
	 */
	int[] goodObjectReleaseFrequency = {80, 45, 3};
	int[] badObjectReleaseFrequency = {45, 34, 3};
	int[] plantEatFrequency = {424, 34, 2};
	int[] totalGoodObjects = {10, 15, 20};
	int[] totalBadObjects = {15, 20, 17000000};
	
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
}
