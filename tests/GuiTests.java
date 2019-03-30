
import game.Direction;
import game.Gameboard;
import game.Point;
import game.ui.MainWindow;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import static org.junit.Assert.*;
import org.junit.Test;

public class GuiTests {

    @Test
    public void testSwingWindowCreation() {
        MainWindow ui = new MainWindow();
        assert ui.getComponentCount() > 0;
    }

    @Test
    public void testSwingRightKeyMovesCharacterRight() throws AWTException {
                MainWindow ui = new MainWindow();
                Gameboard board = ui.getBoard();
                Robot robot = new Robot();
                ui.setVisible(true);
                robot.delay(300);
                int xPosBefore = board.getPlayer().getX();
                robot.delay(50);
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.delay(50);
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.delay(500);
                assertNotSame(xPosBefore, board.getPlayer().getX());
    }
}
