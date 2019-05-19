import java.awt.Graphics;

import javax.swing.ImageIcon;

import jaco.mp3.player.MP3Player;

public class Enemy extends Sprite implements GameConstants{

	boolean isDead;
	
	public Enemy(int x, int y ) {
		w = EWIDTH;
		h = EHEIGHT;
		this.x = x;
		this.y = y;
		speed = ESPEED;
		this.image = new ImageIcon(Enemy.class.getResource(ENEMY_IMG)).getImage();
	}
	
	public void drawEnemy(Graphics g) {
		g.drawImage(image, x,y,w,h,null);
		move();
	}
	
	public void move() {
		x = x-speed;
	}
	
	public void enemySound() {
		MP3Player mp3 = new MP3Player(Enemy.class.getResource(ESOUND));
		mp3.play();
	}
	
}
