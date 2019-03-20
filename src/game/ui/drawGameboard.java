package game.ui;

import game.Gameboard;
import game.TileType;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

public class drawGameboard extends JPanel {

    private final Gameboard BOARD;
    private final int TILESIZE = 32;
    private final int DELAY = 50;
    
    public drawGameboard(Gameboard board) {
        this.BOARD = board;
        Timer timer = new Timer(DELAY, (final ActionEvent e) -> {
            repaint();
        });
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.gray);
        g.fillRect(0, 0, 700, 50);
        //Image background = Toolkit.getDefaultToolkit()
        //        .getImage("src/res/graphics/bg.png");
        //g2.drawImage(background, 0, 0, this);
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
        x = x * 32 + 35;
        y = y * 32;

        Image floor = Toolkit.getDefaultToolkit()
        .getImage("src/res/graphics/floor_1.png");
        Image wall = Toolkit.getDefaultToolkit()
                .getImage("src/res/graphics/wall_right.png");
        Image character = Toolkit.getDefaultToolkit()
                .getImage("src/res/graphics/imp_idle_anim_f0.png");
        Image ladder = Toolkit.getDefaultToolkit()
                .getImage("src/res/graphics/floor_ladder.png");
        Image treasure = Toolkit.getDefaultToolkit()
                .getImage("src/res/graphics/chest_empty_open_anim_f0.png");
        Image monster = Toolkit.getDefaultToolkit()
                .getImage("src/res/graphics/goblin_idle_anim_f0.png");

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
            case LASER:
                g2.drawImage(floor, y, x, TILESIZE, TILESIZE, this);
                g2.drawImage(monster, y, x, TILESIZE, TILESIZE, this);
                break;
            default:
                g2.drawImage(floor, y, x, this);
        }
    }
}
