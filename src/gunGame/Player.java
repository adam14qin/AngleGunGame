package gunGame;

public abstract class Player {
	private Bullet bullet; 

	public abstract int guess();
	
	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}
	
	public Bullet getBullet() {
		return bullet;
	}

	public void removeBullet() {
		this.bullet = null;
	}
}
