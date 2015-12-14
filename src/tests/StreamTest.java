package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Test;

import objects.Algae;
import swampgod.Stream;

public class StreamTest {

	@Test
	public void initialization() {
		Stream s = new Stream(0);
		assertEquals(0, s.getId());
		assertEquals(0, s.getGoodObjects().size());
		assertEquals(0, s.getBadObjects().size());
		assertEquals(0, s.getObjectsToDraw().size());
		assertEquals(new Rectangle(0, 0, 320, 400), s.getBounds());
	}
	
	@Test
	public void createBadObjectAt() {
		Stream s = new Stream(0);
		Algae a = new Algae();
		s.createBadObjectAt(a, 10);
		assertEquals(1, s.getBadObjects().size());
		assertEquals(1, s.getObjectsToDraw().size());
	}
	
	@Test
	public void addGoodObjects() {
		Stream s = new Stream(0);
		s.createGoodObjects(10);
		assertEquals(10, s.getGoodObjects().size());
		assertEquals(10, s.getObjectsToDraw().size());
	}
	
	@Test
	public void addBadObjects() {
		Stream s = new Stream(0);
		s.createBadObjects(10);
		assertEquals(10, s.getBadObjects().size());
		assertEquals(10, s.getBadObjects().size());
	}
	
	@Test
	public void intersectsStream() {
		Stream s = new Stream(0);
		assertTrue(s.intersectsStream(s.getBounds()));
	}
}
