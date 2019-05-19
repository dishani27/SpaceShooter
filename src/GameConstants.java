
public interface GameConstants {

	public static final int GWIDTH = 1370;
	int GHEIGHT = 720;
	String TITLE = "Space Shooter";
	int DELAY = 15;
	String BACKGROUND_IMAGE = "bgg.jpg";
	int BGSPEED = 4;
	String BGSOUND = "bg.mp3";
    
	//player
	int PPOS = 50;
    int FLOOR = GHEIGHT - 650;
	int PWIDTH = 120;
	int PHEIGHT = 150;
	int SPEED= 0;
	String PLAYER_IMG = "aaa.gif";
	String Player_SOUND = "player.mp3";
	String Player_WIN = "pwin.mp3";
	
	//enemy
	int EWIDTH = 70;
	int EHEIGHT = 70;
	int ESPEED = 10;
	String ENEMY_IMG = "bbb.gif";
	int MAX_ENEMY = 50;
	String ESOUND ="enemy.mp3";
	
	//monster
	int MONSTER_POSX = 1100;
	int MONSTER_POSY = 100;
	int MWIDTH = 200;
	int MHEIGHT = 220;
	int MSPEEDY = 3;
	int MSPEEDX = 2;
	String Monster_IMG = "mmm.gif";
	String MSOUND ="monst.mp3";
	
	//bullet
	int BWIDTH = 20;
	int BHEIGHT = 10;
	int BSPEED = 10;
	String B_IMG = "bullet.png";
	String BSOUND = "bullet.mp3";
}