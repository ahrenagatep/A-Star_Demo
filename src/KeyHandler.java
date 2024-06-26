import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {
    Demo dp;
    public KeyHandler(Demo dp){
        this.dp = dp;
    }
    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_ENTER){
            dp.autoSearch();
        } else if (code == KeyEvent.VK_E) {
            dp.manualSearch();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}
