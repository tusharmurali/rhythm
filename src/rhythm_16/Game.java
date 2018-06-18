package rhythm_16;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

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

	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	
	List<Note> noteList = new ArrayList<Note>();

	private int score = 0;
	
	public Game(String titleName, String difficulty, String musicTitle) {
		super();
		this.titleName = titleName;
		this.difficulty = difficulty;
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
		g.drawString(difficulty, 1190, 702);
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
		Beat[] beats = null;
		if (titleName.equals("Hinkik - Time Leaper") && difficulty.equals("Easy")) {
			int startTime = Main.REACH_TIME;
			beats = new Beat[] { new Beat(startTime, "Space"), }; // Add Beats for each song here
		} else if (titleName.equals("Hinkik - Time Leaper") && difficulty.equals("Hard")) {
			int startTime = Main.REACH_TIME;
			beats = new Beat[] { new Beat(startTime, "Space"), };
		} else if (titleName.equals("F-777 - Sonic Blaster") && difficulty.equals("Easy")) {
			int startTime = Main.REACH_TIME;
			beats = new Beat[] { new Beat(startTime, "Space") };
		} else if (titleName.equals("F-777 - Sonic Blaster") && difficulty.equals("Hard")) {
			int startTime = Main.REACH_TIME;
			beats = new Beat[] { new Beat(startTime, "Space") };
		} else if (titleName.equals("Panda Eyes & Teminite - Highscore") && difficulty.equals("Easy")) {
			int startTime = Main.REACH_TIME;
			beats = new Beat[] { new Beat(startTime, "Space") };
		} else if (titleName.equals("Panda Eyes & Teminite - Highscore") && difficulty.equals("Hard")) {
			int startTime = Main.REACH_TIME;
			beats = new Beat[] { new Beat(startTime, "Space") };
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

	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
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

}
