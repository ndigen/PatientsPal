
public class Distance_Calculator {
	public static double distance;
	public Distance_Calculator(double latitude1, double longitude1, double latitude, double longitude){
		double difference, difference1;
		
		difference = (latitude1 - latitude)*(latitude1 - latitude);
		difference1 = (longitude1 - longitude) * (longitude1 - longitude);
		
		distance = Math.sqrt(difference + difference1);
	}
}
