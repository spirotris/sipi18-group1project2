package game.ui;

import game.Direction;
import game.Gameboard;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class MainWindow extends javax.swing.JFrame {

    private Gameboard board;
    private final int DELAY = 50;

    public MainWindow() {
        this(new Gameboard(20, 20));
    }
    
    public MainWindow(Gameboard board) {
        this.board = board;
        initComponents();
        board.getPoint(12, 16).setStatus(Gameboard.MONSTER);
        board.getPoint(8, 10).setStatus(Gameboard.TREASURE);
        board.getPoint(3, 2).setStatus(Gameboard.WALL);
        board.getPoint(3, 3).setStatus(Gameboard.WALL);
        board.getPoint(3, 4).setStatus(Gameboard.WALL);
        Timer timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dungeon Game");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(null);
        setMinimumSize(new java.awt.Dimension(325, 348));
        setPreferredSize(null);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                board.moveCharacter(Direction.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                board.moveCharacter(Direction.LEFT);
                break;
            case KeyEvent.VK_UP:
                board.moveCharacter(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                board.moveCharacter(Direction.DOWN);
                break;
        }
    }//GEN-LAST:event_formKeyPressed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Image floor = Toolkit.getDefaultToolkit().getImage("src/res/graphics/floor_1.png");
        Image wall = Toolkit.getDefaultToolkit().getImage("src/res/graphics/wall_right.png");
        Image character = Toolkit.getDefaultToolkit().getImage("src/res/graphics/imp_idle_anim_f0.png");
        Image ladder = Toolkit.getDefaultToolkit().getImage("src/res/graphics/floor_ladder.png");
        Image treasure = Toolkit.getDefaultToolkit().getImage("src/res/graphics/chest_empty_open_anim_f0.png");
        Image monster = Toolkit.getDefaultToolkit().getImage("src/res/graphics/goblin_idle_anim_f0.png");

        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                int x = j * 16 + 25;
                int y = i * 16+3;
                switch (board.getPoint(j, i).getStatus()) {
                    case Gameboard.WALL:
                        g2.drawImage(wall, y, x, this);
                        break;
                    case Gameboard.FLOOR:
                        g2.drawImage(floor, y, x, this);
                        break;
                    case Gameboard.CHARACTER:
                        g2.drawImage(floor, y, x, this);
                        g2.drawImage(character, y, x, this);
                        break;
                    case Gameboard.DOOR:
                        g2.drawImage(ladder, y, x, this);
                        break;
                    case Gameboard.TREASURE:
                        g2.drawImage(treasure, y, x, this);
                        break;
                    case Gameboard.MONSTER:
                        g2.drawImage(floor, y, x, this);
                        g2.drawImage(monster, y, x, this);
                        break;
                    default:
                        g2.drawImage(floor, y, x, this);
                }
            }
        }
    }
}
