
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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GuiTests {


    /*@Before
    public void setUp() throws Exception {

    }*/
    @Test
    public void testSwingWindowCreation() {
        MainWindow ui = new MainWindow();
        assert ui.getComponentCount() > 0;
    }

    @Test
    public void testMoveCharacter() {
        Gameboard board = new Gameboard();
        Point charPos = board.getCharacterPosition();
        board.moveCharacter(Direction.RIGHT);
        Point newCharPos = board.getCharacterPosition();
        assertNotEquals(charPos, newCharPos);
    }

    @Test
    public void testSwingRightKeyMovesCharacterRight() throws AWTException {
        try {
            SwingUtilities.invokeAndWait(() -> {
                MainWindow ui = new MainWindow();
                Gameboard board = ui.getBoard();
                Point charPos = board.getCharacterPosition();
                ui.requestFocus();
                ui.getComponent(0).getComponentAt(0, 0);
                System.out.println(ui.getComponent(0).getComponentAt(0, 0));

                ui.dispatchEvent(new KeyEvent(ui.getComponent(0), 0, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
                Point newCharPos = board.getCharacterPosition();
                assertNotSame(charPos, newCharPos);
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            fail(ex.getCause().getMessage());
        }
    }
}
