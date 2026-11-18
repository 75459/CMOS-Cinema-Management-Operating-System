package cinema;

public class PasswordManager {
    private String password;


    public PasswordManager(String password){
        if (password != null && password.length() > 0)
        {
            this.password = password;
        }
    }

    public boolean checkPassword(String value){
        if (value != null && password.length() > 0)
        {
            if (value.equals(password))
            {
                return true;
            }
        }
        else
        {
            return false;
        }
        return false;
    }
}
