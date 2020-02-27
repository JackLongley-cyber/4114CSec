package doctorsSystem;

import java.util.Scanner;

public class sysAdmin {
	
	static Scanner console = new Scanner(System.in);
	
	public static String display() {
		
		System.out.println("1 - List doctors");
		System.out.println("2 - List patients");
		System.out.println("3 - List drugs prescribed per doctor (or per patient), if access is allowed");
		System.out.println("4 - List previous/future patient visits (including date and ailments).");
		System.out.println("5 - Add/edit/remove doctors/patients");
		System.out.println("6 - Add/change/remove visits");
		System.out.println("Q - Logout");
		System.out.println("Pick an option");
		String answer = console.next();
		return answer;
		
	}
}
