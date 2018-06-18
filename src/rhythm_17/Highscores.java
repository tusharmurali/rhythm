package rhythm_17;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Highscores {
	
	public static String getHighScore(String titleName) {
		String filePath = null;
		if (titleName.equals("Hinkik - Time Leaper")) {
			filePath = "timeLeaper.txt";
		} else if (titleName.equals("F-777 - Sonic Blaster")) {
			filePath = "sonicBlaster.txt";
		} else if (titleName.equals("Panda Eyes & Teminite - Highscore")) {
			filePath = "highscore.txt";
		}
		
		String highest = null;
		try {
			File scores = new File(filePath);
			if (!scores.exists()) {
				scores.createNewFile();
			}
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = br.readLine();
			if (line != null) {
				highest = line.substring(0, line.length());
			} else {
				br.close();
				return "none";
			}
			int highScore = Integer.parseInt(line.substring(line.indexOf("/") + 1));
			while ((line = br.readLine()) != null) {
				if (line.contains("/")) {
					if (highScore < Integer.parseInt(line.substring(line.indexOf("/") + 1))) {
						highest = line;
					}
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return highest;
	}
}
