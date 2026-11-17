package cinema;

public class PasswordManager {
    private String password;

    public PasswordManager(){}

    public PasswordManager(String password){
        this.password = password;
    }

    public boolean checkPassword(String value){
        if (value == password)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
