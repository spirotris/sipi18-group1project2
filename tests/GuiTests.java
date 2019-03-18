
import game.Gameboard;
import game.Point;
import game.ui.MainWindow;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.CHAR_UNDEFINED;
import static org.junit.Assert.*;
import org.junit.Test;

public class GuiTests {

    @Test
    public void testSwingWindowCreation() {
        MainWindow ui = new MainWindow();
        assert ui.getComponentCount() > 0;
    }

    @Test
    public void testSwingKeyPressesMovesCharacter() {
        // Arrange
        Gameboard board = new Gameboard(20, 20);
        MainWindow ui = new MainWindow(board);

        // Arrange
        Point charPos = board.getCharPosition();
        KeyEvent keyEvt = new KeyEvent(ui, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, CHAR_UNDEFINED);
        ui.dispatchEvent(keyEvt);
        Point newCharPos = board.getCharPosition();

        // Assert
        assertNotSame(charPos, newCharPos);
    }
}
