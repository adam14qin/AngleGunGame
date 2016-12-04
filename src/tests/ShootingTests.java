package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import gunGame.Board;
import gunGame.Bullet;
import gunGame.Target;

public class ShootingTests {
	
	private static Board board;
	
	@Before
	public void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
	}
	
	@Test
	public void testBulletCollision() {
		Target testTarget = new Target(0, 0, 50);
		
		board.getActivePlayer().setBullet(new Bullet(10, 0));
		board.clearTargets();
		board.addTarget(testTarget);
		
		for (int i = 0; i < 4; i++) {
			board.update();
			Target target = board.checkCollision();
			assertNull(target);
		}
		
		board.update();
		Target target = board.checkCollision();
		assertEquals(testTarget, target); 
	}
	
	@Test
	public void testBulletMiss() {
		board.getActivePlayer().setBullet(new Bullet(10, 0));
		board.clearTargets();
		board.addTarget(new Target(180, 0, 50));
		
		for (int i = 0; i < 4; i++) {
			board.update();
			Target target = board.checkCollision();
			assertNull(target);
		}
		
		board.update();
		Target target = board.checkCollision();
		assertNull(target);
	}
	
	@Test 
	public void testScoreIncrement() {
		int score = board.getScore();
		testBulletCollision();
		assertTrue(board.getScore() == score+1);
		
		int score2 = board.getScore();
		testBulletMiss();
		assertEquals(board.getScore(), score2);
	}
}
