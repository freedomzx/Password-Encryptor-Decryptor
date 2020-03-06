public class Password{
    public String originalPassword;
    public String pwSalt;
    public String saltedPassword;
    public String hashedPassword;
    public Password(String password){
        originalPassword = password;
        pwSalt = encryptor.salt(password);
        saltedPassword = encryptor.season(password, pwSalt);
        hashedPassword = encryptor.hashSeasoned(saltedPassword);
    }
    public static Password changePassword(String newPassword){
        Password toReturn = new Password(newPassword);
        return toReturn;
    }
}