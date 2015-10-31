package swampgod;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;


public class EstuaryObjectTest {

	@Test
	public void test() {
		
		Game g1 = new Game();
		EstuaryObject obj1 = new EstuaryObject();
		Point p = new Point();
		
		// Tick
		assertEquals(0, obj1.getPos());
		g1.Tick();
		assertFalse(p == obj1.getPos());
		
		// End wave
		g1.endWave();
		assertEquals(2, g1.getGameStatus());
		
		// Start wave
		g1.startWave();
		assertEquals(1, g1.getGameStatus());
		
		
		fail("Not yet implemented");
	}
	

}
