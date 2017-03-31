import java.util.Comparator;

public class Hospital {

	private int id;
	private String name;
	private String address;
	private String zip;
	private String state;
	private String county;
	private String phoneNum;
	private double latitude;
	private double longitude;
	private int[] ratings;

	/**
	 * Constructs a Hospital object
	 * 
	 * @param id
	 *            ID of the hospital
	 * @param name
	 *            Name of the hospital
	 * @param address
	 *            Address of the Hospital
	 * @param zip
	 *            ZIP code of the hospital
	 * @param state
	 *            State of the hospital
	 * @param phoneNum
	 *            Phone number of the hospital
	 * @param county
	 *            County of the hospital
	 * @param ratings
	 *            array of all the rating statistics
	 */
	public Hospital(int id, String name, String address, String zip, String state, String phoneNum, String county,
			double latitude, double longitude, int[] ratings) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.zip = zip;
		this.state = state;
		this.phoneNum = phoneNum;
		this.county = county;
		this.latitude = latitude;
		this.longitude = longitude;
		this.ratings = ratings;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @return the ratings
	 */
	public int[] getRatings() {
		return ratings;
	}

	/**
	 * Uses the Haversine formula to calculate the distance between two
	 * hospitals
	 * 
	 * @param that
	 *            hospital to find distance to
	 * @return distance between hospitals
	 */
	public double distTo(Hospital that) {
		int R = 6371;
		double lat1 = degreesToRadians(this.latitude);
		double lat2 = degreesToRadians(that.getLatitude());
		double lon1 = this.longitude;
		double lon2 = that.getLongitude();

		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;

		dlon = degreesToRadians(dlon);

		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;

		return d;
	}

	/**
	 * @return Cleanliness Rating
	 */
	public double getCleanlinessRating() {
		return 0 * ratings[2] + 0.5 * ratings[1] + 1 * ratings[0];
	}

	/**
	 * @return nurse communication rating
	 */
	public double getNurseCommunicationRating() {
		return 0 * ratings[5] + 0.5 * ratings[4] + 1 * ratings[3];
	}

	/**
	 * @return doctor communication rating
	 */
	public double getDoctorCommunicationRating() {
		return 0 * ratings[8] + 0.5 * ratings[7] + 1 * ratings[6];
	}

	/**
	 * @return help rating
	 */
	public double getHelpRating() {
		return 0 * ratings[11] + 0.5 * ratings[10] + 1 * ratings[9];
	}

	/**
	 * @return pain control rating
	 */
	public double getPainControlRating() {
		return 0 * ratings[14] + 0.5 * ratings[13] + 1 * ratings[12];
	}

	/**
	 * @return quietness rating
	 */
	public double getQuietnessRating() {
		return 0 * ratings[26] + 0.5 * ratings[25] + 1 * ratings[24];
	}
	
	/**
	 * @return overall rating
	 */
	public double getOverallRating() {
		double sum = getCleanlinessRating() + getNurseCommunicationRating() + getDoctorCommunicationRating()
				+ getHelpRating() + getPainControlRating() + getQuietnessRating();
		return sum / 6;
	}

	/**
	 * @return formatting string for details JTextArea
	 */
	public String getDetails() {
		if(getOverallRating() < 0)
			return String.format(
					"Name: " + name + "\nLocation: " + address + ", " + county + ", " + state + "\nOverall Rating: NA "
							+ "\n   Cleanliness: NA" + "\n   Nurse Communication: NA"
							+ "\n   Doctor Communication: NA" + "\n   Effective Help: NA" + "\n   Pain Control: NA"
							+ "\n   Quietness: NA");
		return String.format(
				"Name: " + name + "\nLocation: " + address + ", " + county + ", " + state + "\nOverall Rating: %.2f "
						+ "\n   Cleanliness: %.2f" + "\n   Nurse Communication: %.2f"
						+ "\n   Doctor Communication: %.2f" + "\n   Effective Help: %.2f" + "\n   Pain Control: %.2f"
						+ "\n   Quietness: %.2f",
				getOverallRating(), getCleanlinessRating(), getNurseCommunicationRating(),
				getDoctorCommunicationRating(), getHelpRating(), getPainControlRating(), getQuietnessRating());
	}

	@Override
	public String toString() {
		if(getOverallRating() < 0)
			return String.format("%-70s%-20s%-30s%-50sNA\n", name, state, county, address, getOverallRating());
		return String.format("%-70s%-20s%-30s%-50s%.2f\n", name, state, county, address, getOverallRating());
	}

	/**
	 * Compares a hospital to another on different criteria
	 * 
	 * @param that
	 *            the hospital to compare to
	 * @param criteria
	 *            criteria to compare by
	 * @return -1 if less, 0 if equal, 1 if more
	 */
	public int compareTo(Hospital that, String criteria) {
		if (criteria.equals("Location")) {
			if (state.equals(that.getState())) {
				if (county.equals(that.getCounty())) {
					return address.compareTo(that.getAddress());
				} else {
					return county.compareTo(that.getCounty());
				}
			} else
				return state.compareTo(that.getState());
		} else if (criteria.equals("Name")) {
			return name.compareTo(that.getName());
		} else if (criteria.equals("Overall Rating")) {
			if (getOverallRating() < that.getOverallRating()) {
				return 1;
			} else if (getOverallRating() > that.getOverallRating()) {
				return -1;
			} else {
				return 0;
			}
		}

		return 0;
	}

	/**
	 * converts degrees to radians
	 * 
	 * @param x
	 *            number to be converted
	 * @return radian value
	 */
	private double degreesToRadians(double x) {
		return x * (Math.PI / 180);
	}
}
