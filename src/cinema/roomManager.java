package cinema;

import java.util.ArrayList;

public class roomManager {
    private ArrayList<Room> roomList = new ArrayList<>();

    public roomManager(){}
    public roomManager(ArrayList<Room> list){
        if (list != null)
        {
            roomList = list;
        }
    }

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

    private int getSalaIndexById(int id){
        int reps = 0;
        for (Room item : roomList)
        {
            reps++;
            if (item.getId() == id)
            {
                return reps;
            }
        }
        return -1;
    }

    private Room getSalaById(int id){ // gets room object by it id property
        for (Room item: roomList)
        {
            if (item.getId() == id)
            {
                return item;
            }
        }
        return null;
    }

    public boolean checkId(int id){ // check whether sala with that id exists
        for (Room item : roomList){
            if (item.getId() == id)
            {
                return true;
            }
        }
        return false;
    }

    // -------------------------------------------------------------------------

    public void createSala(String number, int seatCount){
        Room room = new Room(number, seatCount, getLastId() + 1);
        roomList.add(room);
    }

    public void setNumber(int id, String newNumber){
        if (checkId(id))
        {
            Room roomObj = getSalaById(id);
            if (roomObj != null)
            {
                roomObj.setNumber(newNumber);
            }
        }
    }

    public void setSeatCount(int id, int newSeatCount){
        if (checkId(id))
        {
            Room roomObj = getSalaById(id);
            if (roomObj != null)
            {
                roomObj.setSeatCount(newSeatCount);
            }
        }
    }

    public void setClean(int id, boolean newState){
        if (checkId(id))
        {
            Room roomObj = getSalaById(id);
            if (roomObj != null)
            {
                roomObj.setClean(newState);
            }
        }
    }

    public void setActive(int id, boolean newState){
        if (checkId(id))
        {
            Room roomObj = getSalaById(id);
            if (roomObj != null)
            {
                roomObj.setActive(newState);
            }
        }
    }

    public void deleteSala(int id){
        if (checkId(id))
        {
            int index = getSalaIndexById(id);
            if (index >= 0)
            {
                roomList.remove(index);
            }
        }
    }


}
