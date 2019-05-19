import java.awt.Graphics;
import javax.swing.ImageIcon;
import jaco.mp3.player.MP3Player;

public class Monster extends Sprite implements GameConstants{

	boolean isDead;
	
	public Monster() {
	    x = MONSTER_POSX;
	    this.y = MONSTER_POSY;
		w = MWIDTH;
		h = MHEIGHT;
		speed = MSPEEDY;
		this.image = new ImageIcon(Monster.class.getResource(Monster_IMG)).getImage();
	}
	
	public void drawMonster(Graphics g) {
			g.drawImage(image, x,y,w,h,null);
			move();	
	}
	
	public void move() {
		y+=speed;
		changeDirection();
		x-=MSPEEDX;
	}
	
	public void MonsterSound() {
		MP3Player mp3 = new MP3Player(Monster.class.getResource(ESOUND));
		mp3.play();
	}
	
	public void MonsterDieSound() {
		MP3Player mp3 = new MP3Player(Monster.class.getResource(MSOUND));
		mp3.play();
	}
	
	public void changeDirection() {
		if(y>=(GHEIGHT-300)) {
           speed = speed * -1;
		}
		else
		if(y<=50) {	
			speed = speed * -1;
		}
	}
	
}
