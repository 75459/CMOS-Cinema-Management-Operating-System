package cinema;

public class Screening {
    private int screeningId;
    private String date; // YYYY-MM-D HH:MM / example: 2026-11-20 15:30
    private int filmId;
    private int roomId;
    private int boughtSeats;
    private int reservedSeats;

    Screening(int screeningId, String date, int  filmId, int roomId){
        this.screeningId = screeningId;
        this.date = date;
        this.filmId = filmId;
        this.roomId = roomId;
        boughtSeats = 0;
        reservedSeats = 0;
    }

    int getId(){
        return screeningId;
    }
    String getDate(){
        return date;
    }
    int getFilmId(){
        return filmId;
    }
    int getRoomId(){
        return roomId;
    }
    int getBoughtSeats(){
        return boughtSeats;
    }
    int getReservedSeats(){
        return reservedSeats;
    }


    void setDate(String date){
        this.date = date;
    }
    void setFilmId(int filmId){
        this.filmId = filmId;
    }
    void setRoomId(int roomId){
        this.roomId = roomId;
    }
    void setBoughtSeats(int boughtSeats){
        this.boughtSeats = boughtSeats;
    }
    void setReservedSeats(int reservedSeats){
        this.reservedSeats = reservedSeats;
    }

}
