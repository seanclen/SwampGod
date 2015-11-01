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
		
		
		
		//Bad Object
		BadObject crab = new BadObject("Crab");
		assertEquals(2, crab.getHealthValue());
		assertFalse(crab.isGood());
		assertEquals(3, crab.getPointValue());
		assertEquals("Crab", crab.getType());
		assertEquals(2, crab.getSpeed());

		BadObject algae = new BadObject("Algae");
		assertEquals(3, algae .getHealthValue());
		assertFalse(algae .isGood());
		assertEquals(4, algae .getPointValue());
		assertEquals("Algae", algae .getType());
		assertEquals(1, algae .getSpeed());

		BadObject hazardWaste = new BadObject("Hazard Waste");
		assertEquals(4, hazardWaste.getHealthValue());
		assertFalse(hazardWaste.isGood());
		assertEquals(5, hazardWaste.getPointValue());
		assertEquals("Hazard Waste", hazardWaste.getType());
		assertEquals(3, hazardWaste.getSpeed());


		//Good Object
		GoodObject fish = new GoodObject("Fish");
		assertEquals(4, fish.getHealthValue());
		assertTrue(fish.isGood());
		assertEquals(3, fish.getPointValue());
		assertEquals("Fish", fish.getType());
		assertEquals(3, fish.getSpeed());

		GoodObject clam = new GoodObject("Clam");
		assertEquals(3, clam.getHealthValue());
		assertTrue(clam.isGood());
		assertEquals(4, clam.getPointValue());
		assertEquals("Clam", clam.getType());
		assertEquals(2, clam.getSpeed());

		GoodObject lilyPad = new GoodObject("Lily Pad");
		assertEquals(2, lilyPad.getHealthValue());
		assertTrue(lilyPad.isGood());
		assertEquals(2, lilyPad.getPointValue());
		assertEquals("Lily Pad", lilyPad.getType());
		assertEquals(1, lilyPad.getSpeed());



		//move
		obj1.setPosition(p);
		assertEquals(p, obj1.getPos());
		obj1.move();
		Point m=new Point();
		assertEquals(m, obj1.getPos());
		
		

		fail("Not yet implemented");
	}
}
