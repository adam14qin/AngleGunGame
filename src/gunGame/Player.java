package gunGame;

public abstract class Player {
	private int guess; 
	private Bullet bullet; 
	
	public Player() {
		
	}
	
	public abstract int guess();
	
	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}
	
	public Bullet getBullet() {
		return bullet;
	}
}
