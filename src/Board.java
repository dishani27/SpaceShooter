import javax.swing.Timer;
import jaco.mp3.player.MP3Player;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Board extends JPanel implements GameConstants{
  
	private Timer timer;
	private Player player;
	private Monster monster;
	private Enemy enemies[] = new Enemy[MAX_ENEMY];
	private ArrayList<Bullet> bullets = new ArrayList<>();
	MP3Player mp3 = new MP3Player(Board.class.getResource(BGSOUND));

	public Board() {
		setSize(GWIDTH, GHEIGHT);
		player = new Player();
		setFocusable(true);
		gameLoop();
		loadEnemies();
	    monster = new Monster();
		bindEvents();
		playBg();
	}
	
	private void gameLoop() {
		timer = new Timer(DELAY, (e)->{
			repaint();		
		});
		timer.start();

	}
	
	@Override
	public void paintComponent(Graphics g){
		
		drawBackground(g);
		Camera.right();
		player.drawPlayer(g);
		drawEnemies(g);
		drawMonster(g);
		checkCollision(g);
		drawBullets(g);
		checkBulletEnemyCollision(g);
		checkBulletMonsterCollision(g);
		checkPlayerMonsterCollision(g);
        Win(g);
	}
	
	private void drawBackground(Graphics g) {
		Camera.drawBg(g);		
	}
	
	private void loadEnemies() {
		Random random = new Random();
		
		int x = GWIDTH/2 +150;
		for(int i =0; i<enemies.length; i++) {
			int y = random.nextInt(600);
			enemies[i] = new Enemy(x,y);
			x = x+300;
		}
	}
	
	private void drawEnemies(Graphics g) {
		for(Enemy enemy: enemies) {
			if(!enemy.isDead) {
				enemy.drawEnemy(g);	
			}
		}
	}
	
	private void bindEvents() {
		this.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent e) {
				player.setspeed(0);
				player.setspeedr(0);
			}
			
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				
				if(keyCode==KeyEvent.VK_UP) {
					player.setspeed(-10);
				}
				else
				if(keyCode==KeyEvent.VK_RIGHT) {
					player.setspeedr(5);
				}
				else
				if(keyCode==KeyEvent.VK_LEFT) {
					player.setspeedr(-5);
				}
				else	
				if(keyCode==KeyEvent.VK_DOWN) {
					player.setspeed(10);
				}	
				else
				if(keyCode==KeyEvent.VK_SPACE) {
					int bulletX = player.getX() + (player.getW()/2);
					int bulletY = player.getY() + (player.getH()/2);
					Bullet bullet = new Bullet(bulletX, bulletY);
					bullets.add(bullet);
					bullet.bulletSound();
				}	
			}
		});
	}
	
	private boolean isCollide(Player player, Enemy enemy) {
		int maxW = Math.max(player.w, enemy.w);
		int maxH = Math.max(player.h, enemy.h);
		int xDist = Math.abs(player.x - enemy.x);
		int yDist = Math.abs(player.y - enemy.y);
		return xDist<= maxW -20 && yDist<= maxH -35;
	}
	
	private void checkCollision(Graphics g) {
		for(Enemy enemy: enemies) {
			if (isCollide(player, enemy)) {
				gameOver(g);
				return;
			}
		}
	}
	
	private void gameOver(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("Calisto MT", Font.BOLD, 40));
		g.drawString("GAME OVER", GWIDTH/2 - 120, GHEIGHT/2);
		timer.stop();
		player.playerSound();
		mp3.stop();
		
	}
	
	private void drawBullets(Graphics g) {
		for(Bullet bullet: bullets) {
			if(bullet.isVisible) {
				bullet.drawBullet(g);
			}
		}
	}
	
	private boolean isCollideBulletEnemy(Bullet bullet, Enemy enemy) {
		int maxW = Math.max(bullet.getW(), enemy.getW());
		int maxH = Math.max(bullet.getH(), enemy.getH());
		int yDist = Math.abs(bullet.getY()-enemy.getY());
		int xDist = Math.abs(bullet.getX() - enemy.getX());
		return xDist <= maxW-50 && yDist <= maxH-10;
	}
	
	private void checkBulletEnemyCollision(Graphics g) {
		for(Bullet bullet: bullets) {
			for(Enemy enemy: enemies) {
				if(isCollideBulletEnemy(bullet, enemy)) {
					enemy.isDead =true;
					enemy.setX(-200);
					enemy.setY(-200);
					bullet.setX(-100);
					bullet.setY(-100);
					bullet.isVisible = false;
					enemy.enemySound();
				}
			}
		}
	}
	
	private void playBg() {
		mp3.play();
	}
	
	private void drawMonster(Graphics g) {
		Enemy last = enemies[enemies.length-1];
		int lastenemy = last.getX();
		if (!monster.isDead) {
			if(lastenemy<-100) {
				monster.drawMonster(g);
			}
		}
	}
	
	private boolean isCollideBulletMonster(Bullet bullet, Monster monster) {
		int maxW = Math.max(bullet.getW(), monster.getW());
		int maxH = Math.max(bullet.getH(), monster.getH());
		int yDist = Math.abs(bullet.getY()-monster.getY());
		int xDist = Math.abs(bullet.getX() - monster.getX());
		return xDist <= maxW-150 && yDist <= maxH-150;
		
	}
	
    int count = 0;
	private void checkBulletMonsterCollision(Graphics g) {
		for(Bullet bullet: bullets) {
				if(isCollideBulletMonster(bullet, monster)) {
					bullet.isVisible = false;
					bullet.setX(-100);
					bullet.setY(-100);
					count++;
//					System.out.println(count);
					monster.MonsterSound();
					if(count==10) {
						monster.isDead = true;
						monster.setX(-500);
						monster.setY(-500);
						monster.MonsterDieSound();
						Camera.stop();
//						Win(g);
//						mp3.stop();
					}
				}
		}
	}
	

	private void Win(Graphics g) {
		if(player.getX()>GWIDTH) {
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Calisto MT", Font.BOLD, 40));
			g.drawString("YOU WIN", GWIDTH/2 - 120, GHEIGHT/2);
			player.winSound();
			mp3.stop();
			timer.stop();
		}
		
	}
	
	private boolean isCollidePlayerMonster(Player player, Monster monster) {
		int maxW = Math.max(player.getW(), monster.getW());
		int maxH = Math.max(player.getH(), monster.getH());
		int yDist = Math.abs(player.getY()-monster.getY());
		int xDist = Math.abs(player.getX() - monster.getX());
		return xDist <= maxW-150 && yDist <= maxH-100;
	}
	
	private void checkPlayerMonsterCollision(Graphics g) {
				if(isCollidePlayerMonster(player, monster)) {
					player.setX(-700);
					player.setY(-700);
					gameOver(g);
				}
	}
	
	
	
	
	      
	
	
	 
	

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
}
