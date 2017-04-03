import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;

import java.util.ArrayList;

public class CSVReader {

	private static final String REGEX = "(,(?=\\S)|:)";
	
	/**
	 * Initializes all the data to be used from the dataset
	 * 
	 * @throws FileNotFoundException
	 */
	public static void initData() throws FileNotFoundException {
		//open file for reading
		Scanner scanner = new Scanner(new File("data//Patient_survey__HCAHPS__-_Hospital.csv"));
		scanner.nextLine();

		//initialize the array to hold hospitals and counties
		Client.hospitals = new Hospital[4807];
		Client.countyList = new ArrayList<ArrayList<String>>(50);

		
		String line = scanner.nextLine();
		String[] fields = line.split(REGEX);

		String currentState = "";
		String currentCounty = "";
		int stateCount = -1;
		
		int count = 0;
		while (scanner.hasNextLine()) {
			//save field variables to initalize hospital object
			int id = Integer.parseInt(fields[0]);
			String name = fields[1].replaceAll("\"", "");
			String address = fields[2];
			String zip = fields[5];
			String state = fields[4];
			String phoneNum = fields[7];
			String county = fields[6];
			double latitude = Double.parseDouble(fields[14]);
			double longitude = Double.parseDouble(fields[15]);
			
			
			//Creates the countyList array using state and county data
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

			//initialize the reviews, if review is not available, make it 0
			int[] reviews = new int[27];
			for (int i = 0; i < 27; i++) {
				if (!fields[11].equals("Not Available")) {
					reviews[i] = Integer.parseInt(fields[11]);
				} else
					reviews[i] = -1;

				if (scanner.hasNextLine())
					line = scanner.nextLine();
				fields = line.split(REGEX);
				
			}
			
			//Making sure that we have moved on to the next hospital
			while(fields[2].equals(address) && scanner.hasNextLine()){
				
				line = scanner.nextLine();
				fields = line.split(REGEX);
			}
			
			//initialize the hospital
			Client.hospitals[count] = new Hospital(id, name, address, zip, state, phoneNum, county, latitude, longitude, reviews);
			count++;
		}
		scanner.close();
		Client.sortedHospitals = Client.hospitals.clone();
	}
	
	public static void initGraph() {
		Client.graph = new EdgeWeightedGraph(Client.hospitals.length);
		
		for(int i = 0; i < Client.hospitals.length; i++) {
			for(int j = 0; j < Client.hospitals.length; j++) {
				if(j == i) continue;
				if(Client.hospitals[i].distTo(Client.hospitals[j]) <= 50) {
					Client.graph.addEdge(new Edge(i, j, Client.hospitals[i].distTo(Client.hospitals[j])));
				}
			}
		}
	}

}
