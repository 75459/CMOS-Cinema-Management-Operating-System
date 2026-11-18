package cinema;

import java.util.ArrayList;

public class FilmManager implements Displayable {
    private ArrayList<Film> filmList = new ArrayList<>();

    public FilmManager() {}


    public Film[] getFilmsArray() {
        Film[] array = new Film[filmList.size()];
        for (int i = 0; i < filmList.size(); i++) {
            array[i] = filmList.get(i);
        }
        return array;
    }

    // --------------------------------- USEFUL ---------------------------------

    private Film getFilmObjById(int id){ // self-explanatory pretty much
        for (Film item : filmList)
        {
            if (item.getId() == id){
                return item;
            }
        }
        return null;
    }

    private boolean checkId(int id){ // check whether film with that id exists
        for (Film item : filmList){
            if (item.getId() == id)
            {
                return true;
            }
        }
        return false;
    }

    private int getLastId(){ // returns last used id (for creating new film)
        if (filmList.size() == 0)
        {
            return 0;
        }
        else
        {
            return filmList.get(filmList.size()-1).getId();
        }
    }

    // -------------------------------------------------------------------------

    public String getAllInfo() {
        if (filmList.size() == 0) {
            return "Brak filmów w bazie.";
        }
        String result = "";
        for (Film item : filmList) {
            result = result + item.toString() + "\n";
        }
        return result;
    }

    public boolean createFilm(String title) {
        Film newFilm = new Film(getLastId() + 1, title);
        if (filmList.contains(newFilm)) { // automatycznie wywołuje Film.equals()
            return false;
        }
        filmList.add(newFilm);
        return true;
    }

    public void deleteFilm(int id){
        if (checkId(id)){
            filmList.remove(getFilmObjById(id));
        }
    }


}
