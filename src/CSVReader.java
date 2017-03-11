import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVReader {

	private static final String REGEX = "(,(?=\\S)|:)";
	
	/**
	 * Initializes the data as an array of hospitals
	 * 
	 * @return array of hospitals
	 * @throws FileNotFoundException
	 */
	public static Hospital[] init() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("data//Patient_survey__HCAHPS__-_Hospital.csv"));
		scanner.nextLine();

		Hospital[] hospitals = new Hospital[4807];

		int count = 0;
		String line = scanner.nextLine();
		String[] fields = line.split(REGEX);

		while (scanner.hasNextLine()) {

			int id = Integer.parseInt(fields[0]);
			String name = fields[1];
			String address = fields[2];
			String zip = fields[5];
			String state = fields[4];
			String phoneNum = fields[7];
			String county = fields[6];

			int[] reviews = new int[29];
			for (int i = 0; i < 29; i++) {
				if (!fields[11].equals("Not Available")) {
					reviews[i] = Integer.parseInt(fields[11]);
				} else
					reviews[i] = -1;

				if (scanner.hasNextLine())
					line = scanner.nextLine();
				fields = line.split(REGEX);
			}
			hospitals[count] = new Hospital(id, name, address, zip, state, phoneNum, county, reviews);
			count++;
		}
		scanner.close();
		return hospitals;
	}

}
