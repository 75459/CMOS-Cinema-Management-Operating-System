package cinema;

public class PasswordManager {
    private String password;

    public PasswordManager(){
        password = "123";
    }

    public PasswordManager(String password){
        this(); // fires a empty constructor (password == 123)
        if (password != null && !password.isBlank())
        {
            this.password = password;
        }
    }

    public boolean checkPassword(String value){
        if (value != null && !value.isBlank())
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
