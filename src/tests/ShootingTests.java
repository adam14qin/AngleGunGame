package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.BeforeClass;
import org.junit.Test;

import gunGame.Board;
import gunGame.Bullet;
import gunGame.Target;
import gunGame.TargetDirection;

public class ShootingTests {
	
	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		//Initialize the board
		board.initialize();
	}
	@Test
	public void testBulletCollision() {
		board.getChallengePlayer().setBullet(new Bullet(10, 0));
		board.addTarget(new Target(TargetDirection.EAST, 0, 50));
		
		for(int i=0; i < 5; i++)
		{
		board.update();
		System.out.println(board.getChallengePlayer().getBullet().getPoint());
		Target target = board.checkCollision();
		assertNull(target);
		}
		
		board.update();
		Target target = board.checkCollision();
		assertEquals(board.getTargets().get(0), target); 
	}
	
	@Test
	public void testBulletMiss() {
		board.getChallengePlayer().setBullet(new Bullet(10, 0));
		board.addTarget(new Target(TargetDirection.WEST, 0, 50));
		
		for(int i=0; i < 4; i++)
		{
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
