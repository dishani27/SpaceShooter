import java.awt.Graphics;
import javax.swing.ImageIcon;
import jaco.mp3.player.MP3Player;

public class Bullet extends Sprite implements GameConstants {
	boolean isVisible;
	
	public Bullet(int x,int y) {
		this.x = x;
		this.y = y;
		this.speed = BSPEED;
		this.isVisible = true;
		w = BWIDTH;
		h = BHEIGHT;
		this.image = new ImageIcon(Bullet.class.getResource(B_IMG)).getImage();
	}
	
	public void drawBullet(Graphics g){
       g.drawImage(image, x, y, w, h, null);       
       move();
	}
	
	public void move() {
		x+=speed;
		outOfScreen();
	}
	
	public void outOfScreen() {
		if(x>=GWIDTH) {
			isVisible = false;
		}
	}
	
	public void bulletSound() {
		MP3Player mp3 = new MP3Player(Bullet.class.getResource(BSOUND));
		mp3.play();
	}

}
