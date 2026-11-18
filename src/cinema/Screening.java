package cinema;

public class Screening extends CinemaEntity {
    private String date; // YYYY-MM-DD HH:MM / example: 2026-11-20 15:30
    private int filmId;
    private int roomId;
    private int boughtSeats;
    private int reservedSeats;

    Screening(int screeningId, String date, int filmId, int roomId) {
        super(screeningId);
        this.date = date;
        this.filmId = filmId;
        this.roomId = roomId;
        boughtSeats = 0;
        reservedSeats = 0;
    }

    String getDate() {
        return date;
    }

    int getFilmId() {
        return filmId;
    }

    int getRoomId() {
        return roomId;
    }

    int getBoughtSeats() {
        return boughtSeats;
    }

    int getReservedSeats() {
        return reservedSeats;
    }

    void setDate(String date) {
        this.date = date;
    }

    void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    void setBoughtSeats(int boughtSeats) {
        this.boughtSeats = boughtSeats;
    }

    void setReservedSeats(int reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    @Override
    String getInfo() {
        return id + ". Data: " + date + " | Film ID: " + filmId + " | Sala ID: " + roomId
                + " | Kupione: " + boughtSeats + " | Zarezerwowane: " + reservedSeats;
    }
}