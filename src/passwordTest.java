import java.util.InputMismatchException;
import java.util.Scanner;
public class passwordTest{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int length = 0;
        System.out.print("Are you going to input your own password, or do you want us to generate one?\n\"1\" for your own, \"2\" to generate: ");
        int mode = scanner.nextInt();
        String password = null;
        if(mode == 1) {
        	while(true) {
        		System.out.print("Enter your new password: ");
            	password = scanner.next();
            	if(password.length() > 64 || password.length() < 6) {
            		System.out.println("Password must be greater than 6 characters and less than 64 characters.");
            		System.exit(0);
            	}
            	System.out.print("Verify new password: ");
            	String verify = scanner.next();
            	if(!verify.contentEquals(password)) {
            		System.out.println("Passwords do not match, please try again.");
            		continue;
            	}
            	else break;
        	}
        }
        else {
        	System.out.print("How long do you want the password to be?  No more than 64 characters.: ");
            try{
                length = scanner.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Please enter a valid input.");
                System.exit(0);
            }
            password = passwordGenerator.generate(length); //will exit if length > 64 or length < 6
        }
        Password test = new Password(password);
        System.out.print("Original password: " + test.originalPassword + "\n");
        System.out.print("Password salt: " + test.pwSalt + "\n");
        System.out.print("Seasoned passwod: " + test.saltedPassword + "\n");
        System.out.println("Hashed as: " + test.hashedPassword);
        scanner.close();
    }
}