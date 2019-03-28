package game.ui;

import game.*;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainWindow extends JFrame {

    private GameEngine BOARD;
    private Player player;
    
    public MainWindow() {
        initComponents();
    }
    
    public GameEngine getBoard() {
        return BOARD;
    }
    
    private void initComponents() {
        BOARD = new GameEngine();
        player = new Player();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dungeon Game");
        setMinimumSize(new java.awt.Dimension(640, 715));
        setResizable(false);
        
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arrowKeyPressed(evt);
            }
        });
        
        JPanel panel = new drawGameboard(BOARD);
        this.add(panel);
        pack();
    }
    
      private void arrowKeyPressed(java.awt.event.KeyEvent evt) {                                
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                Mover.move(Direction.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                Mover.move(Direction.LEFT);
                break;
            case KeyEvent.VK_UP:
                Mover.move(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                Mover.move(Direction.DOWN);
                break;
        }
    }     
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }
}
