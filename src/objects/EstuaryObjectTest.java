package objects;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

import swampgod.Game;
import swampgod.Stream;


public class EstuaryObjectTest implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -11082015;
	Game g1 = new Game();
	EstuaryObject obj1 = new EstuaryObject();
	Point p = new Point();
	
	@Test
	public void tickTest() {
		assertEquals(0, obj1.getPos());
		g1.tick();
		assertFalse(p == obj1.getPos());
	}
	@Test	
	public void endWaveTest(){
		g1.endWave();
		assertEquals(1, g1.getGameStatus());
	}
	@Test
	public void startWaveTest(){
		g1.startWave();
		assertEquals(1, g1.getGameStatus());
	}
	@Test
	public void inbetweenWaveTest(){
		assertEquals(2, g1.getGameStatus());
	}
	@Test
	public void setPositionTest(){
		//assertEquals(p, obj1.checkPosition());
		Point k= new Point(6,1);
		obj1.setPosition(k);
		assertEquals(k , obj1.checkPosition());
	}
	
		//pick stream
	@Test
	public void pickStreamTest(){
		assertEquals(1, obj1.pickStream());
		assertTrue((0 < obj1.pickStream())&&(obj1.pickStream() < 4));
	}
	@Test
	public void plantTest(){
		Tree tree = new Tree(p);
		assertEquals(8, tree.radius);
		assertEquals(35, tree.percentEat);
		
		Bush bush= new Bush(p);
		assertEquals("Bush", obj1.type);
		assertEquals(5, bush.radius);
		assertEquals(40, bush.percentEat);
	}
	@Test
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
	@Test
	public void crabTest(){
		Crab crab = new Crab();
		assertEquals(11, crab.getHealthValue());
		assertFalse(crab.isGood());
		assertEquals(17, crab.getPointValue());
		assertEquals("Crab", crab.getType());
		assertEquals(5, crab.getSpeed());
	}
	@Test
	public void algaeTest(){
		Algae algae = new Algae();
		assertEquals(10, algae .getHealthValue());
		assertFalse(algae .isGood());
		assertEquals(15, algae .getPointValue());
		assertEquals("Algae", algae .getType());
		assertEquals(2, algae .getSpeed());
	}
	@Test
	public void hazardWasteTest(){
		HazardWaste hazardWaste = new HazardWaste();
		assertEquals(16, hazardWaste.getHealthValue());
		assertFalse(hazardWaste.isGood());
		assertEquals(13, hazardWaste.getPointValue());
		assertEquals("Hazard Waste", hazardWaste.getType());
		assertEquals(7, hazardWaste.getSpeed());
	}

//GOOD OBJECTS
	@Test
	public void fishTest(){
		Fish fish= new Fish();
		assertEquals(7, fish.getHealthValue());
		assertTrue(fish.isGood());
		assertEquals(7, fish.getPointValue());
		assertEquals("Fish", fish.getType());
		assertEquals(18, fish.getSpeed());
	}
	@Test
	public void clamTest(){
		Clam clam = new Clam();
		assertEquals(18, clam.getHealthValue());
		assertTrue(clam.isGood());
		assertEquals(18, clam.getPointValue());
		assertEquals("Clam", clam.getType());
		assertEquals(23, clam.getSpeed());
	}
	@Test
	public void lilyPadTest(){
		LilyPad lilyPad = new LilyPad();
		assertEquals(15, lilyPad.getHealthValue());
		assertTrue(lilyPad.isGood());
		assertEquals(15, lilyPad.getPointValue());
		assertEquals("LilyPad", lilyPad.getType());
		assertEquals(20, lilyPad.getSpeed());
	}


	//move
	@Test
	public void moveTest(){
		obj1.setPosition(p);
		assertEquals(p, obj1.getPos());
		obj1.move();
		Point m=new Point();
		assertEquals(m, obj1.getPos());
	}
	
	@Test
	public void streamObjectTest(){
		Stream stream1 = new Stream(1);
		stream1.createBadObjects(1);
		stream1.createBadObjects(1);
		stream1.createBadObjects(1);
		assertEquals(3, stream1.getbadObjects().size());
		assertEquals(3, stream1.getbadObjects().size());
		
		stream1.createGoodObjects(1);
		stream1.createGoodObjects(1);
		stream1.createGoodObjects(1);
		assertEquals(3, stream1.getgoodObjects().size());
		assertEquals(3, stream1.getGoodObjects().size());
		assertEquals(3, stream1.getgoodObjects().size());
	}
	 
	
}
