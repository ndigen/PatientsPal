import java.lang.reflect.Array;
import java.util.ArrayList;

public class Graph_input {
	
	public static ArrayList<Integer> input;
	public static ArrayList<Double> distan;
	
	public Graph_input(Hospital[] hospitals, int hospital_id){
		
		input = new ArrayList<Integer>();
		distan = new ArrayList<Double>();
		int index_value = 0; //index value of the hospital_id in the constructor.
		
		
		for (int i = 0; i < hospitals.length; i ++){
			if (hospitals[i].getId() == hospital_id)
				index_value = i;
		}
		
		
		Distance_Calculator dist;
		
		
		input.add(index_value);
		for (int i =0; i < hospitals.length; i ++){
			if (hospitals[i].getId() != hospitals[index_value].getId()){
				dist = new Distance_Calculator(hospitals[index_value].getLatitude(), hospitals[index_value].getLongitude(), hospitals[i].getLatitude(), hospitals[i].getLongitude());
				
				if (dist.distance <= 20){
					distan.add(dist.distance);
					input.add(i);
				}
			}
		}
		
		
		
	}
}
