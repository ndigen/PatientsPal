import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class CSVReader {

	private static final String REGEX = "(,(?=\\S)|:)";
	
	/**
	 * Initializes the data as an array of hospitals
	 * 
	 * @return array of hospitals
	 * @throws FileNotFoundException
	 */
	public static void init() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("data//Patient_survey__HCAHPS__-_Hospital.csv"));
		scanner.nextLine();

		Client.hospitals = new Hospital[4806];
		Client.countyList = new ArrayList<ArrayList<String>>(50);

		int count = 0;
		String line = scanner.nextLine();
		String[] fields = line.split(REGEX);

		String currentState = "";
		String currentCounty = "";
		int stateCount = -1;
		
		while (scanner.hasNextLine()) {
			int id = Integer.parseInt(fields[0]);
			String name = fields[1].replaceAll("\"", "");
			String address = fields[2];
			String zip = fields[5];
			String state = fields[4];
			String phoneNum = fields[7];
			String county = fields[6];
			double latitude = Double.parseDouble(fields[14]);
			double longitude = Double.parseDouble(fields[15]);
			
			
			
			if(!county.equals(currentCounty)){
				if(!state.equals(currentState)){
					stateCount++;
					Client.countyList.add(new ArrayList<String>());
					Client.countyList.get(stateCount).add(state);
					currentState = state;
				}
				Client.countyList.get(stateCount).add(county);
				currentCounty = county;
			}

			int[] reviews = new int[28];
			for (int i = 0; i < 28; i++) {
				if (!fields[11].equals("Not Available")) {
					reviews[i] = Integer.parseInt(fields[11]);
				} else
					reviews[i] = -1;

				if (scanner.hasNextLine())
					line = scanner.nextLine();
				fields = line.split(REGEX);
			}
			Client.hospitals[count] = new Hospital(id, name, address, zip, state, phoneNum, county, latitude, longitude, reviews);
			count++;
		}
		scanner.close();
		
	}

}
