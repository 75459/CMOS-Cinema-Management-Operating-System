# CMOS — Cinema Management Operating System

> **Academic Project:** Console-based Java application developed as a credit assignment for the **Object-Oriented Programming** university course.

> **Projekt zaliczeniowy:** Konsolowa aplikacja w języku Java zrealizowana w ramach zaliczenia przedmiotu **Programowanie Obiektowe**.

A console-based Java application simulating internal management software for a multi-screen cinema. The system separates access into two user profiles (**Employee** and password-protected **Manager**) and handles daily cinema operations such as ticket sales, seat reservations, screening schedules, room status tracking, staff records, and revenue reporting.

---

## Features

### Employee Mode
* **Internal Memos (Read-Only):** View announcements posted by management.
* **Ticket Sales:** Browse screenings sorted chronologically, choose ticket type (`NORMAL` — 25.0 PLN, `REDUCED` — 18.0 PLN), verify available seats, sell tickets, and automatically log revenue.
* **Seat Reservations:** Reserve available seats, cancel existing reservations, or process payment for a reservation (converting it into a sold ticket and recording revenue).
* **Screening Schedule Management:**
  * Create new screenings (automatically blocked if the selected room is marked as dirty or inactive).
  * Delete existing screenings.
  * Modify screening details (date/time, assigned movie, or room).
  * View the schedule sorted chronologically by date or by popularity (tickets sold).
* **Room Status Management:** View all rooms and update their cleanliness (`clean`) and operational status (`active`).

### Manager Mode (Admin)
*Access requires authentication via `PasswordManager` (default password: **`123`**).*
* **Memos Management:** View, publish, and delete internal announcements for employees.
* **Movie Repertoire:** View available movies, add new titles (with automatic duplicate prevention), and remove movies.
* **Room Configuration:** View rooms, add new screening rooms with custom capacity, and remove rooms.
* **Staff Management:** View all personnel, filter exclusively by employees or managers, and hire new employees or managers.
* **Financial & System Statistics:** Check total ticket revenue and total cinema entities created during the session.

---

## Project Structure

The codebase is organized into two packages to separate business logic from the console interface:

```text
src/
├── cinema/                         # Business logic, data models, interfaces & exceptions
│   ├── CinemaEntity.java           # Abstract base class for cinema objects + generic array formatter
│   ├── Person.java                 # Abstract base class for staff members
│   ├── Film.java                   # Movie model (overrides toString, equals, hashCode)
│   ├── Room.java                   # Cinema room model (capacity, clean & active flags)
│   ├── Screening.java              # Screening model (implements Comparable<Screening>)
│   ├── Memo.java                   # Employee announcement model
│   ├── Employee.java               # Operational staff model (extends Person)
│   ├── Manager.java                # Management staff model (extends Person)
│   ├── Displayable.java            # Interface enforcing getAllInfo() across all managers
│   ├── ScreeningSeatsComparator.java # Comparator sorting screenings by sold seats
│   ├── TicketType.java             # Enum defining NORMAL and REDUCED tickets
│   ├── CinemaException.java        # Custom exception for cinema business rule violations
│   ├── FilmManager.java            # Movie repertoire CRUD & duplicate validation
│   ├── RoomManager.java            # Room storage & status updates
│   ├── ScreeningManager.java       # Screenings, ticketing, reservations, revenue & sorting
│   ├── MemoManager.java            # Internal memos storage & management
│   ├── EmployeeManager.java        # Staff storage & role-based filtering
│   └── PasswordManager.java        # Admin password verification
└── app/
    └── Main.java                   # Entry point, console menus, input validation & seed data
```

---

## Key OOP Concepts Applied

* **Encapsulation:** Private/protected fields across all classes; package-private constructors, getters, and setters on data models so `app.Main` interacts strictly through public manager methods.
* **Abstraction & Inheritance:** Two class hierarchies built on abstract classes (`CinemaEntity` and `Person`).
* **Polymorphism (Upcasting & Downcasting):** Storing `Employee` and `Manager` instances in a single `ArrayList<Person>`, passing managers as `Displayable` to `Main.showList()`, and safe downcasting with `instanceof` when filtering staff by role.
* **Custom Interface:** `Displayable` implemented across all five manager classes.
* **Dual Sorting:** `Screening` objects sorted via `Arrays.sort()` using both `Comparable` (chronological order) and `Comparator` (popularity by sold seats).
* **Generics, Collections & Enums:** Generic static method `CinemaEntity.formatArray(T[])`, `ArrayList` and `HashMap<Integer, Double>` for per-screening revenue tracking, and `TicketType` enum.
* **Exception Handling:** Custom checked `CinemaException` for domain errors (dirty/inactive rooms, sold-out screenings, invalid IDs) and `NumberFormatException` handling for safe console input.

---

## Getting Started

1. Clone the repository and open the project in IntelliJ IDEA (or any Java IDE).
2. Run `app.Main`.
3. On startup, the application automatically loads sample movies, rooms, memos, staff members, and screenings (`seedInitialData()`) so you can test all features right away.
4. Use **`123`** when prompted for the Manager password.
