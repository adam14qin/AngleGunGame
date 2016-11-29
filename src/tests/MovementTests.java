package tests;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.BeforeClass;
import org.junit.Test;

import gunGame.Board;
import gunGame.Bullet;
import gunGame.Target;
import gunGame.TargetDirection;

public class MovementTests {
	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		//Initialize the board
		board.initialize();
	}
	@Test
	public void testBulletMovement() {
		Bullet bulletZero = new Bullet(20, 0);
		Bullet bulletWeird = new Bullet(20, 45);
		Bullet bulletNegative = new Bullet(20, 225);
		
		Point point1 = bulletZero.update(); 
		Point point2 = bulletWeird.update();
		Point point3 = bulletNegative.update();
		
		assertEquals(new Point((int)board.ORIGIN.getX()+20, (int)board.ORIGIN.getY()), point1); 
		assertEquals(new Point((int)board.ORIGIN.getX()+14, (int)board.ORIGIN.getY()-14), point2); 
		assertEquals(new Point((int)board.ORIGIN.getX()-14, (int)board.ORIGIN.getY()+14), point3);
	}
	
	@Test
	public void testTargetMovement() {
		Target target1 = new Target(TargetDirection.EAST, 10, 50);
		Target target2 = new Target(TargetDirection.SOUTH_WEST, 10, 50); 
		Target target3 = new Target(TargetDirection.NORTH_WEST, 10, 50); 
		
		Point point1 = target1.update(); 
		Point point2 = target2.update();
		Point point3 = target3.update();
		
		assertEquals(point1 , new Point((int)board.ORIGIN.getX() + 40, (int)board.ORIGIN.getY()));
		assertEquals(point2 , new Point((int)board.ORIGIN.getX() - 43, (int)board.ORIGIN.getY() + 43));
		assertEquals(point3 , new Point((int)board.ORIGIN.getX() - 43, (int)board.ORIGIN.getY()-43));
	}
	
	
}
