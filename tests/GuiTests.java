
import game.GameEngine;
import game.Point;
import game.ui.MainWindow;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
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
    public void testSwingRightKeyMovesCharacterRight() {
        try {
            SwingUtilities.invokeAndWait(() -> {
                MainWindow ui = new MainWindow();
                GameEngine board = ui.getBoard();
                // TODO: 2019-03-27 Change getPlayer to getCharacterPosition and in that method loop through the boardgrid to find the position of character and return it
                Point charPos = board.getCharacterPosition();
                ui.requestFocus();
                KeyEvent rArrow = new KeyEvent(ui.getComponent(0), 0, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED); 
                ui.getComponent(0).getComponentAt(0, 0).dispatchEvent(rArrow);
                Point newCharPos = board.getCharacterPosition();
                assertNotSame(charPos, newCharPos);
            });
        } catch (InvocationTargetException | InterruptedException ex) {
            System.out.println(ex.getCause().getMessage());
        }
    }
}
