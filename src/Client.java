import java.util.ArrayList;
import java.util.Scanner;

import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.SymbolGraph;

public class Client {
	
	public static Hospital[] hospitals;
	public static Hospital[] sortedHospitals;
	public static ArrayList<ArrayList<String>> countyList;
	public static EdgeWeightedGraph graph;
	public static String[] stateList = {"ALL", "AK","AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA",
						  "GU","HI","IA","ID", "IL","IN","KS","KY","LA","MA","MD",
						  "ME","MH","MI","MN","MO","MS","MT","NC","ND","NE","NH",
						  "NJ","NM","NV","NY", "OH","OK","OR","PA","PR","PW","RI",
						  "SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY"};

	
	public static void main(String[] args) throws Exception {
		CSVReader.initData();
		CSVReader.initGraph();
		
		GUI frame = new GUI();
		frame.setVisible(true);
	}
}
