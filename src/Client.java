import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	
	public static Hospital[] hospitals;
	public static ArrayList<ArrayList<String>> countyList;
	public static String[] stateList = {"ALL", "AK","AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA",
						  "GU","HI","IA","ID", "IL","IN","KS","KY","LA","MA","MD",
						  "ME","MH","MI","MN","MO","MS","MT","NC","ND","NE","NH",
						  "NJ","NM","NV","NY", "OH","OK","OR","PA","PR","PW","RI",
						  "SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY"};

	
	public static void main(String[] args) throws Exception {
		CSVReader.init();
		
		Scanner input = new Scanner(System.in); // CHANGE THIS SO IT TAKES THE RIGHT HOSPITAL / HOSPITAL ID from GUI.
		System.out.println("Enter a hospital id: ");  // CHANGE THIS SO IT TAKES THE RIGHT HOSPITAL / HOSPITAL ID from GUI.
		int hospital_id = input.nextInt(); // CHANGE THIS SO IT TAKES THE RIGHT HOSPITAL / HOSPITAL ID from GUI.
		
		Graph_input inputForGraph = new Graph_input(hospitals, hospital_id);

		//The index values of hospitals was used to make the graph.
		Graph g = new Graph(hospitals, inputForGraph.input, inputForGraph.distan);
		
		
/*		for (Edge i : g.adj(0)){
			System.out.println(i.toString());
		} */  
		
		GUI frame = new GUI();
		frame.setVisible(true);
	}
}
