import java.io.FileNotFoundException;
import java.util.Scanner;

public class Client {
    
	public static Hospital[] hospitals;
	static Scanner in;
	
    public static void main(String[] args) throws FileNotFoundException {
    	in = new Scanner(System.in);
        hospitals = CSVReader.init();
        mainMenu();
        
    }
    
    public static void mainMenu() {
    	boolean done = false;
    	while(!done) {
    		System.out.println("1. Search Hospitals"
    			           + "\n2. Sort Hospitals"
    			           + "\n3. Quit");
    		
    		System.out.print("enter numeric option: ");
    		int input = in.nextInt();
    		
    		switch (input) {
			case 1:
				searchMenu();
				break;
			case 2:
				sortMenu();
				break;
			case 3:
				done = true;
				break;
			default:
				System.out.println("Invalid input, please enter a number 1-3 inclusive");
			}
    	}
    	
    }
    
    public static void sortMenu() {
    	//needs to be implemented
    }
    
    public static void searchMenu() {
    	//needs to be implemented
    }
    
    public static void printHospitals(Hospital[] a) {
    	for (int i = 0; i < a.length; i++) {
			System.out.println(a[i].toString());
		}
    }
}
