package rhythm_17;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Note extends Thread implements Runnable {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y; // = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	private boolean proceeded = true;

	public String getNoteType() {
		return noteType;
	}

	public boolean isProceeded() {
		return proceeded;
	}

	public void close() {
		proceeded = false;
	}

	public Note(String noteType) {
		if (noteType.equals("S")) {
			x = 278;
		} else if (noteType.equals("D")) {
			x = 382;
		} else if (noteType.equals("F")) {
			x = 486;
		} else if (noteType.equals("Space")) {
			x = 590;
		} else if (noteType.equals("J")) {
			x = 694;
		} else if (noteType.equals("K")) {
			x = 798;
		} else if (noteType.equals("L")) {
			x = 902;
		}
		this.noteType = noteType;
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(noteBasicImage, x, y, null);
	}

	public void drop() {
		y += Main.NOTE_SPEED;
		if (y > 660) {
			System.out.println("X");
			close();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				drop();
				if (proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				} else {
					interrupt();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String judge() {
		if (y >= 650) {
			System.out.println("50");
			close();
			return "50";
		} else if (y >= 630) {
			System.out.println("100");
			close();
			return "100";
		} else if (y >= 605) {
			System.out.println("200");
			close();
			return "200";
		} else if (y >= 590) {
			System.out.println("300");
			close();
			return "300";
		} else if (y >= 570) {
			System.out.println("300!");
			close();
			return "300!";
		} else if (y >= 555) {
			System.out.println("300");
			close();
			return "300";
		} else if (y >= 530) {
			System.out.println("200");
			close();
			return "200";
		} else if (y >= 510) {
			System.out.println("100");
			close();
			return "100";
		} else if (y >= 500) {
			System.out.println("50");
			close();
			return "50";
		}
		return "None";
	}

	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
