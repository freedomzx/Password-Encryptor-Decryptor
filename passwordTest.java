import java.util.InputMismatchException;
import java.util.Scanner;
public class passwordTest{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int length = 0;
        System.out.print("How long do you want the password to be?  No more than 64 characters.: ");
        try{
            length = scanner.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Please enter a valid input.");
            System.exit(0);
        }
        String password = passwordGenerator.generate(length); //will exit if length > 64
        Password test = new Password(password);
        System.out.print("Original password: " + test.originalPassword + "\n");
        System.out.print("Password salt: " + test.pwSalt + "\n");
        System.out.print("Seasoned passwod: " + test.saltedPassword + "\n");
        System.out.println("Hashed as: " + test.hashedPassword);

        scanner.close();
    }
}