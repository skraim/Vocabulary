import java.io.*;
import java.util.*;

public class Vocabulary {
	
	static Map<String,String> vocab;
	static BufferedReader br;
	static int lul = 0;
	static File currentFile = new File("eng-rus.txt");

	public static void main(String[] args) throws NumberFormatException, IOException {
		int selectedMenu = -1;
		br = new BufferedReader(new InputStreamReader(System.in));
		vocab = new HashMap<String,String>();
		loadFile(currentFile);
		/*
		FileReader reader = new BufferedReader("eng-rus.txt");
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] result =
		}
		*/
		for ( ; ; ) {
			do {
				try {
					System.out.println("Select menu item:");
					System.out.println("1. Put new");
					System.out.println("2. Learn");
					selectedMenu = Integer.parseInt(br.readLine());
					if (selectedMenu==1) {
						putNew();
					} else if (selectedMenu==2) {
						learnEng();
					}
				} catch (NumberFormatException ex) {
					System.out.println("Wrong input!");
					continue;
				} catch (Exception ex) {
					System.out.println("Something went wrong!");
				}
			} while ((selectedMenu!=1)&&((selectedMenu!=2)));
		}
	}

	private static void saveFile(File file, String eng, String rus) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(eng + "/");
			writer.write(rus + "\n");
			writer.close();
			System.out.println("Success!");
		} catch (IOException ex) {
			System.out.println("Error!");
		}
	}

	private static void loadFile(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				takeLine(line);
			}
			System.out.println("File successfuly loaded!");
		} catch (IOException ex) {
			System.out.println("Can't open file!");
		}
	}

	private static void takeLine(String line) {
		String[] result = line.split("/");
		vocab.put(result[0], result[1]);
	}

	public static void putNew() {
		String eng;
		String rus;
		System.out.println("Type \"end\" if you want to stop.");
		do {
			System.out.println("Type new English word:");
			try {
				eng = br.readLine();
				if (eng.equals("end")) {
					//save to file
					break;
				}
			} catch (Exception ex) {
				System.out.println("Error!");
				continue;
			} 
			System.out.println("Type Russian translation:");
			try {
				rus = br.readLine();
				if (rus.equals("end")) {
					//save to file
					break;
				}
			} catch (Exception ex) {
				System.out.println("Error!");
				continue;
			}
			vocab.put(eng, rus);
			saveFile(currentFile, eng, rus);
		} while (true);
	}
	
	public static String learnEng() throws IOException {
		//!!!!!
		if (lul>=50) {
			try {
				Integer.parseInt("asfasfg");
			} catch (Exception ex) {
			ex.printStackTrace();
			}
		}
		lul++;
		//!!!!!
		for (;;) {
			int numOfTests = 0;
			int scores = 0;
			System.out.println("Type \"end\" if you want to stop.");
			System.out.println("Type \"swap\" if you want to swap languages.");
			System.out.println("GL HF!");
			String answer;
			for (Map.Entry<String, String> pair : vocab.entrySet()) {
				System.out.println(pair.getValue());
				answer = br.readLine();
				if (answer.equals("swap")) {
					learnRus();
					break;
				} else if (answer.equals("end")) {
					System.out.println(scores + "/" + numOfTests);
					return null;
				}
				if (answer.equals(pair.getKey())) {
					scores++;
					numOfTests++;
					System.out.println("Right!");
				} else {
					numOfTests++;
					System.out.println("Wrong! The right answer is (!)" + pair.getKey() + "(!)");
				}
			}
		}
	}

	private static String learnRus() throws IOException {
		//!!!!!
		lul++;
		//!!!!!
		int numOfTests = 0;
		int scores = 0;
		System.out.println("Type \"end\" if you want to stop.");
		System.out.println("Type \"swap\" if you want to swap languages.");
		System.out.println("GL HF!");
		String answer;
		for (Map.Entry<String, String> pair : vocab.entrySet()) {
			System.out.println(pair.getKey());
			answer = br.readLine();
			if (answer.equals("swap")) {
				return null;
			} else if (answer.equals("end")) {
				System.out.println(scores + "/" + numOfTests);
				main(null);
			}
			if (answer.equals(pair.getValue())) {
				scores++;
				numOfTests++;
				System.out.println("Right!");
			} else {
				numOfTests++;
				System.out.println("Wrong! The right answer is (!)" + pair.getValue() + "(!)");
			}
		}
	}
}
