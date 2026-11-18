package app;

import cinema.*;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PasswordManager pass = new PasswordManager("123");
    private static final MemoManager memoManager = new MemoManager();
    private static final RoomManager roomManager = new RoomManager();
    private static final FilmManager filmManager = new FilmManager();
    private static final ScreeningManager screeningManager = new ScreeningManager();
    private static final EmployeeManager employeeManager = new EmployeeManager();

    static void main() {
        seedInitialData();

        boolean running = true;
        while (running) {
            drawStart();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    menuEmp();
                    break;
                case "2":
                    System.out.print("Podaj hasło dostępu dla zarządcy (domyślnie 123): ");
                    String adminPassword = scanner.nextLine();
                    if (pass.checkPassword(adminPassword)) {
                        menuAdmin();
                    } else {
                        System.out.println("Nieprawidłowe hasło!");
                    }
                    break;
                case "0":
                    running = false;
                    System.out.println("Zamykanie systemu CMOS...");
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja.");
                    break;
            }
        }
    }

    // ----------- DANE STARTOWE DO SZYBKIEGO TESTU ------------------

    private static void seedInitialData() {
        memoManager.createMemo("Przypomnienie: po każdym seansie należy oznaczyć salę jako wymagającą sprzątania.");
        memoManager.createMemo("Nowy cennik biletów: Normalny 25.0 zł, Ulgowy 18.0 zł.");

        filmManager.createFilm("Diuna: Część Druga");
        filmManager.createFilm("Oppenheimer");
        filmManager.createFilm("Incepcja");

        roomManager.createSala("1A", 120);
        roomManager.createSala("2B", 80);
        roomManager.createSala("VIP", 30);

        employeeManager.createEmployee("Jan", "Kowalski", "Kasjer");
        employeeManager.createEmployee("Anna", "Nowak", "Obsługa sali");
        employeeManager.createManager("Piotr", "Wiśniewski", "Zarząd kina");

        try {
            screeningManager.createScreening("2026-11-20 18:00", 1, 1, roomManager);
            screeningManager.createScreening("2026-11-20 20:30", 2, 2, roomManager);
        } catch (CinemaException e) {
            System.out.println("Błąd inicjalizacji seansów: " + e.getMessage());
        }
    }

    // ----------- MENU GŁÓWNE PROFILI ------------------

    private static void menuEmp() {
        boolean inEmpMenu = true;
        while (inEmpMenu) {
            drawEmp();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    drawMemos();
                    break;
                case "2":
                    drawTicketSale();
                    break;
                case "3":
                    drawReservationsManage();
                    break;
                case "4":
                    drawScreeningManage();
                    break;
                case "5":
                    drawRoomManage();
                    break;
                case "0":
                    inEmpMenu = false;
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja.");
                    break;
            }
        }
    }

    private static void menuAdmin() {
        boolean inAdminMenu = true;
        while (inAdminMenu) {
            drawAdmin();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    drawAdminMemos();
                    break;
                case "2":
                    drawAdminFilms();
                    break;
                case "3":
                    drawAdminRooms();
                    break;
                case "4":
                    drawAdminStaff();
                    break;
                case "5":
                    drawAdminStats();
                    break;
                case "0":
                    inAdminMenu = false;
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja.");
                    break;
            }
        }
    }

    // ----------- EKRANY START / MENU ------------------

    private static void drawStart() {
        System.out.println("\n---------------------------- CMOS -------------------------------");
        System.out.println("Wybierz profil:");
        System.out.println("[1] - Pracownik");
        System.out.println("[2] - Zarządca");
        System.out.println("[0] - Wyjdź");
    }

    private static void drawEmp() {
        System.out.println("\n-------------------------- CMOS - EMPLOYEE - MENU --------------------------");
        System.out.println("Wybierz opcję i zatwierdź Enter:");
        System.out.println("[1] - Ogłoszenia pracownicze");
        System.out.println("[2] - Sprzedaż biletów");
        System.out.println("[3] - Rezerwacje");
        System.out.println("[4] - Harmonogram i zarządzanie seansami");
        System.out.println("[5] - Sale");
        System.out.println("[0] - Wyjdź");
    }

    private static void drawAdmin() {
        System.out.println("\n-------------------------- CMOS - MANAGER - MENU --------------------------");
        System.out.println("Wybierz opcję i zatwierdź Enter:");
        System.out.println("[1] - Zarządzanie ogłoszeniami");
        System.out.println("[2] - Zarządzanie repertuarem (filmy)");
        System.out.println("[3] - Zarządzanie salami");
        System.out.println("[4] - Kadry (pracownicy i zarządcy)");
        System.out.println("[5] - Raport finansowy i statystyki systemu");
        System.out.println("[0] - Wyjdź");
    }

    // ------------ FUNKCJE PRACOWNIKA -----------

    private static void drawMemos() {
        System.out.println("\n--- OGŁOSZENIA PRACOWNICZE ---");
        showList(memoManager);
    }

    private static void drawTicketSale() {
        System.out.println("\n--- SPRZEDAŻ BILETÓW ---");
        System.out.println(screeningManager.getScreeningsSortedByDate());

        int screeningId = readInt("Podaj ID seansu: ");
        int typeChoice = readInt("Typ biletu ([1] - Normalny, [2] - Ulgowy): ");
        TicketType ticketType = (typeChoice == 2) ? TicketType.REDUCED : TicketType.NORMAL;

        try {
            screeningManager.sellSeat(screeningId, roomManager);
            screeningManager.registerTicketSale(screeningId, ticketType);
            System.out.println("Sprzedano bilet (" + ticketType + ") za " + ScreeningManager.getTicketPrice(ticketType) + " zł.");
        } catch (CinemaException e) {
            System.out.println("Błąd sprzedaży: " + e.getMessage());
        }
    }

    private static void drawReservationsManage() {
        System.out.println("\n--- ZARZĄDZANIE REZERWACJAMI ---");
        showList(screeningManager);

        System.out.println("[1] - Dodaj rezerwację");
        System.out.println("[2] - Anuluj rezerwację");
        System.out.println("[3] - Opłać rezerwację");
        System.out.println("[0] - Powrót");
        String action = scanner.nextLine();

        if (action.equals("0")) {
            return;
        }

        int screeningId = readInt("Podaj ID seansu: ");
        try {
            switch (action) {
                case "1":
                    screeningManager.reserveSeat(screeningId, roomManager);
                    System.out.println("Miejsce zostało zarezerwowane.");
                    break;
                case "2":
                    screeningManager.cancelReservation(screeningId);
                    System.out.println("Rezerwacja została anulowana.");
                    break;
                case "3":
                    int typeChoice = readInt("Typ opłacanego biletu ([1] - Normalny, [2] - Ulgowy): ");
                    TicketType ticketType = (typeChoice == 2) ? TicketType.REDUCED : TicketType.NORMAL;
                    screeningManager.payReservation(screeningId);
                    screeningManager.registerTicketSale(screeningId, ticketType);
                    System.out.println("Rezerwacja opłacona (" + ScreeningManager.getTicketPrice(ticketType) + " zł).");
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja.");
                    break;
            }
        } catch (CinemaException e) {
            System.out.println("Błąd operacji: " + e.getMessage());
        }
    }

    private static void drawScreeningManage() {
        System.out.println("\n--- SEANSE ---");
        System.out.println("[1] - Utwórz nowy seans");
        System.out.println("[2] - Usuń seans");
        System.out.println("[3] - Modyfikuj seans");
        System.out.println("[4] - Wyświetl harmonogram");
        System.out.println("[0] - Powrót");
        String action = scanner.nextLine();

        switch (action) {
            case "1":
                System.out.println("Dostępne filmy:");
                System.out.println(CinemaEntity.formatArray(filmManager.getFilmsArray()));
                System.out.println("Dostępne sale:");
                showList(roomManager);

                int filmId = readInt("Podaj ID filmu: ");
                int roomId = readInt("Podaj ID sali: ");
                System.out.print("Podaj datę i godzinę (np. 2026-11-20 18:30): ");
                String date = scanner.nextLine();

                try {
                    screeningManager.createScreening(date, filmId, roomId, roomManager);
                    System.out.println("Seans został utworzony.");
                } catch (CinemaException e) {
                    System.out.println("Błąd: " + e.getMessage());
                }
                break;

            case "2":
                showList(screeningManager);
                int deleteId = readInt("Podaj ID seansu do usunięcia: ");
                screeningManager.deleteScreening(deleteId);
                System.out.println("Zakończono operację usuwania seansu.");
                break;

            case "3":
                showList(screeningManager);
                int editId = readInt("Podaj ID seansu do modyfikacji: ");
                System.out.println("[1] - Zmień datę  [2] - Zmień film  [3] - Zmień salę");
                String editChoice = scanner.nextLine();

                switch (editChoice) {
                    case "1":
                        System.out.print("Nowa data (YYYY-MM-DD HH:MM): ");
                        screeningManager.setDate(editId, scanner.nextLine());
                        break;
                    case "2":
                        System.out.println(CinemaEntity.formatArray(filmManager.getFilmsArray()));
                        screeningManager.setFilmId(editId, readInt("Nowe ID filmu: "));
                        break;
                    case "3":
                        showList(roomManager);
                        screeningManager.setRoomId(editId, readInt("Nowe ID sali: "));
                        break;
                    default:
                        System.out.println("Nieprawidłowa opcja.");
                        return;
                }
                System.out.println("Zakończono aktualizację seansu.");
                break;

            case "4":
                System.out.println("[1] - Chronologicznie (wg daty)  [2] - Wg popularności (sprzedane bilety)");
                String sortChoice = scanner.nextLine();
                if (sortChoice.equals("2")) {
                    System.out.println(screeningManager.getScreeningsSortedByPopularity());
                } else {
                    System.out.println(screeningManager.getScreeningsSortedByDate());
                }
                break;

            case "0":
                break;

            default:
                System.out.println("Nieprawidłowa opcja.");
                break;
        }
    }

    private static void drawRoomManage() {
        System.out.println("\n--- ZARZĄDZANIE SALAMI ---");
        showList(roomManager);

        int roomId = readInt("Podaj ID sali do zmiany statusu: ");
        System.out.println("[1] - Zmień status czystości (clean)");
        System.out.println("[2] - Zmień status sprawności (active)");
        String action = scanner.nextLine();

        if (action.equals("1")) {
            int val = readInt("Czy sala jest czysta? ([1] - Tak, [2] - Nie): ");
            roomManager.setClean(roomId, val == 1);
            System.out.println("Zakończono zmianę statusu czystości.");
        } else if (action.equals("2")) {
            int val = readInt("Czy sala jest sprawna? ([1] - Tak, [2] - Nie): ");
            roomManager.setActive(roomId, val == 1);
            System.out.println("Zakończono zmianę statusu sprawności.");
        } else {
            System.out.println("Nieprawidłowa opcja.");
        }
    }

    // ------------ FUNKCJE ZARZĄDCY -----------

    private static void drawAdminMemos() {
        System.out.println("\n--- ZARZĄDZANIE OGŁOSZENIAMI ---");
        showList(memoManager);
        System.out.println("[1] - Dodaj ogłoszenie  [2] - Usuń ogłoszenie  [0] - Powrót");
        String action = scanner.nextLine();

        if (action.equals("1")) {
            System.out.print("Treść ogłoszenia: ");
            memoManager.createMemo(scanner.nextLine());
            System.out.println("Dodano ogłoszenie.");
        } else if (action.equals("2")) {
            int id = readInt("Podaj ID ogłoszenia do usunięcia: ");
            memoManager.deleteMemo(id);
            System.out.println("Zakończono usuwanie ogłoszenia.");
        }
    }

    private static void drawAdminFilms() {
        System.out.println("\n--- ZARZĄDZANIE REPERTUAREM ---");
        showList(filmManager);
        System.out.println("[1] - Dodaj film  [2] - Usuń film  [0] - Powrót");
        String action = scanner.nextLine();

        if (action.equals("1")) {
            System.out.print("Tytuł filmu: ");
            boolean added = filmManager.createFilm(scanner.nextLine());
            System.out.println(added ? "Dodano film do repertuaru." : "Taki film już istnieje w bazie!");
        } else if (action.equals("2")) {
            int id = readInt("Podaj ID filmu do usunięcia: ");
            filmManager.deleteFilm(id);
            System.out.println("Zakończono usuwanie filmu.");
        }
    }

    private static void drawAdminRooms() {
        System.out.println("\n--- KONFIGURACJA SAL KINOWYCH ---");
        showList(roomManager);
        System.out.println("[1] - Dodaj salę  [2] - Usuń salę  [0] - Powrót");
        String action = scanner.nextLine();

        if (action.equals("1")) {
            System.out.print("Numer/nazwa sali (np. 1A): ");
            String number = scanner.nextLine();
            int seats = readInt("Liczba miejsc: ");
            roomManager.createSala(number, seats);
            System.out.println("Dodano salę.");
        } else if (action.equals("2")) {
            int id = readInt("Podaj ID sali do usunięcia: ");
            roomManager.deleteSala(id);
            System.out.println("Zakończono usuwanie sali.");
        }
    }

    private static void drawAdminStaff() {
        System.out.println("\n--- KADRY ---");
        System.out.println("[1] - Lista wszystkich  [2] - Tylko pracownicy  [3] - Tylko zarządcy");
        System.out.println("[4] - Dodaj pracownika  [5] - Dodaj zarządcę  [0] - Powrót");
        String action = scanner.nextLine();

        switch (action) {
            case "1":
                showList(employeeManager);
                break;
            case "2":
                System.out.println(employeeManager.getStaffByRole(false));
                break;
            case "3":
                System.out.println(employeeManager.getStaffByRole(true));
                break;
            case "4":
                System.out.print("Imię: ");
                String empFirst = scanner.nextLine();
                System.out.print("Nazwisko: ");
                String empLast = scanner.nextLine();
                System.out.print("Stanowisko: ");
                String pos = scanner.nextLine();
                employeeManager.createEmployee(empFirst, empLast, pos);
                System.out.println("Dodano pracownika.");
                break;
            case "5":
                System.out.print("Imię: ");
                String mgrFirst = scanner.nextLine();
                System.out.print("Nazwisko: ");
                String mgrLast = scanner.nextLine();
                System.out.print("Dział: ");
                String dept = scanner.nextLine();
                employeeManager.createManager(mgrFirst, mgrLast, dept);
                System.out.println("Dodano zarządcę.");
                break;
            default:
                break;
        }
    }

    private static void drawAdminStats() {
        System.out.println("\n--- STATYSTYKI SYSTEMU CMOS ---");
        System.out.println("Łączny utarg ze sprzedaży biletów: " + screeningManager.getTotalRevenue() + " zł");
        System.out.println("Liczba utworzonych encji kinowych w sesji: " + CinemaEntity.getEntityCount());
    }

    // ------------ POMOCNICZE ------------------

    private static void showList(Displayable manager) {
        System.out.println(manager.getAllInfo());
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Błąd: podaj poprawną liczbę całkowitą!");
            }
        }
    }
}