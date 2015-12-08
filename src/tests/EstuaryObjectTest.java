package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

import objects.Algae;
import objects.BadObject;
import objects.Bush;
import objects.Clam;
import objects.Crab;
import objects.EstuaryObject;
import objects.Fish;
import objects.HazardWaste;
import objects.LilyPad;
import objects.Tree;
import swampgod.Game;
import swampgod.Main.GameState;
import swampgod.Stream;


public class EstuaryObjectTest implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -11082015;
	Game g1 = new Game();
	EstuaryObject obj1 = new EstuaryObject();
	Point p = new Point();
	
//	@Test
//	public void tickTest() {
//		Point pt = new Point(0,0);
//		assertEquals(pt, obj1.getPos());
//		g1.tick();
//		assertFalse(p == obj1.getPos());
//	}
	@Test	
	public void endWaveTest(){
		g1.endWave();
		assertEquals(GameState.UPGRADE_STATE, g1.getGameStatus());
	}

	@Test
	public void setPositionTest(){
		Point k= new Point(6,1);
		obj1.setPosition(k);
		assertFalse(obj1.checkPosition());
	}
	
	@Test
	public void pickStreamTest(){
		assertTrue((0 < obj1.pickStream())&&(obj1.pickStream() < 4));
	}
	
	@Test
	public void checkRadiusTest(){
		ArrayList<BadObject> bo= new ArrayList<BadObject>(2);
		Bush bush= new Bush(p);
		Crab a= new Crab();
		bo.add(a);
		//assertEquals(a, bush.checkRadius(bo));
	}
	
	
//BAD OBJECTS
	@Test
	public void crabTest(){
		Crab crab = new Crab();
		assertEquals(-25, crab.getHealthValue());
		assertFalse(crab.isGood());
		assertEquals(-17, crab.getPointValue());
		assertEquals("Crab", crab.getType());
	}
	@Test
	public void algaeTest(){
		Algae algae = new Algae();
		assertEquals(-15, algae .getHealthValue());
		assertFalse(algae .isGood());
		assertEquals(-15, algae .getPointValue());
		assertEquals("Algae", algae .getType());
	}
	@Test
	public void hazardWasteTest(){
		HazardWaste hazardWaste = new HazardWaste();
		assertEquals(-40, hazardWaste.getHealthValue());
		assertFalse(hazardWaste.isGood());
		assertEquals(-13, hazardWaste.getPointValue());
		assertEquals("Hazard Waste", hazardWaste.getType());
		assertTrue(hazardWaste.edible());
	}
	
	@Test
	public void badObjectTest(){
//		BadObject bk;
//		boolean bo=false;
//		g1.getStreams()[1].createBadObjects(1);
//		if(g1.getStreams()[1].getBadObjects().)
		
	}

//GOOD OBJECTS
	@Test
	public void fishTest(){
		Fish fish= new Fish();
		assertEquals(5, fish.getHealthValue());
		assertTrue(fish.isGood());
		assertEquals(7, fish.getPointValue());
		assertEquals("Fish", fish.getType());
		assertTrue(fish.isGood());
	}
	@Test
	public void clamTest(){
		Clam clam = new Clam();
		assertEquals(8, clam.getHealthValue());
		assertTrue(clam.isGood());
		assertEquals(18, clam.getPointValue());
		assertEquals("Clam", clam.getType());
	}
	@Test
	public void lilyPadTest(){
		LilyPad lilyPad = new LilyPad();
		assertEquals(7, lilyPad.getHealthValue());
		assertTrue(lilyPad.isGood());
		assertEquals(15, lilyPad.getPointValue());
		assertEquals("LilyPad", lilyPad.getType());
	}


	//Plants
	
	@Test
	public void treeTest(){
		Tree tr= new Tree(p);
		assertFalse(tr.canPlace());
		tr.setCanPlace(true);
		assertTrue(tr.canPlace());
		assertEquals(15, tr.getPointsPerPlants());
		assertEquals("Tree", tr.getType());
		assertEquals(p, tr.getPosition());
		assertEquals(9, tr.getRadius());
		Point pt= new Point(6,1);
		tr.setPosition(pt);
		assertEquals(pt, tr.getPosition());
		assertEquals(40, tr.getPercentEat());
	}
	
	//move
	@Test
	public void moveTest(){
		obj1.setPosition(p);
		assertEquals(p, obj1.getPos());
		obj1.move(g1.getWaveNumber());
		Point m=new Point();
		assertEquals(m, obj1.getPos());
	}
	
//	@Test
//	public void streamObjectTest(){
//		Stream stream1 = new Stream(1);
//		stream1.createBadObjects(1);
//		stream1.createBadObjects(1);
//		stream1.createBadObjects(1);
//		assertEquals(3, stream1.getBadObjects().size());
//		assertEquals(3, stream1.getBadObjects().size());
//		
//		stream1.createGoodObjects(1);
//		stream1.createGoodObjects(1);
//		stream1.createGoodObjects(1);
//		assertEquals(3, stream1.getGoodObjects().size());
//		assertEquals(3, stream1.getGoodObjects().size());
//		assertEquals(3, stream1.getGoodObjects().size());
//	}
	 
	
}
