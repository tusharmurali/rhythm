package rhythm_12;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		if (Rhythm.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			Rhythm.game.pressS();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			Rhythm.game.pressD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			Rhythm.game.pressF();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Rhythm.game.pressSpace();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			Rhythm.game.pressJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			Rhythm.game.pressK();
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			Rhythm.game.pressL();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (Rhythm.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			Rhythm.game.releaseS();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			Rhythm.game.releaseD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			Rhythm.game.releaseF();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Rhythm.game.releaseSpace();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			Rhythm.game.releaseJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			Rhythm.game.releaseK();
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			Rhythm.game.releaseL();
		}
	}
	
	
	
}
