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
		
		//check position
		assertEquals(p, obj1.checkPosition());
		Point k= new Point(6,1);
		obj1.setPosition(k);
		assertEquals(k , obj1.checkPosition());
		
		//pick stream
		assertEquals(2, obj1.pickStream());
		assertTrue((0 < obj1.pickStream())&&(obj1.pickStream() < 4));
		
		//plant
		Plant tree = new Plant("Tree", p);
		assertEquals("Tree", obj1.type);
		assertEquals(10, tree.radius);
		assertEquals(20, tree.percentEat);
		
		Plant bush = new Plant("Bush", p);
		assertEquals("Bush", obj1.type);
		assertEquals(5, bush.radius);
		assertEquals(40, bush.percentEat);
		
		//eat
		
		fail("Not yet implemented");
	}
}
