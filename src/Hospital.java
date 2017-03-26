import java.util.Comparator;

public class Hospital {

	private int id;
	private String name;
	private String address;
	private String zip;
	private String state;
	private String county;
	private String phoneNum;
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
			int[] ratings) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.zip = zip;
		this.state = state;
		this.phoneNum = phoneNum;
		this.county = county;
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
	 * @return the ratings
	 */
	public int[] getRatings() {
		return ratings;
	}
	
	public double getCleanlinessRating(){
		int sum = ratings[0] + ratings[1] + ratings[2];
		double ratingTotal = 0 * ratings[2] + 2.5 * ratings[1] + 5 * ratings[0];
		return ratingTotal / sum;
	}
	
	public double getNurseCommunicationRating(){
		int sum = ratings[3] + ratings[4] + ratings[5];
		double ratingTotal = 0 * ratings[5] + 2.5 * ratings[4] + 5 * ratings[3];
		return ratingTotal / sum;
	}
	
	public double getDoctorCommunicationRating(){
		int sum = ratings[6] + ratings[7] + ratings[8];
		double ratingTotal = 0 * ratings[8] + 2.5 * ratings[7] + 5 * ratings[6];
		return ratingTotal / sum;
	}
	
	public double getHelpRating(){
		int sum = ratings[9] + ratings[10] + ratings[11];
		double ratingTotal = 0 * ratings[11] + 2.5 * ratings[10] + 5 * ratings[9];
		return ratingTotal / sum;
	}
	
	public double getPainControlRating(){
		int sum = ratings[12] + ratings[13] + ratings[14];
		double ratingTotal = 0 * ratings[14] + 2.5 * ratings[13] + 5 * ratings[12];
		return ratingTotal / sum;
	}
	
	public double getQuietnessRating(){
		int sum = ratings[24] + ratings[25] + ratings[26];
		double ratingTotal = 0 * ratings[26] + 2.5 * ratings[25] + 5 * ratings[24];
		return ratingTotal / sum;
	}

	public double getOverallRating() {
		double sum = getCleanlinessRating() + getNurseCommunicationRating() + getDoctorCommunicationRating() + getHelpRating() + getPainControlRating() + getQuietnessRating();
		return sum/6;
	}
	
	public String getDetails(){
		return String.format("Name: " + name + "\nLocation: " + address + ", " + county + ", " + state + "\nOverall Rating: %.2f "
				+ "\n   Cleanliness: %.2f" + "\n   Nurse Communication: %.2f" + "\n   Doctor Communication: %.2f" + "\n   Effective Help: %.2f"
				+ "\n   Pain Control: %.2f" + "\n   Quietness: %.2f", getOverallRating(), getCleanlinessRating(), getNurseCommunicationRating()
				, getDoctorCommunicationRating(), getHelpRating(), getPainControlRating(), getQuietnessRating());
	}

	@Override
	public String toString() {
		return String.format("%-70s%-20s%-30s%-50s%.2f\n", name, state, county, address, getOverallRating());
	}

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
}
