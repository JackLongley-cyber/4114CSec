package doctorsSystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Scanner;
import java.io.File;


public class main 
{
	static Scanner console = new Scanner(System.in);
	static int credentials = 0;
	static String error = "";
	static String key = "s6v9y$B&E)H@McQe";
	
	public static void main(String[] args)
	{	
		int i = 0;
		String file = "";
		while(i != 1) {
			String answer = displayMainMenu();
			if (answer.equals("1")) {
				try {
					String username = userLogin();
					if(username.equals("")) {
						error = "No Such Account Exists";
					}
					else {

					}
				} 
				catch (Exception ex) 
		        {
		            System.out.println(ex.getMessage());
		            ex.printStackTrace();
		        }
			}
			else if (answer.equals("2")) {
				file = whichFile();
				try {
					encrypt(file);
					error = "Success";
				} 
				catch (Exception ex) 
		        {
		            System.out.println(ex.getMessage());
		            ex.printStackTrace();
		        }
			}
			else if (answer.equals("3")) 
			{
				file = whichFile();
				try {
					decrypt(file);
					error = "Success";
				} 
				catch (Exception ex) 
		        {
		            System.out.println(ex.getMessage());
		            ex.printStackTrace();
		        }
			}
			else if (answer.equals("Q") || answer.equals("q")) {
				i++;
			}
			else {
				System.out.println("Please Enter a valid option!");
			}
		}
		console.close();
	}

	public static String displayMainMenu()
	{
		String answer;
		
		System.out.println("");
		System.out.println("- - Doctor/Patient System - -");
		System.out.println(error);
		System.out.println("- - MAIN MENU - -");
		System.out.println("1 - Login");
		System.out.println("2 - Encyrpt");
		System.out.println("3 - Decyrpt");
		System.out.println("Q - Quit");
		System.out.println("Pick an option");
		error = "";
		answer = console.next();
		console.nextLine();
		return answer;
	}
	
	private static String userLogin() throws FileNotFoundException {
		String inputUsername = console.next();
		String inputPassword = console.next();
		console.nextLine();
		if(checkInput(inputUsername, inputPassword) == true) {
			return inputUsername;
		}
		else {
			return "";
		}
		
	}
	
	public static boolean checkInput(String inputUsername,String inputPassword) throws FileNotFoundException {
		String fileName = ("data\\users.txt");
		FileReader file = new FileReader(fileName);
		Scanner read = new Scanner(file);
		while(read.hasNext() != false) 
		{
			if(read.next().equals(inputUsername)) {
				if(read.next().equals(hashPassword(inputPassword))) {
					credentials = read.nextInt();
					System.out.print(true);
					return true;
				}
			}
		}
		read.close();
		return false;
	}
	
	public static String hashPassword(String inputPassword) {
		String hashedPassword = null;
        try {
            //Create a MessageDigest instance for SHA-512
            MessageDigest sha = MessageDigest.getInstance("SHA-512");
            //Add password bytes to digest
            sha.update(inputPassword.getBytes());
            //Get the hash's bytes 
            byte[] hashBytes = sha.digest();
            //bytes[] contains bytes in decimal format;
            //Convert it to hexadecimal
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< hashBytes.length ;i++)
            {
            	sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
            	//Integer.toString(src, 16) converts the integer to a hexadecimal
                //& 0xFF performs a binary AND, causing the returning value to be between 0 and 255(FF is 255 in hexidecimal)
            	//+ 0x100 adds 256 to the result to ensure the result is always 3 digits
            	//Finally .substring(1) strips the first character (the 1 from step 2)
            }
            //Get complete hashed password in hexadecimal format
            hashedPassword = sb.toString();
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return hashedPassword;
	}
	
	public static String whichFile()
	{
		String answer = "0";
		
		System.out.println("");
		System.out.println("- - Doctor/Patient System - -");
		System.out.println(error);
		System.out.println("- - FILE MENU - -");
		System.out.println("1 - Patient");
		System.out.println("2 - Doctor");
		System.out.println("3 - SysAdmin");
		System.out.println("Pick an option");
		answer = console.next();
		console.nextLine();
		if(answer.equals("1")) {
			return "patient";
		}
		else if(answer.equals("2")) {
			return "doctor";
		}
		else if(answer.equals("3")) {
			return "sysAdmin";
		}
		return "";
	}
	
	public static void encrypt(String userArea) throws IOException {
        
		File file = new File("data\\usersStorage\\"+ userArea +".txt");
         
        try {
        	encyrption.encrypt(file);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
	}
	
	public static void decrypt(String userArea) throws IOException {
        
		File file = new File("data\\usersStorage\\"+ userArea +".txt");
         
        try {
        	encyrption.decrypt(file);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
	}
}
