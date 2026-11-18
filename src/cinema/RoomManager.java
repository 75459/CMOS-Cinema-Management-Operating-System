package cinema;

import java.util.ArrayList;

public class RoomManager implements Displayable {
    private ArrayList<Room> roomList = new ArrayList<>();

    public RoomManager(){}


    // --------------------------------- USEFUL ---------------------------------

    private int getLastId(){ // returns last used id (for creating new room object)
        if (roomList.size() == 0)
        {
            return 0;
        }
        else
        {
            return roomList.get(roomList.size()-1).getId();
        }
    }

    Room getRoomById(int id){ // gets room object by it id property
        for (Room item: roomList)
        {
            if (item.getId() == id)
            {
                return item;
            }
        }
        return null;
    }

    private boolean checkId(int id){ // check whether sala with that id exists
        for (Room item : roomList){
            if (item.getId() == id)
            {
                return true;
            }
        }
        return false;
    }

    // -------------------------------------------------------------------------


    @Override
    public String getAllInfo() {
        if (roomList.size() == 0) {
            return "Brak sal w bazie.";
        }
        String result = "";
        for (Room item : roomList) {
            result = result + item.toString() + "\n";
        }
        return result;
    }

    public int getRoomCountById(int id){
        if (checkId(id)){
            return getRoomById(id).getSeatCount();
        }
        return -1;
    }
    public void createSala(String number, int seatCount){
        Room room = new Room(number, seatCount, getLastId() + 1);
        roomList.add(room);
    }



    public void setClean(int id, boolean newState){
        if (checkId(id))
        {
            Room roomObj = getRoomById(id);
            if (roomObj != null)
            {
                roomObj.setClean(newState);
            }
        }
    }



    public void setActive(int id, boolean newState){
        if (checkId(id))
        {
            Room roomObj = getRoomById(id);
            if (roomObj != null)
            {
                roomObj.setActive(newState);
            }
        }
    }

    public void deleteSala(int id){
        if (checkId(id)) {
            roomList.remove(getRoomById(id));
        }
    }


}
