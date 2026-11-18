package cinema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ScreeningManager {
    private ArrayList<Screening> screeningsList = new ArrayList<Screening>();
    private static double[] ticketPrices = {25.0, 18.0}; // [0] = normal, [1] = kids
    private HashMap<Integer, Double> screeningRevenue = new HashMap<>();
    public ScreeningManager() {}


    public static double getTicketPrice(TicketType type) {
        if (type != null && type.ordinal() < ticketPrices.length) {
            return ticketPrices[type.ordinal()];
        }
        return -1;
    }

    public void registerTicketSale(int screeningId, TicketType type) {
        double price = getTicketPrice(type);
        if (price > 0) {
            double current = screeningRevenue.getOrDefault(screeningId, 0.0);
            screeningRevenue.put(screeningId, current + price);
        }
    }

    public double getTotalRevenue() {
        double total = 0.0;
        for (double amount : screeningRevenue.values()) {
            total += amount;
        }
        return total;
    }

    // --------------------------------- USEFUL ---------------------------------
    private Screening getScreeningObjById(int id) {
        for (Screening item : screeningsList) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    private boolean checkId(int id) {
        for (Screening item : screeningsList) {
            if (item.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private int getLastId() {
        if (screeningsList.size() == 0) {
            return 0;
        } else {
            return screeningsList.get(screeningsList.size() - 1).getId();
        }
    }

    private int getAvailableSeats(RoomManager roomManagerObj, int screeningId) {
        int roomId = getRoomId(screeningId);
        int roomSeats = roomManagerObj.getRoomCountById(roomId);
        if (roomSeats >= 0) {
            return roomSeats - getBoughtSeats(screeningId) - getReservedSeats(screeningId);
        }
        return -1;
    }

    public Screening[] getScreeningsArray() {
        Screening[] array = new Screening[screeningsList.size()];
        for (int i = 0; i < screeningsList.size(); i++) {
            array[i] = screeningsList.get(i);
        }
        return array;
    }

    // -------------------------------------------------------------------------

    public void createScreening(String date, int filmId, int roomId) {
        Screening screening = new Screening(getLastId() + 1, date, filmId, roomId);
        screeningsList.add(screening);
    }

    public void deleteScreening(int id) {
        if (checkId(id)) {
            screeningsList.remove(getScreeningObjById(id));
        }
    }

    public void sellSeat(int screeningId, RoomManager roomManagerObj) throws CinemaException {
        if (!checkId(screeningId)) {
            throw new CinemaException("Nie znaleziono seansu o podanym ID.");
        }
        int availableSeats = getAvailableSeats(roomManagerObj, screeningId);
        if (availableSeats <= 0) {
            throw new CinemaException("Brak wolnych miejsc na ten seans.");
        }
        setBoughtSeats(screeningId, getBoughtSeats(screeningId) + 1);
    }

    public void reserveSeat(int screeningId, RoomManager roomManagerObj) throws CinemaException {
        if (!checkId(screeningId)) {
            throw new CinemaException("Nie znaleziono seansu o podanym ID.");
        }
        int availableSeats = getAvailableSeats(roomManagerObj, screeningId);
        if (availableSeats <= 0) {
            throw new CinemaException("Brak wolnych miejsc do rezerwacji.");
        }
        setReservedSeats(screeningId, getReservedSeats(screeningId) + 1);
    }

    public void cancelReservation(int screeningId) throws CinemaException {
        if (!checkId(screeningId)) {
            throw new CinemaException("Nie znaleziono seansu o podanym ID.");
        }
        if (getReservedSeats(screeningId) <= 0) {
            throw new CinemaException("Ten seans nie ma żadnych aktywnych rezerwacji.");
        }
        setReservedSeats(screeningId, getReservedSeats(screeningId) - 1);
    }

    public void payReservation(int screeningId) throws CinemaException {
        if (!checkId(screeningId)) {
            throw new CinemaException("Nie znaleziono seansu o podanym ID.");
        }
        if (getReservedSeats(screeningId) <= 0) {
            throw new CinemaException("Brak rezerwacji do opłacenia na tym seansie.");
        }
        setReservedSeats(screeningId, getReservedSeats(screeningId) - 1);
        setBoughtSeats(screeningId, getBoughtSeats(screeningId) + 1);
    }

    // ---------------- GET ----------------
    public String getDate(int id) {
        if (checkId(id)) {
            return getScreeningObjById(id).getDate();
        }
        return "Coś poszło nie tak!";
    }

    public int getFilmId(int id) {
        if (checkId(id)) {
            return getScreeningObjById(id).getFilmId();
        }
        return -1;
    }

    public int getRoomId(int id) {
        if (checkId(id)) {
            return getScreeningObjById(id).getRoomId();
        }
        return -1;
    }

    public int getBoughtSeats(int id) {
        if (checkId(id)) {
            return getScreeningObjById(id).getBoughtSeats();
        }
        return -1;
    }

    public int getReservedSeats(int id) {
        if (checkId(id)) {
            return getScreeningObjById(id).getReservedSeats();
        }
        return -1;
    }

    // ---------------- SET ----------------
    public void setDate(int id, String newDate) {
        if (checkId(id)) {
            getScreeningObjById(id).setDate(newDate);
        }
    }

    public void setFilmId(int id, int newFilmId) {
        if (checkId(id)) {
            getScreeningObjById(id).setFilmId(newFilmId);
        }
    }

    public void setRoomId(int id, int newRoomId) {
        if (checkId(id)) {
            getScreeningObjById(id).setRoomId(newRoomId);
        }
    }

    public void setBoughtSeats(int id, int newBoughtSeats) {
        if (checkId(id)) {
            getScreeningObjById(id).setBoughtSeats(newBoughtSeats);
        }
    }

    public void setReservedSeats(int id, int newReservedSeats) {
        if (checkId(id)) {
            getScreeningObjById(id).setReservedSeats(newReservedSeats);
        }
    }

    public String getScreeningsSortedByDate() {
        Screening[] array = getScreeningsArray();
        if (array.length == 0) {
            return "Brak seansów w bazie.";
        }
        Arrays.sort(array);

        String result = "";
        for (Screening item : array) {
            result = result + item.toString() + "\n";
        }
        return result;
    }

    public String getScreeningsSortedByPopularity() {
        Screening[] array = getScreeningsArray();
        if (array.length == 0) {
            return "Brak seansów w bazie.";
        }
        Arrays.sort(array, new ScreeningSeatsComparator());

        String result = "";
        for (Screening item : array) {
            result = result + item.toString() + "\n";
        }
        return result;
    }
}