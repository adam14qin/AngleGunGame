package tests;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import gunGame.Board;
import gunGame.Bullet;
import gunGame.Target;
import gunGame.TargetDirection;

public class MovementTests {

	@Test
	public void testBulletMovement() {
		Bullet bulletZero = new Bullet(20, 0);
		Bullet bulletWeird = new Bullet(20, 45);
		Bullet bulletNegative = new Bullet(20, 225);
		
		Point point1 = bulletZero.update(); 
		Point point2 = bulletWeird.update();
		Point point3 = bulletNegative.update();
		
		assertEquals(new Point((int)Board.ORIGIN.getX()+20, (int)Board.ORIGIN.getY()), point1); 
		assertEquals(new Point((int)Board.ORIGIN.getX()+14, (int)Board.ORIGIN.getY()-14), point2); 
		assertEquals(new Point((int)Board.ORIGIN.getX()-14, (int)Board.ORIGIN.getY()+14), point3);
	}
	
	@Test
	public void testTargetMovement() {
		Target target1 = new Target(0, 10, 50);
		Target target2 = new Target(225, 10, 50); 
		Target target3 = new Target(135, 10, 50); 
		
		Point point1 = target1.update(); 
		Point point2 = target2.update();
		Point point3 = target3.update();
		
		assertEquals(new Point((int)Board.ORIGIN.getX() + 40, (int)Board.ORIGIN.getY()), point1);
		assertEquals(new Point((int)Board.ORIGIN.getX() - 28, (int)Board.ORIGIN.getY() + 28), point2);
		assertEquals(new Point((int)Board.ORIGIN.getX() - 28, (int)Board.ORIGIN.getY()-28), point3);
	}
}
