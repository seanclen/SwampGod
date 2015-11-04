package swampgod;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;

public class GameTest implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4704623357378697027L;

	@Test
	public void gameStateTest() {
		Game g1 = new Game();
		assertEquals(1,g1.gameState);
	}
	
	@Test
	public void healthTest() {
		Game g1 = new Game();
		assertEquals(0,g1.health);
		g1.updateHealth(30);
		assertEquals(30, g1.health);
	}
	
	@Test
	public void plantArrTest() {
		Game g1 = new Game();
		assertEquals(null,g1.plants);
	}
	
	@Test
	public void pointTest() {
		Game g1 = new Game();
		assertEquals(0,g1.getPoints());
	}
	
	@Test
	public void waveTest() {
		Game g1 = new Game();
		assertEquals(0,g1.waveNumber);
	}
	
	@Test
	public void updatePointsTest() {
		Game g1 = new Game();
		g1.updateScore(2);
		assertEquals(2,g1.getPoints());
	}
	
	@Test
	public void addGoodObjTest() {
		Game g1 = new Game();
		EstuaryObject obj = g1.createObject("fish");
		g1.addObject(1, obj);
		assertTrue(Arrays.asList(g1.streams[1].goodObjects).contains(obj));
	}
	
	@Test
	public void removeGoodObjTest(){
		Game g1 = new Game();
		EstuaryObject obj = g1.createObject("fish");
		g1.addObject(1, obj);
		g1.removeObjects(obj);
		assertFalse(Arrays.asList(g1.streams[1].goodObjects).contains(obj));
	}
	
	@Test
	public void addBadObjTest() {
		Game g1 = new Game();
		EstuaryObject obj = g1.createObject("crab");
		g1.addObject(1, obj);
		assertTrue(Arrays.asList(g1.streams[1].badObjects).contains(obj));
	}
	
	@Test
	public void removeBadObjTest(){
		Game g1 = new Game();
		EstuaryObject obj = g1.createObject("crab");
		g1.addObject(1, obj);
		g1.removeObjects(obj);
		assertFalse(Arrays.asList(g1.streams[1].badObjects).contains(obj));
	}
	
	@Test
	public void updateHeathTest(){
		Game g1 = new Game();
		g1.updateHealth(2);
		assertEquals(2,g1.health);
		
	}
	
	@Test
	public void collectFishTest(){
		Game g1 = new Game();
		g1.collectFish();
		assertEquals(-1,g1.health);
		assertEquals(5,g1.getPoints());

	}
}

