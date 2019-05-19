import javax.swing.JFrame;

public class GameFrame extends JFrame implements GameConstants {
	
	public GameFrame() {
		setSize(GWIDTH, GHEIGHT);
		setTitle(TITLE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		
		Board board = new Board();
		add(board);
		setVisible(true);
	}
	

	public static void main(String[] args) {
       @SuppressWarnings("unused")
	GameFrame gFrame = new GameFrame();
	}

}
