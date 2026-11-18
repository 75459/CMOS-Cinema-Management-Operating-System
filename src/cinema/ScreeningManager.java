package cinema;

import java.util.ArrayList;

public class ScreeningManager {
    private ArrayList<Screening> screeningsList = new ArrayList<Screening>();

    public ScreeningManager(){}
    public ScreeningManager(ArrayList<Screening> screeningsList){
        if (screeningsList != null)
        {
            this.screeningsList = screeningsList;
        }
    }

    // --------------------------------- USEFUL ---------------------------------
    private Screening getScreeningObjById(int id){ // self-explanatory pretty much
        for (Screening item : screeningsList)
        {
            if (item.getId() == id){
                return item;
            }
        }
        return null;
    }

    private boolean checkId(int id){ // check whether Screening with that id exists
        for (Screening item : screeningsList){
            if (item.getId() == id)
            {
                return true;
            }
        }
        return false;
    }

    private int getLastId(){ // returns last used id (for creating new Screening)
        if (screeningsList.size() == 0)
        {
            return 0;
        }
        else
        {
            return screeningsList.get(screeningsList.size()-1).getId();
        }
    }

    private int getAvabileSeats(int roomId, RoomManager roomManagerObj, int screeningId){
        int roomSeats = roomManagerObj.getRoomCountById(roomId);
        if (roomSeats >= 0){
            return roomSeats - getBoughtSeats(screeningId);
        }
        return -1;
    }

    // -------------------------------------------------------------------------

    public void createScreening(String date, int  filmId, int roomId, int boughtSeats, int reservedSeats){
        Screening screening = new Screening(getLastId()+1, date, filmId, roomId);
        screeningsList.add(screening);
    }

    public void deleteScreening(int id){
        if (checkId(id)){
            screeningsList.remove(getScreeningObjById(id));
        }
    }

    public boolean sellSeat(int roomId, RoomManager roomManagerObj, int screeningId){
        int availableSeats = getAvabileSeats(roomId, roomManagerObj, screeningId) - getReservedSeats(screeningId);
        if (availableSeats >= 0){
            if (availableSeats == 0){
                return false;
            }
            else if (availableSeats > 0){
                setBoughtSeats(screeningId, getBoughtSeats(screeningId) + 1);
                return true;
            }
        }
        return false;
    }
    public boolean reserveSeat(int roomId, RoomManager roomManagerObj, int screeningId){
        int availableSeats2 = getAvabileSeats(roomId, roomManagerObj, screeningId);
        if (availableSeats2 >= 0){
            if (availableSeats2 == 0){
                return false;
            }
            else if (availableSeats2 > 0){
                setReservedSeats(screeningId, getReservedSeats(screeningId) + 1);
                return true;
            }
        }
        return false;
    }
    public void cancelReservation(int screeningId){
        if (getReservedSeats(screeningId) > 0){
            setReservedSeats(screeningId, getReservedSeats(screeningId)-1);
        }
    }
    public void payReservation(int screeningId,int roomId, RoomManager roomManagerObj){
        cancelReservation(screeningId);
        sellSeat(roomId, roomManagerObj, screeningId);
    }

    // ---------------- GET ----------------
    public String getDate(int id){
        if (checkId(id))
        {
            return getScreeningObjById(id).getDate();
        }
        return "Coś poszło nie tak!";
    }

    public int getFilmId(int id){
        if (checkId(id))
        {
            return getScreeningObjById(id).getFilmId();
        }
        return -1;
    }

    public int getRoomId(int id){
        if (checkId(id))
        {
            return getScreeningObjById(id).getRoomId();
        }
        return -1;
    }

    public int getBoughtSeats(int id){
        if (checkId(id))
        {
            return getScreeningObjById(id).getBoughtSeats();
        }
        return -1;
    }

    public int getReservedSeats(int id){
        if (checkId(id))
        {
            return getScreeningObjById(id).getReservedSeats();
        }
        return -1;
    }

    // ---------------- SET ----------------
    public void setDate(int id, String newDate){
        if (checkId(id))
        {
            getScreeningObjById(id).setDate(newDate);
        }
    }

    public void setFilmId(int id, int newFilmId){
        if (checkId(id))
        {
            getScreeningObjById(id).setFilmId(newFilmId);
        }
    }

    public void setRoomId(int id, int newRoomId){
        if (checkId(id))
        {
            getScreeningObjById(id).setRoomId(newRoomId);
        }
    }

    public void setBoughtSeats(int id, int newBoughtSeats){
        if (checkId(id))
        {
            getScreeningObjById(id).setBoughtSeats(newBoughtSeats);
        }
    }

    public void setReservedSeats(int id, int newReservedSeats){
        if (checkId(id))
        {
            getScreeningObjById(id).setReservedSeats(newReservedSeats);
        }
    }
}
