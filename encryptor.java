import java.lang.Math;
import java.util.*;

public class encryptor{

    static char translations[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', '!', '?' };

    public static String salt(String password) {
        int saltLength = password.length();
        String salt = "";
        for (int i = 0; i < saltLength; i++) {
            int generated = (int) (Math.random() * ((126 - 33) + 1)) + 33;
            char insertThis = (char) generated;
            salt = salt + insertThis;
        }
        return salt;
    }

    public static String season(String password, String salt) {
        String saltedPassword = "";
        for (int i = 0; i < password.length(); i++) {
            if (i % 2 == 0)
                saltedPassword = saltedPassword + password.charAt(i) + salt.charAt(i);
            else
                saltedPassword = saltedPassword + salt.charAt(i) + password.charAt(i);
        }
        return saltedPassword;
    }

    public static String hashSeasoned(String saltedPassword) {
        String hashed = "";
        String binary = "";
        for (int i = 0; i < saltedPassword.length(); i++) {
            int ascii = (int) saltedPassword.charAt(i);
            String tempBinary = toBinary(ascii);
            if(tempBinary.length() < 8){
                while(tempBinary.length() != 8){
                    tempBinary = "0" + tempBinary;
                }
            }
            binary = binary + tempBinary;
        } //converts each char in salted to a binary of 8 bits and stores it within a long string of binary
        for (int i = 0; i < binary.length(); i += 6) {
            String temp = "";
            if (i + 6 > binary.length()) {
                int remaining = binary.length() - i;
                temp = binary.substring(i, i + remaining);
            } 
            else temp = binary.substring(i, i + 6);
            //System.out.println(temp);
            char toAdd = convert64(temp);
            hashed = hashed + toAdd;
        } //converts every 6 bits of binary to decimal, which determine its placing in base64 dictionary
        return hashed;
    }

    public static char convert64(String str) {
        int parsed = Integer.parseInt(str, 2);
        return translations[parsed];
    }
    
    public static String toBinary(int number){
        String toReturn = "";
        Stack<Integer> binaryStack = new Stack<Integer>();
        while(number > 0){
            binaryStack.push(number % 2);
            number /= 2;
        }
        while(!binaryStack.isEmpty()){
            toReturn = toReturn + binaryStack.pop();
        }
        return toReturn;
    }
}