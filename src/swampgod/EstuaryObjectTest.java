package swampgod;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;


public class EstuaryObjectTest implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5055127386658248567L;
	Game g1 = new Game();
	EstuaryObject obj1 = new EstuaryObject();
	Point p = new Point();
	
	@Test
	public void tickTest() {
		assertEquals(0, obj1.getPos());
		g1.Tick();
		assertFalse(p == obj1.getPos());
	}
		
	public void endWaveTest(){
		g1.endWave();
		assertEquals(1, g1.getGameStatus());
	}
	
	public void startWaveTest(){
		g1.startWave();
		assertEquals(1, g1.getGameStatus());
	}
	
	public void inbetweenWaveTest(){
		assertEquals(2, g1.getGameStatus());
	}
	
	public void setPositionTest(){
		assertEquals(p, obj1.checkPosition());
		Point k= new Point(6,1);
		obj1.setPosition(k);
		assertEquals(k , obj1.checkPosition());
	}
	
		//pick stream
	public void pickStreamTest(){
		assertEquals(2, obj1.pickStream());
		assertTrue((0 < obj1.pickStream())&&(obj1.pickStream() < 4));
	}
	
	public void plantTest(){
		Tree tree = new Tree(p);
		assertEquals("Tree", obj1.type);
		assertEquals(10, tree.radius);
		assertEquals(20, tree.percentEat);
		
		Bush bush= new Bush(p);
		assertEquals("Bush", obj1.type);
		assertEquals(5, bush.radius);
		assertEquals(40, bush.percentEat);
	}
	
	public void eatTest(){
		Bush bush= new Bush(p);
		ArrayList<BadObject> bo= new ArrayList<BadObject>(2);
		Crab a= new Crab();
		bo.add(a);
		Algae b= new Algae();
		bo.add(b);
		assertTrue(bo.contains(a));
		bush.eat();
		assertFalse(bo.contains(a));
	}
	
	@Test
	public void checkRadiusTest(){
		Bush bush= new Bush(p);
		Crab a= new Crab();
		assertEquals(a, bush.checkRadius());
	}
	
	
//BAD OBJECTS
	public void crabTest(){
		Crab crab = new Crab();
		assertEquals(2, crab.getHealthValue());
		assertFalse(crab.isGood());
		assertEquals(3, crab.getPointValue());
		assertEquals("Crab", crab.getType());
		assertEquals(2, crab.getSpeed());
	}
	
	public void algaeTest(){
		Algae algae = new Algae();
		assertEquals(3, algae .getHealthValue());
		assertFalse(algae .isGood());
		assertEquals(4, algae .getPointValue());
		assertEquals("Algae", algae .getType());
		assertEquals(1, algae .getSpeed());
	}
	
	public void hazardWasteTest(){
		HazardWaste hazardWaste = new HazardWaste();
		assertEquals(4, hazardWaste.getHealthValue());
		assertFalse(hazardWaste.isGood());
		assertEquals(5, hazardWaste.getPointValue());
		assertEquals("Hazard Waste", hazardWaste.getType());
		assertEquals(3, hazardWaste.getSpeed());
	}

//GOOD OBJECTS
	public void fishTest(){
		Fish fish= new Fish();
		assertEquals(4, fish.getHealthValue());
		assertTrue(fish.isGood());
		assertEquals(3, fish.getPointValue());
		assertEquals("Fish", fish.getType());
		assertEquals(3, fish.getSpeed());
	}
	
	public void clamTest(){
		Clam clam = new Clam();
		assertEquals(3, clam.getHealthValue());
		assertTrue(clam.isGood());
		assertEquals(4, clam.getPointValue());
		assertEquals("Clam", clam.getType());
		assertEquals(2, clam.getSpeed());
	}

	public void lilyPadTest(){
		LilyPad lilyPad = new LilyPad();
		assertEquals(2, lilyPad.getHealthValue());
		assertTrue(lilyPad.isGood());
		assertEquals(2, lilyPad.getPointValue());
		assertEquals("Lily Pad", lilyPad.getType());
		assertEquals(1, lilyPad.getSpeed());
	}


	//move
	public void moveTest(){
		obj1.setPosition(p);
		assertEquals(p, obj1.getPos());
		obj1.move();
		Point m=new Point();
		assertEquals(m, obj1.getPos());
	}
	
	public void streamObjectTest(){
		Stream stream1 = new Stream();
		stream1.createBadObjects(1);
		stream1.createBadObjects(1);
		stream1.createBadObjects(1);
		assertEquals(3, stream1.badObjects.size());
		
		stream1.createGoodObjects(1);
		stream1.createGoodObjects(1);
		stream1.createGoodObjects(1);
		assertEquals(3, stream1.goodObjects.size());
	}
	 
	
}
