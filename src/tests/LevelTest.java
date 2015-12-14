package tests;

import swampgod.Level;

import static org.junit.Assert.*;

import java.awt.geom.CubicCurve2D;

import org.junit.Test;

public class LevelTest {

	@Test
	public void getGoodObjectReleaseFrequency() {
		Level l = new Level();
		assertEquals(140, l.getGoodObjectReleaseFrequency(0));
		assertEquals(150, l.getGoodObjectReleaseFrequency(1));
		assertEquals(144, l.getGoodObjectReleaseFrequency(2));
	}
	
	@Test
	public void getBadObjectReleaseFrequency() {
		Level l = new Level();
		assertEquals(75, l.getBadObjectReleaseFrequency(0));
		assertEquals(62, l.getBadObjectReleaseFrequency(1));
		assertEquals(45, l.getBadObjectReleaseFrequency(2));
	}
	
	@Test
	public void plantEatFrequency() {
		Level l = new Level();
		assertEquals(424, l.getPlantEatFrequency(0));
		assertEquals(34, l.getPlantEatFrequency(1));
		assertEquals(2, l.getPlantEatFrequency(2));
	}
	
	@Test
	public void getTotalGoodObject() {
		Level l = new Level();
		assertEquals(10, l.getTotalGoodObjects(0));
		assertEquals(15, l.getTotalGoodObjects(1));
		assertEquals(20, l.getTotalGoodObjects(2));
	}
	
	@Test
	public void getTotalBadObjects() {
		Level l = new Level();
		assertEquals(20, l.getTotalBadObjects(0));
		assertEquals(30, l.getTotalBadObjects(1));
		assertEquals(50, l.getTotalBadObjects(2));
	}
	
//	@Test
//	public void getStreamCurveByPercentage() {
//		Level l = new Level();
//		assertEquals(new CubicCurve2D.Double(0, 0, 20, 35, 75, 70, 80, 100), l.getStreamCurveByPercentage(0));
//		assertEquals(new CubicCurve2D.Double(20, 0, 60, 30, 40, 65, 90, 100), l.getStreamCurveByPercentage(1));
//		assertEquals(new CubicCurve2D.Double(90, 0, 15, 25, 60, 60, 20, 100), l.getStreamCurveByPercentage(2));
//	}
}
