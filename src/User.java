public class User{
    Password password;
    String username;
    public User(String username, Password password){
        this.username = username;
        this.password = password;
    }
    public void changePassword(String newPassword){
        this.password = Password.changePassword(newPassword);
    }
}
