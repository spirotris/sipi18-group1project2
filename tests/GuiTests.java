
import game.Gameboard;
import game.Point;
import game.ui.MainWindow;
import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.CHAR_UNDEFINED;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Test;

public class GuiTests {

    @Test
    public void testSwingWindowCreation() {
        MainWindow ui = new MainWindow();
        assert ui.getComponentCount() > 0;
    }

    @Test
    public void testSwingRightKeyMovesCharacterRight() {
        // Arrange
        MainWindow ui = new MainWindow();
        Gameboard board = ui.getBoard();

        // Arrange
        Point charPos = board.getCharacterPosition();
        System.out.println(charPos.getX());
        KeyEvent keyEvt = new KeyEvent(ui, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, CHAR_UNDEFINED);
        ui.getComponent(0).dispatchEvent(keyEvt);
        
        Point newCharPos = board.getCharacterPosition();
        System.out.println(newCharPos.getX());

        // Assert
        assertNotSame(charPos, newCharPos);
    }
}
