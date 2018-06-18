package rhythm_17;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Game extends Thread {

	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgmentLineImage = new ImageIcon(Main.class.getResource("../images/judgmentLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpaceImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	private Image scorePanel = new ImageIcon(Main.class.getResource("../images/scorePanel.png")).getImage();
	private Image flareImage;
	private Image judgeImage;
	private Image keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpaceImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();

	private String userName;
	private String titleName;
	private String musicTitle;
	private Music gameMusic;

	List<Note> noteList = new ArrayList<Note>();

	private int score = 0;

	Beat[] beats = null;
	
	public Game(String titleName, String musicTitle) {
		super();
		this.titleName = titleName;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 278, 30, null);
		g.drawImage(noteRouteDImage, 382, 30, null);
		g.drawImage(noteRouteFImage, 486, 30, null);
		g.drawImage(noteRouteSpaceImage, 590, 30, null);
		g.drawImage(noteRouteJImage, 694, 30, null);
		g.drawImage(noteRouteKImage, 798, 30, null);
		g.drawImage(noteRouteLImage, 902, 30, null);
		g.drawImage(noteRouteLineImage, 274, 30, null);
		g.drawImage(noteRouteLineImage, 378, 30, null);
		g.drawImage(noteRouteLineImage, 482, 30, null);
		g.drawImage(noteRouteLineImage, 586, 30, null);
		g.drawImage(noteRouteLineImage, 690, 30, null);
		g.drawImage(noteRouteLineImage, 794, 30, null);
		g.drawImage(noteRouteLineImage, 898, 30, null);
		g.drawImage(noteRouteLineImage, 1002, 30, null);
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgmentLineImage, 0, 580, null);

		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (note.getY() > 660) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeX.png")).getImage();
			}
			if (!note.isProceeded()) {
				noteList.remove(i);
				i++;
			} else {
				note.screenDraw(g);
			}
		}

		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Impact", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		g.setFont(new Font("Impact", Font.PLAIN, 26));
		g.setColor(Color.WHITE);
		g.drawString("S", 322, 610);
		g.drawString("D", 426, 610);
		g.drawString("F", 530, 610);
		g.drawString("Space", 607, 610);
		g.drawString("J", 738, 610);
		g.drawString("K", 841, 610);
		g.drawString("L", 945, 610);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Impact", Font.BOLD, 45));
		g.drawString(String.format("%06d", score), 566, 710);

		g.drawImage(scorePanel, 1024, 50, null);
		g.drawImage(judgeImage, 1041, 61, null);
		g.drawImage(flareImage, 320, 540, null);

		g.drawImage(keyPadSImage, 278, 580, null);
		g.drawImage(keyPadDImage, 382, 580, null);
		g.drawImage(keyPadFImage, 486, 580, null);
		g.drawImage(keyPadSpaceImage, 590, 580, null);
		g.drawImage(keyPadJImage, 694, 580, null);
		g.drawImage(keyPadKImage, 798, 580, null);
		g.drawImage(keyPadLImage, 902, 580, null);
	}

	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drum.mp3", false).start();
	}

	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drum.mp3", false).start();
	}

	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drum.mp3", false).start();
	}

	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressSpace() {
		judge("Space");
		noteRouteSpaceImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSpaceImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drum.mp3", false).start();
	}

	public void releaseSpace() {
		noteRouteSpaceImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSpaceImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drum.mp3", false).start();
	}

	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drum.mp3", false).start();
	}

	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drum.mp3", false).start();
	}

	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	@Override
	public void run() {
		dropNotes(this.titleName);
	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}

	public void dropNotes(String titleName) {

		if (titleName.equals("Hinkik - Time Leaper")) {
			int startTime = Main.REACH_TIME;
			int gap = 125;
			beats = new Beat[] { 
					new Beat(startTime + gap * 10, "F"), 
					new Beat(startTime + gap * 16, "L"),
					new Beat(startTime + gap * 22, "F"),
					new Beat(startTime + gap * 24, "D"),
					new Beat(startTime + gap * 26, "S"),
					new Beat(startTime + gap * 32, "J"), 
					new Beat(startTime + gap * 38, "S"),
					new Beat(startTime + gap * 44, "J"),
					new Beat(startTime + gap * 46, "K"),
					new Beat(startTime + gap * 48, "L"),
					new Beat(startTime + gap * 54, "Space"),
					new Beat(startTime + gap * 62, "Space"),
					new Beat(startTime + gap * 64, "Space"),
					new Beat(startTime + gap * 74, "Space"),
					new Beat(startTime + gap * 76, "Space"),
					new Beat(startTime + gap * 86, "Space"),
					new Beat(startTime + gap * 88, "Space"),
					new Beat(startTime + gap * 98, "F"),
					new Beat(startTime + gap * 104, "J"),
					new Beat(startTime + gap * 110, "Space"),
					new Beat(startTime + gap * 112, "Space"),
					new Beat(startTime + gap * 114, "S"),
					new Beat(startTime + gap * 114, "L"),
					new Beat(startTime + gap * 120, "J"),
					new Beat(startTime + gap * 126, "F"),
					new Beat(startTime + gap * 132, "Space"),
					new Beat(startTime + gap * 134, "Space"),
					new Beat(startTime + gap * 136, "S"),
					new Beat(startTime + gap * 136, "L"),
					new Beat(startTime + gap * 142, "F"),
					new Beat(startTime + gap * 148, "J"),
					new Beat(startTime + gap * 154, "Space"),
					new Beat(startTime + gap * 156, "Space"),
					new Beat(startTime + gap * 158, "S"),
					new Beat(startTime + gap * 158, "L"),
					new Beat(startTime + gap * 164, "J"),
					new Beat(startTime + gap * 170, "F"),
					new Beat(startTime + gap * 176, "Space"),
					new Beat(startTime + gap * 178, "Space"),
					new Beat(startTime + gap * 180, "S"),
					new Beat(startTime + gap * 180, "L"),
					
					new Beat(startTime + gap * 275, "F"),
					new Beat(startTime + gap * 280, "Space"),
					new Beat(startTime + gap * 285, "Space"),
					new Beat(startTime + gap * 290, "J"),
					new Beat(startTime + gap * 293, "J"),
					new Beat(startTime + gap * 296, "J"),
					
					new Beat(startTime + gap * 301, "Space"),
					new Beat(startTime + gap * 306, "Space"),
					new Beat(startTime + gap * 311, "F"),
					new Beat(startTime + gap * 314, "F"),
					new Beat(startTime + gap * 317, "F"),
					
					new Beat(startTime + gap * 357, "F"),
					new Beat(startTime + gap * 358, "Space"),
					new Beat(startTime + gap * 359, "J"),
					
					new Beat(startTime + gap * 364, "S"),
					new Beat(startTime + gap * 370, "Space"),
					new Beat(startTime + gap * 372, "J"),
					new Beat(startTime + gap * 376, "J"),
					
					new Beat(startTime + gap * 383, "Space"),
					new Beat(startTime + gap * 392, "Space"),
					new Beat(startTime + gap * 394, "Space"),
					new Beat(startTime + gap * 398, "S"),
					
			};
//		new Reminder();
		} else if (titleName.equals("F-777 - Sonic Blaster")) {
			int startTime = Main.REACH_TIME;
			int gap = 125;
			beats = new Beat[] { 
					new Beat(startTime + gap * 3, "S"),
					new Beat(startTime + gap * 3, "D"),
					new Beat(startTime + gap * 9, "L"),
					new Beat(startTime + gap * 16, "S"),
					new Beat(startTime + gap * 23, "J"),
					new Beat(startTime + gap * 30, "Space"),
					new Beat(startTime + gap * 36, "F"),
					new Beat(startTime + gap * 43, "L"),
					new Beat(startTime + gap * 50, "F"),
					new Beat(startTime + gap * 57, "S"),
					new Beat(startTime + gap * 57, "D"),
					new Beat(startTime + gap * 60, "L"),
					new Beat(startTime + gap * 63, "F"),
					new Beat(startTime + gap * 66, "J"),
					new Beat(startTime + gap * 69, "S"),
					new Beat(startTime + gap * 72, "D"),
					new Beat(startTime + gap * 75, "S"),
					new Beat(startTime + gap * 78, "L"),
					new Beat(startTime + gap * 82, "Space"),
					new Beat(startTime + gap * 85, "Space"),
					new Beat(startTime + gap * 88, "Space"),
					new Beat(startTime + gap * 91, "Space"),
					new Beat(startTime + gap * 94, "Space"),
					
					new Beat(startTime + gap * 108, "K"),
					new Beat(startTime + gap * 108, "L"),
					new Beat(startTime + gap * 115, "S"),
					new Beat(startTime + gap * 122, "F"),
					new Beat(startTime + gap * 129, "Space"),
					new Beat(startTime + gap * 135, "K"),
					new Beat(startTime + gap * 135, "L"),
					new Beat(startTime + gap * 142, "F"),
					new Beat(startTime + gap * 149, "K"),
					new Beat(startTime + gap * 149, "L"),
					new Beat(startTime + gap * 156, "Space"),
					new Beat(startTime + gap * 163, "S"),
					new Beat(startTime + gap * 169, "Space"),
					new Beat(startTime + gap * 175, "S"),
					new Beat(startTime + gap * 175, "D"),
					new Beat(startTime + gap * 181, "K"),
					new Beat(startTime + gap * 181, "L"),
					new Beat(startTime + gap * 188, "D"),
					new Beat(startTime + gap * 188, "K"),
					new Beat(startTime + gap * 191, "D"),
					new Beat(startTime + gap * 191, "K"),
					new Beat(startTime + gap * 194, "D"),
					new Beat(startTime + gap * 194, "K"),
					new Beat(startTime + gap * 197, "D"),
					new Beat(startTime + gap * 197, "K"),
					new Beat(startTime + gap * 200, "D"),
					new Beat(startTime + gap * 200, "K"),
					
					new Beat(startTime + gap * 218, "S"),
					new Beat(startTime + gap * 221, "L"),
					new Beat(startTime + gap * 224, "D"),
					new Beat(startTime + gap * 227, "K"),
					new Beat(startTime + gap * 230, "F"),
					new Beat(startTime + gap * 233, "J"),
					new Beat(startTime + gap * 236, "Space"),
					new Beat(startTime + gap * 238, "Space"),
					new Beat(startTime + gap * 240, "Space"),
					
					new Beat(startTime + gap * 244, "S"),
					new Beat(startTime + gap * 247, "L"),
					new Beat(startTime + gap * 250, "D"),
					new Beat(startTime + gap * 253, "K"),
					new Beat(startTime + gap * 256, "F"),
					new Beat(startTime + gap * 259, "J"),
					new Beat(startTime + gap * 262, "Space"),
					new Beat(startTime + gap * 264, "Space"),
					new Beat(startTime + gap * 266, "Space"),
					
					new Beat(startTime + gap * 270, "S"),
					new Beat(startTime + gap * 273, "L"),
					new Beat(startTime + gap * 276, "D"),
					new Beat(startTime + gap * 279, "K"),
					new Beat(startTime + gap * 282, "F"),
					new Beat(startTime + gap * 285, "J"),
					new Beat(startTime + gap * 288, "Space"),
					new Beat(startTime + gap * 290, "Space"),
					new Beat(startTime + gap * 292, "Space"),

					new Beat(startTime + gap * 296, "Space"),
					new Beat(startTime + gap * 300, "Space"),
					new Beat(startTime + gap * 304, "Space"),
					
					
			};
//			new Reminder();
		} else if (titleName.equals("Panda Eyes & Teminite - Highscore")) {
			int startTime = Main.REACH_TIME;
			int gap = 125;
			beats = new Beat[] { 
					new Beat(startTime + gap * 23, "Space"),
					new Beat(startTime + gap * 57, "S"), 
					new Beat(startTime + gap * 57, "L"),
					new Beat(startTime + gap * 74, "D"), 
					new Beat(startTime + gap * 74, "K"),
					new Beat(startTime + gap * 91, "F"), 
					new Beat(startTime + gap * 91, "J"),
					new Beat(startTime + gap * 108, "Space"),
					new Beat(startTime + gap * 117, "F"), 
					new Beat(startTime + gap * 117, "J"),
					new Beat(startTime + gap * 121, "D"), 
					new Beat(startTime + gap * 121, "K"),
					new Beat(startTime + gap * 125, "S"), 
					new Beat(startTime + gap * 125, "L"),
					new Beat(startTime + gap * 145, "S"), 
					new Beat(startTime + gap * 145, "L"),
					new Beat(startTime + gap * 162, "D"), 
					new Beat(startTime + gap * 162, "K"),
					new Beat(startTime + gap * 179, "F"), 
					new Beat(startTime + gap * 179, "J"),
					new Beat(startTime + gap * 196, "Space"),
					new Beat(startTime + gap * 201, "S"),
					new Beat(startTime + gap * 203, "D"),
					new Beat(startTime + gap * 205, "F"),
					new Beat(startTime + gap * 207, "Space"),
					new Beat(startTime + gap * 209, "J"),
					new Beat(startTime + gap * 211, "K"),
					new Beat(startTime + gap * 213, "L"),
					new Beat(startTime + gap * 219, "S"),
					new Beat(startTime + gap * 221, "L"),
					new Beat(startTime + gap * 223, "D"),
					new Beat(startTime + gap * 225, "K"),
					new Beat(startTime + gap * 227, "F"),
					new Beat(startTime + gap * 229, "J"),
					new Beat(startTime + gap * 231, "Space"),
					new Beat(startTime + gap * 236, "L"),
					new Beat(startTime + gap * 238, "S"),
					new Beat(startTime + gap * 240, "K"),
					new Beat(startTime + gap * 242, "D"),
					new Beat(startTime + gap * 244, "J"),
					new Beat(startTime + gap * 246, "F"),
					new Beat(startTime + gap * 248, "Space"),
					new Beat(startTime + gap * 265, "Space"),
					new Beat(startTime + gap * 283, "Space"),
					new Beat(startTime + gap * 301, "Space"),
					new Beat(startTime + gap * 318, "Space"),
					new Beat(startTime + gap * 340, "S"),
					new Beat(startTime + gap * 340, "L"),
					new Beat(startTime + gap * 341, "Space"),
					new Beat(startTime + gap * 342, "S"),
					new Beat(startTime + gap * 342, "L"),
					new Beat(startTime + gap * 343, "Space"),
					new Beat(startTime + gap * 344, "S"),
					new Beat(startTime + gap * 344, "L"),
					new Beat(startTime + gap * 345, "S"),
					new Beat(startTime + gap * 345, "L"),
					new Beat(startTime + gap * 346, "S"),
					new Beat(startTime + gap * 346, "L"),
					new Beat(startTime + gap * 347, "S"),
					new Beat(startTime + gap * 347, "L"),
					new Beat(startTime + gap * 348, "S"),
					new Beat(startTime + gap * 348, "L"),
					new Beat(startTime + gap * 349, "S"),
					new Beat(startTime + gap * 349, "L"),
					new Beat(startTime + gap * 350, "S"),
					new Beat(startTime + gap * 350, "L"),
					new Beat(startTime + gap * 351, "S"),
					new Beat(startTime + gap * 351, "L"),
					new Beat(startTime + gap * 353, "Space"),
//					new Beat(startTime + gap * 357, "S"),
//					new Beat(startTime + gap * 357, "L"),
//					new Beat(startTime + gap * 358, "Space"),
//					new Beat(startTime + gap * 359, "S"),
//					new Beat(startTime + gap * 359, "L"),
//					new Beat(startTime + gap * 360, "Space"),
//					new Beat(startTime + gap * 361, "S"),
//					new Beat(startTime + gap * 361, "L"),
//					new Beat(startTime + gap * 362, "S"),
//					new Beat(startTime + gap * 362, "L"),
//					new Beat(startTime + gap * 363, "S"),
//					new Beat(startTime + gap * 363, "L"),
//					new Beat(startTime + gap * 364, "S"),
//					new Beat(startTime + gap * 364, "L"),
//					new Beat(startTime + gap * 365, "S"),
//					new Beat(startTime + gap * 365, "L"),
//					new Beat(startTime + gap * 366, "S"),
//					new Beat(startTime + gap * 366, "L"),
//					new Beat(startTime + gap * 367, "S"),
//					new Beat(startTime + gap * 367, "L"),
//					new Beat(startTime + gap * 368, "S"),
//					new Beat(startTime + gap * 368, "L"),
//					new Beat(startTime + gap * 370, "Space"),
//					new Beat(startTime + gap * 371, "S"),
//					new Beat(startTime + gap * 371, "L"),
//					new Beat(startTime + gap * 372, "Space"),
//					new Beat(startTime + gap * 373, "S"),
//					new Beat(startTime + gap * 373, "L"),
//					new Beat(startTime + gap * 374, "Space"),
//					new Beat(startTime + gap * 375, "S"),
//					new Beat(startTime + gap * 375, "L"),
//					new Beat(startTime + gap * 376, "S"),
//					new Beat(startTime + gap * 376, "L"),
//					new Beat(startTime + gap * 377, "S"),
//					new Beat(startTime + gap * 377, "L"),
//					new Beat(startTime + gap * 378, "S"),
//					new Beat(startTime + gap * 378, "L"),
//					new Beat(startTime + gap * 379, "S"),
//					new Beat(startTime + gap * 379, "L"),
//					new Beat(startTime + gap * 380, "S"),
//					new Beat(startTime + gap * 380, "L"),
//					new Beat(startTime + gap * 381, "S"),
//					new Beat(startTime + gap * 381, "L"),
//					new Beat(startTime + gap * 382, "S"),
//					new Beat(startTime + gap * 382, "L"),
			};
//		new Reminder();
		}

		int i = 0;
		gameMusic.start();
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void addScore(String titleName) {
		BufferedWriter bw = null;
		String filePath = null;

		if (titleName.equals("Hinkik - Time Leaper")) {
			filePath = "timeLeaper.txt";
		} else if (titleName.equals("F-777 - Sonic Blaster")) {
			filePath = "sonicBlaster.txt";
		} else if (titleName.equals("Panda Eyes & Teminite - Highscore")) {
			filePath = "highscore.txt";
		}

		try {
			String entry = null;
			File scores = new File(filePath);
			if (!scores.exists()) {
				scores.createNewFile();
			}
			FileWriter fw = new FileWriter(scores, true);
			bw = new BufferedWriter(fw);
			entry = this.userName + "/" + this.score;
			bw.write(entry);
			bw.newLine();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				String judgment = note.judge();
				judgeEvent(judgment);
				break;
			}
		}
	}

	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {
			flareImage = new ImageIcon(Main.class.getResource("../images/Flare.png")).getImage();
		}
		if (judge.equals("X")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeX.png")).getImage();
		} else if (judge.equals("50")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judge50.png")).getImage();
			score += 50;
		} else if (judge.equals("100")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judge100.png")).getImage();
			score += 100;
		} else if (judge.equals("200")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judge200.png")).getImage();
			score += 200;
		} else if (judge.equals("300")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judge300.png")).getImage();
			score += 300;
		} else if (judge.equals("300!")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judge300!.png")).getImage();
			score += 300;
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTitleName() {
		return titleName;
	}

	public int getScore() {
		return score;
	}

	public class Reminder {
		Timer timer;
		
		public Reminder(int seconds) {
			timer = new Timer();
			timer.schedule(new RemindTask(), seconds * 1000);
		}
		
		class RemindTask extends TimerTask {

			@Override
			public void run() {
				Main.rhythm.enterScores();
				timer.cancel();
			}
			
		}
	}
}
