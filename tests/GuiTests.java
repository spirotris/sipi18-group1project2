
import game.Gameboard;
import game.Point;
import game.ui.MainWindow;
import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.CHAR_UNDEFINED;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GuiTests {

    MainWindow ui = null;
    
    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            ui = new MainWindow();
        });
    }
    
    @Test
    public void testSwingWindowCreation() {
        assert ui.getComponentCount() > 0;
    }

    @Test
    public void testSwingRightKeyMovesCharacterRight() {
        try {
            SwingUtilities.invokeAndWait(() -> {
                Gameboard board = ui.getBoard();
                Point charPos = board.getCharacterPosition();
                ui.dispatchEvent(new KeyEvent(ui, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, CHAR_UNDEFINED));
                Point newCharPos = board.getCharacterPosition();
                assertNotSame(charPos, newCharPos);
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            fail(ex.getLocalizedMessage());
        }
    }
}
