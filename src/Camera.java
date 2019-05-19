import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Camera  implements GameConstants {
	private static int x = 0;
	private static int speed =BGSPEED;
	private static BufferedImage bg;
	static {
		loadImage();
	}

	public static void drawBg(Graphics g) throws RasterFormatException{
		try {
			BufferedImage subImage = bg.getSubimage(x, 0 ,GWIDTH ,500);
			g.drawImage(subImage, 0, 0, GWIDTH, GHEIGHT, null);
		}
		catch (RasterFormatException e) {
			return;
		}
	}
	
	public static void left() {
		x-=speed;
	}
	
	public static void right() {
		x+=speed;
	}
	
	public static void stop() {
		speed=0;
	}
	
	public static void loadImage() {
		try {
			bg =ImageIO.read(Camera.class.getResource(BACKGROUND_IMAGE));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	

}


