package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;

import objects.Crab;
import objects.Fish;
import swampgod.Game;
import swampgod.Main.GameState;

public class GameTest implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 11052015;

	@Test
	public void gameStateTest() {
		Game g1 = new Game();
		assertEquals(GameState.INITIALIZE,g1.getGameState());
	}
	
	@Test
	public void healthTest() {
		Game g5 = new Game();
		g5.initialize();
		assertEquals(50,g5.getHealth());
		g5.updateHealth(30);
		assertEquals(80, g5.getHealth());
	}
	
	@Test
	public void plantArrTest() {
		Game g1 = new Game();
		assertEquals(null,g1.getPlants());
	}
	
	@Test
	public void pointTest() {
		Game g1 = new Game();
		assertEquals(0,g1.getPoints());
	}
	
	@Test
	public void waveTest() {
		Game g1 = new Game();
		assertEquals(0,g1.getWaveNumber());
	}
	
	@Test
	public void updatePointsTest() {
		Game g1 = new Game();
		g1.updateScore(2);
		assertEquals(2,g1.getPoints());
	}
	
	@Test
	public void updateHeathTest(){
		Game g1 = new Game();
		g1.initialize();
		g1.updateHealth(2);
		assertEquals(52,g1.getHealth());
		
	}
	
	@Test
	public void levelTest(){
		Game g = new Game();
		g.initialize();
		assertEquals(140, g.getLevel().getGoodObjectReleaseFrequency(1));
		assertEquals(75, g.getLevel().getBadObjectReleaseFrequency(1));
		assertEquals(34, g.getLevel().getPlantEatFrequency(2));
	}
	
	@Test
	public void collectFishTest(){
		Game g1 = new Game();
		g1.initialize();
		g1.collectFish();
		assertEquals(50,g1.getHealth());
		assertEquals(25,g1.getPoints());

	}
}

