package cinema;

import java.util.ArrayList;

public class MemoManager {
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

    private Memo getMemoObjById(int id){ // self-explanatory as well
        for (Memo item : memoList)
        {
            if (item.getId() == id){
                return item;
            }
        }
        return null;
    }

    // -------------------------------------------------------------------------

    public void createMemo(String content){
        Memo memo = new Memo(getLastId()+1, content);
        memoList.add(memo);
    }

    public String getMemoContentById(int id){ // returns string content of memo
        if (checkId(id)) {
            Memo memoObj = getMemoObjById(id);
            if (memoObj != null) {
                return memoObj.getContent();
            }
        }
        return "something went wrong!";
    }

    public ArrayList<String> getAllMemosContent(){ // returns ArrayList of all memos contents, CAN BE EMPTY IF NO MEMOS
        ArrayList<String> memosContent = new ArrayList<String>();
        for (Memo item : memoList){
            memosContent.add(item.getContent());
        }
        return memosContent;
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
