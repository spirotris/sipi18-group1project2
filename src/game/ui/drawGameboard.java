package game.ui;

import game.Gameboard;
import game.TileType;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class drawGameboard extends JPanel {

    private final Gameboard BOARD;
    private final int TILESIZE = 32;
    private final int DELAY = 50;
    private Image floorArray[], wall, character, ladder, treasure, monster, floor;

    public drawGameboard(Gameboard board) {
        this.BOARD = board;
        setUpImages();
        Timer timer = new Timer(DELAY, (final ActionEvent e) -> {
            if (!BOARD.getIsAlive()) {
                JOptionPane.showInputDialog(this, "LOL U DEDD!!!!!");
            }
            if (BOARD.isFinished()) {
                JOptionPane.showInputDialog(this, "LOL U ZE WINNARR!!!!!!!");
            }
            repaint();
        });
        timer.start();
    }

    private void setUpImages() {
        floorArray = new Image[8];
        for (int i = 0; i < floorArray.length; i++) {
            floorArray[i] = Toolkit.getDefaultToolkit()
                    .getImage("src/res/graphics/floor_" + (i + 1) + ".png");
        }
        floor = Toolkit.getDefaultToolkit()
                .getImage("src/res/graphics/floor_1.png");
        wall = Toolkit.getDefaultToolkit()
                .getImage("src/res/graphics/wall_right.png");
        character = Toolkit.getDefaultToolkit()
                .getImage("src/res/graphics/imp_idle_anim_f0.png");
        ladder = Toolkit.getDefaultToolkit()
                .getImage("src/res/graphics/floor_ladder.png");
        treasure = Toolkit.getDefaultToolkit()
                .getImage("src/res/graphics/chest_empty_open_anim_f0.png");
        monster = Toolkit.getDefaultToolkit()
                .getImage("src/res/graphics/goblin_idle_anim_f0.png");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.gray);
        g.fillRect(0, 0, 700, 50);

        drawHearts(g2);

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                drawTile(BOARD.getPoint(j, i).getTileType(), g2, j, i);
            }
        }
    }

    private void drawHearts(Graphics2D g2) {
        Image heart = Toolkit.getDefaultToolkit()
                .getImage("src/res/graphics/chest_empty_open_anim_f0.png");
        for (int i = 0; i < 10; i++) {
            g2.drawImage(heart, i * 36, 1, TILESIZE, TILESIZE, this);
        }
    }

    private void drawTile(TileType type, Graphics2D g2, int x, int y) {
        // x starts 35 px from top to make space for treasure images
        x = x * TILESIZE + 35;
        y = y * TILESIZE;

        switch (type) {
            case WALL:
                g2.drawImage(wall, y, x, TILESIZE, TILESIZE, this);
                break;
            case FLOOR:
                g2.drawImage(floor, y, x, TILESIZE, TILESIZE, this);
                break;
            case CHARACTER:
                g2.drawImage(floor, y, x, TILESIZE, TILESIZE, this);
                g2.drawImage(character, y, x, TILESIZE, TILESIZE, this);
                break;
            case DOOR:
                g2.drawImage(ladder, y, x, TILESIZE, TILESIZE, this);
                break;
            case TREASURE:
                g2.drawImage(floor, y, x, TILESIZE, TILESIZE, this);
                g2.drawImage(treasure, y, x, TILESIZE, TILESIZE, this);
                break;
            case MONSTER:
                g2.drawImage(floor, y, x, TILESIZE, TILESIZE, this);
                g2.drawImage(monster, y, x, TILESIZE, TILESIZE, this);
                break;
            default:
                g2.drawImage(floor, y, x, this);
        }
    }
}
