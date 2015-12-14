package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import objects.EstuaryObject;
import objects.Fish;
import swampgod.Estuary;


public class EstuaryTest {
	@Test
	public void initializationTest() {
		Estuary e = new Estuary();
		assertEquals(new ArrayList<EstuaryObject>(), e.getObjectsToDraw());
		assertEquals(new Rectangle(0, 400, 960, 240), e.getBounds());
	}
	
	@Test
	public void bounds() {
		Estuary e = new Estuary();
		Rectangle rect = new Rectangle(100,100,0,0);
		e.setBounds(rect);
		assertEquals(rect, e.getBounds());
		rect.grow(100, 100);
		assertEquals(rect, e.getBounds());
	}
	
	@Test
	public void addFish() {
		Estuary e = new Estuary();
		e.addFish();
		assertEquals(1, e.getObjectsToDraw().size());
		e.addFish();
		assertEquals(2, e.getObjectsToDraw().size());
	}
	
	@Test
	public void moveFish() {
		Estuary e = new Estuary();
		e.addFish();
		Point p = e.getObjectsToDraw().get(0).getPosition();
		e.moveFish();
		assertNotEquals(p, e.getObjectsToDraw().get(0).getPosition());
	}
	
	@Test
	public void removeFish() {
		Estuary e = new Estuary();
		e.addFish();
		e.removeFish(1);
		assertEquals(1, e.getObjectsToDraw().size());
		e.addFish();
		e.addFish();
		assertEquals(3, e.getObjectsToDraw().size());
		e.addFish();
		e.removeFish(3);
		assertEquals(3, e.getObjectsToDraw().size());
	}
}
