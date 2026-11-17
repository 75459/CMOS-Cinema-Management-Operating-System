package cinema;

import java.util.ArrayList;

public class saleManager {
    private ArrayList<Sala> listaSal = new ArrayList<>();

    public saleManager(){}
    public saleManager(ArrayList<Sala> list){
        if (list != null)
        {
            listaSal = list;
        }
    }

    private int getLastId(){ // returns last used id (for creating new sala)
        if (listaSal.size() == 0)
        {
            return 0;
        }
        else
        {
            return listaSal.get(listaSal.size()-1).getId();
        }
    }

    private int getSalaIndexById(int id){
        int reps = 0;
        for (Sala item : listaSal)
        {
            reps++;
            if (item.getId() == id)
            {
                return reps;
            }
        }
        return -1;
    }

    private Sala getSalaById(int id){ // gets sala object by it id property
        for (Sala item: listaSal)
        {
            if (item.getId() == id)
            {
                return item;
            }
        }
        return null;
    }

    public boolean checkId(int id){ // check whether sala with that id exists
        for (Sala item : listaSal){
            if (item.getId() == id)
            {
                return true;
            }
        }
        return false;
    }

    public void createSala(String number, int seatCount){
        Sala sala = new Sala(number, seatCount, getLastId() + 1);
        listaSal.add(sala);
    }

    public void setNumber(int id, String newNumber){
        if (checkId(id))
        {
            Sala salaObj = getSalaById(id);
            if (salaObj != null)
            {
                salaObj.setNumber(newNumber);
            }
        }
    }

    public void setSeatCount(int id, int newSeatCount){
        if (checkId(id))
        {
            Sala salaObj = getSalaById(id);
            if (salaObj != null)
            {
                salaObj.setSeatCount(newSeatCount);
            }
        }
    }

    public void setClean(int id, boolean newState){
        if (checkId(id))
        {
            Sala salaObj = getSalaById(id);
            if (salaObj != null)
            {
                salaObj.setClean(newState);
            }
        }
    }

    public void setActive(int id, boolean newState){
        if (checkId(id))
        {
            Sala salaObj = getSalaById(id);
            if (salaObj != null)
            {
                salaObj.setActive(newState);
            }
        }
    }

    public void deleteSala(int id){
        if (checkId(id))
        {
            int index = getSalaIndexById(id);
            if (index >= 0)
            {
                listaSal.remove(index);
            }
        }
    }


}
