import java.lang.Math;
import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
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

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException 
    {  
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
  
        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    
    public static String toHexString(byte[] hash) 
    { 
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash);  
  
        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        // Pad with leading zeros 
        while (hexString.length() < 32)  hexString.insert(0, '0');  
  
        return hexString.toString();  
    } 
    
    public static String hashSeasoned(String seasonedPw) {
    	String toReturn = null;
    	try {
    		toReturn = toHexString(getSHA(seasonedPw));
    	}
    	catch(NoSuchAlgorithmException e){
    		System.out.println("Incorrect algorithm");
    	}
    	return toReturn;
    }
}