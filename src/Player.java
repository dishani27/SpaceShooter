import java.awt.Graphics;
import javax.swing.ImageIcon;

import jaco.mp3.player.MP3Player;

public class Player extends Sprite implements GameConstants{
	
	private int speedr=0;

	public Player() {
		x = PPOS;
		y = FLOOR;
		w = PWIDTH;
		h = PHEIGHT;
		speed = SPEED;
		image = new ImageIcon(Player.class.getResource(PLAYER_IMG)).getImage();
	}
	
	public void drawPlayer(Graphics g) {
		g.drawImage(image, x ,y, w ,h, null);
		move();
		right();
	}

	public void right() {
		x+= speedr;
	}
	
	public void move() {
		y+=speed;
        if(y> GHEIGHT-160) {
        try {
			throw new IllegalArgumentException();

		} catch (IllegalArgumentException e) {
		  y=GHEIGHT-160;
		  }	  
	   }
        else 
         if(y<-5) {
            try {
        			throw new IllegalArgumentException();

        		} catch (IllegalArgumentException e) {
        		  y=0;
        		}	  
        }	
}
	
	public void setspeed(int speed) {
		this.speed = speed;
	}
	
	public int getspeed() {
		return speed;
	}
	
	public void setspeedr(int speedr) {
		this.speedr = speedr;
	}
	
	public int getspeedr() {
		return speedr;
	}
	
	public void playerSound() {
		MP3Player mp3 = new MP3Player(Player.class.getResource(Player_SOUND));
		mp3.play();
	}
	
	public void winSound() {
		MP3Player mp3 = new MP3Player(Player.class.getResource(Player_WIN));
		mp3.play();	
	}
	

}
