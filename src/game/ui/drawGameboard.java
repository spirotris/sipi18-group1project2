package game.ui;

import game.GameEngine;
import game.Player;
import game.TileType;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class drawGameboard extends JPanel {

    private final GameEngine BOARD;
    private final int TILESIZE = 32;
    private final int DELAY = 50;
    private Image wall, character, ladder, treasure, monster, floor;

    public drawGameboard(GameEngine board) {
        this.BOARD = board;
        setUpImages();
        Timer timer = new Timer(DELAY, (final ActionEvent e) -> {
            if (!Player.isAlive) {
                restartGameDialog("Game over! You died!");
            }
            if (BOARD.isFinished()) {
                restartGameDialog("Game over! You WON!");
            }
            repaint();
        });
        timer.start();
    }

    private void restartGameDialog(String str) {
        drawGameOverScreen(getGraphics(), str);
        int response = JOptionPane.showConfirmDialog(this, "Do you want to restart?", "GAME OVER!",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            BOARD.resetGame();
        } else {
            System.exit(0);
        }
    }

    private void setUpImages() {
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
        Image closedChest = Toolkit.getDefaultToolkit()
                .getImage("src/res/graphics/chest_empty_open_anim_f0.png");
        Image openChest = Toolkit.getDefaultToolkit()
                .getImage("src/res/graphics/chest_empty_open_anim_f2.png");
        int collectedTreasures = Player.getTreasure();
        int treasuresInLevel = BOARD.getGameBoard().getNrOfTreasures();
        for (int i = 0; i < collectedTreasures; i++) {
            g2.drawImage(openChest, i * 36, 1, TILESIZE, TILESIZE, this);
        }
        for (int i = collectedTreasures; i < treasuresInLevel; i++) {
            g2.drawImage(closedChest, i * 36, 1, TILESIZE, TILESIZE, this);
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

    private void drawGameOverScreen(Graphics g, String str) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.WHITE);
        g2.drawString(str, (getWidth() / 2) - str.length(), getHeight() / 4);
    }
}
