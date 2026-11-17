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

    private int getLastId(){
        if (listaSal.size() == 0)
        {
            return 1;
        }
        else
        {
            return listaSal.get(listaSal.size()).Id;
        }
    }

    private Sala getSalaById(int id){
        for (Sala item: listaSal)
        {
            if (item.Id == id)
            {
                return item;
            }
        }
        return null;
    }

    private boolean checkId(int id){
        for (Sala item : listaSal){
            if (item.Id == id)
            {
                return true;
            }
        }
        return false;
    }

    public void createSala(String number, int seatCount){
        Sala sala = new Sala(number, seatCount, getLastId()+1);
        listaSal.add(sala);
    }

    public void setNumber(int id, String newNumber){
        if (checkId(id))
        {
            Sala salaObj = getSalaById(id);
            if (salaObj != null)
            {
                salaObj.number = newNumber;
            }
        }
    }

    public void setSeatCount(int id, int newSeatCount){
        if (checkId(id))
        {
            Sala salaObj = getSalaById(id);
            if (salaObj != null)
            {
                salaObj.seatCount = newSeatCount;
            }
        }
    }

    public void setClean(int id, boolean newState){
        if (checkId(id))
        {
            Sala salaObj = getSalaById(id);
            if (salaObj != null)
            {
                salaObj.isClean = newState;
            }
        }
    }

    public void setActive(int id, boolean newState){
        if (checkId(id))
        {
            Sala salaObj = getSalaById(id);
            if (salaObj != null)
            {
                salaObj.active = newState;
            }
        }
    }


}
