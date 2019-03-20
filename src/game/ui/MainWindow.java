package game.ui;

import game.Direction;
import game.Gameboard;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainWindow extends JFrame {

    private Gameboard BOARD;
    
    public MainWindow() {
        initComponents();
    }
    
    public Gameboard getBoard() {
        return BOARD;
    }
    
    private void initComponents() {
        BOARD = new Gameboard();
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
                BOARD.moveCharacter(Direction.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                BOARD.moveCharacter(Direction.LEFT);
                break;
            case KeyEvent.VK_UP:
                BOARD.moveCharacter(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                BOARD.moveCharacter(Direction.DOWN);
                break;
        }
    }     
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }
}
