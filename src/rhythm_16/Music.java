package rhythm_16;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {

	private Player player;
	private boolean isLoop;
	private InputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {
		super();
		this.isLoop = isLoop;
		try {
			fis = Main.class.getResourceAsStream("../music/" + name);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}

	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = Main.class.getResourceAsStream("/music/" + getName());
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
