package project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlayMusic {

	static int chordMarker = 0;
	static File[][] chordProgression = new File[4][3];
	static int column = 0;
	static int currentChord = 0;
	static String currentCodon;
	static char Key = 'C';
	static String[] Key_C = { "C", "D", "E", "F", "G", "A", "B" };
	static int measureMarker = 0;
	static File[][] musicArray = new File[88][4];
	static ArrayList<String> notes = new ArrayList<String>();
	static int row = 0;

	public static void MusicPlayer() {
		CodonDriver driver = new CodonDriver();
		initializeMusic();

		ArrayList<Character> sequence = driver.totalSequence;
		int counter = 0;
		char[] stringHolder = new char[3];

		for (int i = 0; i < sequence.size(); i++) {

			char holder = sequence.get(i);

			stringHolder[counter] = holder;
			counter++;

			if (counter >= 3) {
				String sequenceHolderMaster = null;
				sequenceHolderMaster = String.valueOf(stringHolder);
				currentCodon = sequenceHolderMaster;
				counter = 0;

				System.out.println(currentCodon);
				codonMusicEdit(currentCodon);
				
				if (chordMarker >= 4) {
					chordMarker = 0;
				}
				playMusic(currentCodon);
			}

		}
		ArrayList<String> AllNotes = new ArrayList<String>();

		for (int i = 0; i < notes.size(); i++) {
			String holder = notes.get(i);
			String newHolder = holder.substring(0, 2);
			AllNotes.add(newHolder);
		}
		System.out.print(AllNotes);
	}

	static boolean changeChord(boolean b) {
		currentChord++;
		if (currentChord >= 4) {
			currentChord = 0;
		}
		return b;
	}

	static void codonMusicEdit(String s) {

		switch (s.substring(0, 2)) {

		case "AA":
			raisePitch(s, 1);
			break;
		case "AC":
			lowerPitch(s, 1);
			break;
		case "AT":
			raisePitch(s, 2);
			break;
		case "AG":
			lowerPitch(s, 2);
			break;

		case "CA":
			raisePitch(s, 3);
			break;
		case "CC":
			lowerPitch(s, 3);
			break;
		case "CT":
			raisePitch(s, 4);
			break;
		case "CG":
			lowerPitch(s, 4);
			break;

		case "TA":
			raisePitch(s, 5);
			break;
		case "TC":
			lowerPitch(s, 5);
			break;
		case "TT":
			raisePitch(s, 6);
			break;
		case "TG":
			lowerPitch(s, 6);
			break;

		case "GA":
			raisePitch(s, 7);
			break;
		case "GC":
			lowerPitch(s, 7);
			break;
		case "GT":
			raisePitch(s, 0);
			break;
		case "GG":
			lowerPitch(s, 0);
			break;
		default:
			break;

		}
	}

	static void initializeMusic() {

		String[] musicKeys = { "A", "Ab", "B", "Bb", "C", "D", "Db", "E", "Eb",
				"F", "G", "Gb" };

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 7; k++) {
					try {
						musicArray[7 * j + k][i] = new File("src/files/SoundFiles/SoundFiles" + (i + 1) + "/" + Key_C[k] + (j) + ".wav");
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				if (j == 3) {
					chordProgression[0][0] = new File("src/files/SoundFiles/SoundFiles" + 4 + "/C3.wav");
					chordProgression[0][1] = new File("src/files/SoundFiles/SoundFiles" + 4 + "/E3.wav");
					chordProgression[0][2] = new File("src/files/SoundFiles/SoundFiles" + 4 + "/G3.wav");
					chordProgression[1][0] = new File("src/files/SoundFiles/SoundFiles" + 4 + "/D3.wav");
					chordProgression[1][1] = new File("src/files/SoundFiles/SoundFiles" + 4 + "/G3.wav");
					chordProgression[1][2] = new File("src/files/SoundFiles/SoundFiles" + 4 + "/B3.wav");
					chordProgression[2][0] = new File("src/files/SoundFiles/SoundFiles" + 4 + "/C3.wav");
					chordProgression[2][1] = new File("src/files/SoundFiles/SoundFiles" + 4 + "/A3.wav");
					chordProgression[2][2] = new File("src/files/SoundFiles/SoundFiles" + 4 + "/E3.wav");
					chordProgression[3][0] = new File("src/files/SoundFiles/SoundFiles" + 4 + "/C3.wav");
					chordProgression[3][1] = new File("src/files/SoundFiles/SoundFiles" + 4 + "/F3.wav");
					chordProgression[3][2] = new File("src/files/SoundFiles/SoundFiles" + 4 + "/A3.wav");
				}
			}
		}

		row = 25;
		column = 1;

		File startingNote = new File("src/files/SoundFiles/SoundFiles" + Key + 4 + ".wav");

		try {
			Clip clip = AudioSystem.getClip();

			try {
				clip.open(AudioSystem.getAudioInputStream(startingNote));

			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			clip.start();
			try {
				Thread.sleep(clip.getMicrosecondLength() / 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	static void lowerPitch(String s, int lowerBy) {

		if ((row - lowerBy) > 20) {
			row -= lowerBy;
		}
		switch (s.substring(2)) {

		case "A":
			noteValue(1);
			break;
		case "C":
			noteValue(2);
			break;
		case "T":
			noteValue(3);
			break;
		case "G":
			noteValue(4);
			break;
		default:
			break;
		}
	}

	static void noteValue(int value) {
		switch (value) {

		case 1:
			column = 0;
			measureMarker++;

			if (measureMarker >= 8) {
				changeChord(true);
			}
			break; // eighth
		case 2:
			column = 1;
			measureMarker += 2;
			if (measureMarker >= 8) {
				changeChord(true);
			}
			break; // quarter
		case 3:
			column = 2;
			measureMarker += 4;
			if (measureMarker >= 8) {
				changeChord(true);
			}
			break; // half
		case 4:
			column = 3;
			measureMarker += 8;
			if (measureMarker >= 8) {
				changeChord(true);
			}
			break; // whole
		default:
			break;

		}
	}

	static void playMusic(String s) {
		try {
			runningMusic();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	static void raisePitch(String s, int raiseBy) {

		if (row + raiseBy < 55) {
			row += raiseBy;
		}
		switch (s.substring(2)) {

		case "A":
			noteValue(1);
			break;
		case "C":
			noteValue(2);
			break;
		case "T":
			noteValue(3);
			break;
		case "G":
			noteValue(4);
			break;
		default:
			break;
		}
	}

	static void runningMusic() throws LineUnavailableException {

		File currentNote = musicArray[row][column];

		try {
			Clip clip = AudioSystem.getClip();
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			Clip clip2 = AudioSystem.getClip();
			try {
				clip2 = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			Clip clip3 = AudioSystem.getClip();
			try {
				clip3 = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			Clip clip4 = AudioSystem.getClip();
			try {
				clip4 = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			try {
				clip.open(AudioSystem.getAudioInputStream(currentNote));
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			try {
				clip2.open(AudioSystem.getAudioInputStream(chordProgression[currentChord][0]));
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			try {
				clip3.open(AudioSystem.getAudioInputStream(chordProgression[currentChord][1]));
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			try {
				clip4.open(AudioSystem.getAudioInputStream(chordProgression[currentChord][2]));
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			clip2.start();
			clip3.start();
			clip4.start();

			try {
				switch (column) {
				case 0:
					// System.out.println("Eighth");
					clip.loop(7);
					break;
				case 1:
					// System.out.println("Quarter");
					clip.loop(3);

					break;
				case 2:
					// System.out.println("Half");
					clip.loop(1);

					break;
				case 3:
					break;
				default:
					break;
				}

				Thread.sleep(clip2.getMicrosecondLength() / 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		notes.add(currentNote.getName());

	}
}
