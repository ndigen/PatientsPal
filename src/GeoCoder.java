import java.io.*;
import java.util.Scanner;

import com.google.maps.*;
import com.google.maps.errors.*;
import com.google.maps.model.*;
import com.google.maps.internal.*;

//used to get latitudes and latitudes and update the dataset
//Dont use this stuff. may just delete this since dataset is updated

public class GeoCoder {

	private static final String REGEX = "(,(?=\\S)|:)";
	private static GeoApiContext context = new GeoApiContext().setApiKey("API KEY GOES HERE");

	public static void updateLatLong() throws Exception {
		Scanner in = new Scanner(new File("data//Patient_survey__HCAHPS__-_Hospital.csv"));
		BufferedWriter out = new BufferedWriter(new FileWriter("data//Patient_survey__HCAHPS__-_Hospital_FIXED.csv"));

		in.nextLine();

		while (in.hasNextLine()) {
			String line = in.nextLine();
			String address = line.split(REGEX)[1] + ", " + line.split(REGEX)[6]  + ", " + line.split(REGEX)[4];
			String latLong = getLatLon(address);
			System.out.println(latLong);
			for (int i = 0; i < 28; i++) {
				out.write(line + ", " + latLong);
				out.newLine();
				line = in.nextLine();
			}
		}
		
		in.close();
		out.close();
	}
	
	public static void fixLatLong() throws Exception {
		Scanner in = new Scanner(new File("data//Patient_survey__HCAHPS__-_Hospital.csv"));
		BufferedWriter out = new BufferedWriter(new FileWriter("data//Patient_survey__HCAHPS__-_Hospital_FIXED.csv"));

		out.write(in.nextLine());
		out.newLine();

		while (in.hasNextLine()) {
			String line = in.nextLine();
			if(line.split(REGEX).length < 15){
				String address = line.split(REGEX)[1];
				String latLong = getLatLon(address);
				if(latLong.equals("0,0")) latLong = getLatLon(line.split(REGEX)[2] + ", " + line.split(REGEX)[4]);
				System.out.println(latLong);
				for (int i = 0; i < 28; i++) {
					out.write(line + "," + latLong);
					out.newLine();
					line = in.nextLine();
				}
			}else{
				out.write(line);
				out.newLine();
			}
		}
		
		in.close();
		out.close();
	}

	public static String getLatLon(String address) throws Exception {
		GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
		if(results.length > 0)
			return results[0].geometry.location.toString();
		return "0,0";
	}
}
