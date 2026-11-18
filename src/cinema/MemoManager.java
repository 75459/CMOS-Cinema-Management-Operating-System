package cinema;

import java.util.ArrayList;

public class MemoManager implements Displayable{
    private ArrayList<Memo> memoList = new ArrayList<>();

    public MemoManager(){}

    // --------------------------------- USEFUL ---------------------------------

    private int getLastId(){ // returns last used id (for creating new memo)
        if (memoList.size() == 0)
        {
            return 0;
        }
        else
        {
            return memoList.get(memoList.size()-1).getId();
        }
    }

    private boolean checkId(int id){ // check whether memo with that id exists
        for (Memo item : memoList){
            if (item.getId() == id)
            {
                return true;
            }
        }
        return false;
    }

    private int getMemoIndexById(int id){ // self-explanatory pretty much
        int reps = 0;
        for (Memo item : memoList)
        {
            if (item.getId() == id)
            {
                return reps;
            }
            reps++;
        }
        return -1;
    }


    // -------------------------------------------------------------------------

    public void createMemo(String content){
        Memo memo = new Memo(getLastId()+1, content);
        memoList.add(memo);
    }

    public String getAllInfo() {
        if (memoList.size() == 0) {
            return "Brak ogłoszeń w bazie.";
        }
        String result = "";
        for (Memo item : memoList) {
            result = result + item.toString() + "\n";
        }
        return result;
    }

    public void deleteMemo(int id){
        if (checkId(id)){
            int index = getMemoIndexById(id);
            if (index >= 0)
            {
                memoList.remove(index);
            }
        }
    }
}
