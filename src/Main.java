import java.util.Scanner;
import java.io.IOException;

Scanner scanner = new Scanner(System.in);
void main() {

    while(true)
    {
        drawStart();
        String choice = scanner.nextLine();

        switch(choice)
        {
            case "1":
                menuEmp();
                break;
            case "2":
                boolean does = true;
                while(does == true)
                {
                    System.out.println("Podawanie danych dostępu dla zarządcy");
                    String adminPassword = scanner.nextLine();
                    if (adminPassword == "1")
                    {
                        menuAdmin();
                        does = false;
                        break;
                    }
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
    System.out.println("actuall admin menu");
    System.out.println("actuall admin menu");
    System.out.println("actuall admin menu");
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






