package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CodonDriver {

	public static ArrayList<Character> baseArray = new ArrayList<Character>(3);
	public static BufferedReader br;
	public static String chromString = "";
	public static int index = 0;
	public static String line = "";
	public static boolean play = false;
	public static ArrayList<Character> sequence = new ArrayList<Character>();
	public static ArrayList<Character> totalSequence = new ArrayList<Character>();
	public static CodonWindow window;
	private final static String F_E_CODON = "TAA";
	private final static String S_E_CODON = "TAG";
	private final static String START_CODON = "ATG";
	private final static String T_E_CODON = "TGA";

	public static void Driver() throws IOException {
		try {
			br = new BufferedReader(new FileReader("testchr12.txt"));

			while (line != null) {
				line = br.readLine();
				chromString += line;
			}
			br.close();
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			findSequence(index);
		} catch (StringIndexOutOfBoundsException e) {
			PlayMusic.MusicPlayer();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void findSequence(int index) throws IOException {

		char baseA;
		char baseB;
		char baseC;

		baseA = chromString.charAt(index);
		baseArray.add(baseA);
		index++;

		baseB = chromString.charAt(index);
		baseArray.add(baseB);
		index++;

		baseC = chromString.charAt(index);
		baseArray.add(baseC);

		index++;
		String codon = "" + baseA + baseB + baseC;

		while (!codon.equals(START_CODON)) { // Beginning Sequence
			baseArray.clear();

			baseA = chromString.charAt(index);
			baseB = chromString.charAt(index + 1);
			baseC = chromString.charAt(index + 2);
			index += 3;

			baseArray.add(baseA);
			baseArray.add(baseB);
			baseArray.add(baseC);

			codon = "" + baseArray.get(0) + baseArray.get(1) + baseArray.get(2);

		}

		baseArray.clear();
		sequence.clear();

		baseA = chromString.charAt(index);
		baseArray.add(baseA);
		sequence.add(baseA);
		index++;

		baseB = chromString.charAt(index);
		baseArray.add(baseB);
		sequence.add(baseB);
		index++;

		baseC = chromString.charAt(index);
		baseArray.add(baseC);
		sequence.add(baseC);

		index++;
		codon = "" + baseA + baseB + baseC;

		// Ending Sequences here
		while (!codon.equals(F_E_CODON) && !codon.equals(S_E_CODON) && !codon.equals(T_E_CODON)) {
			
			baseArray.remove(0);

			baseA = chromString.charAt(index);
			baseB = chromString.charAt(index + 1);
			baseC = chromString.charAt(index + 2);

			sequence.add(baseA);
			sequence.add(baseB);
			sequence.add(baseC);

			index += 3;

			baseArray.add(baseA);
			baseArray.add(baseB);
			baseArray.add(baseC);

			codon = "" + baseArray.get(0) + baseArray.get(1) + baseArray.get(2);
		}

		// adjust sequence array here to get rid of last three elements
		sequence.remove(sequence.size() - 1);
		sequence.remove(sequence.size() - 1);
		sequence.remove(sequence.size() - 1);
		System.out.println(index);
		System.out.println(sequence + "\n\n\n");
		totalSequence.addAll(sequence);
		findSequence(index);

	}

	public static void main(String[] args) {

		window = new CodonWindow();

	}
}
