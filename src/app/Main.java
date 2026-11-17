import cinema.PasswordManager;

import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
void main() {

    while(true)
    {
        drawStart();
        String choice = scanner.nextLine();
        PasswordManager pass = new PasswordManager();

        switch(choice)
        {
            case "1":
                menuEmp();
                break;
            case "2":
                System.out.println("Podawanie danych dostępu dla zarządcy");
                String adminPassword = scanner.nextLine();
                if (pass.checkPassword(adminPassword))
                {
                    menuAdmin();
                }
                else
                {
                    System.out.println("Nieprawidłowe hasło!");
                }
            case "0":
                break;
            default:
                break;
        }
        break;
    }
}

void menuAdmin() {
    System.out.println("actuall admin menu");
}

void menuEmp() {
    System.out.println("actuall menu pracownika");
}


// ----------- DRAW FUNCTIONS ------------------

static void drawStart() {
    System.out.println("-------------------- CMOS -------------------------------");
    System.out.println("Wybierz profil:");
    System.out.println("[1] - pracownik");
    System.out.println("[2] - Zarządca");
    System.out.println("[0] - wyjdz");
}

static void drawAdmin() {

}

static void drawEmp() {

}






