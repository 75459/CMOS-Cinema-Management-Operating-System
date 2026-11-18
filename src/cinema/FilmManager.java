package cinema;

import java.util.ArrayList;

public class FilmManager {
    private ArrayList<Film> filmList = new ArrayList<>();

    public FilmManager() {}

    public FilmManager(ArrayList<Film> filmList) {
        if (filmList != null)
        {
            this.filmList = filmList;
        }
    }

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


    public void createFilm(String title){
        Film film = new Film(getLastId()+1,title);
        filmList.add(film);
    }

    public String getTitle(int id){
        if (checkId(id)){
            Film filmObj = getFilmObjById(id);
            if (filmObj != null){
                return filmObj.getTitle();
            }
        }
        return "Coś poszło nie tak!";
    }

    public void deleteFilm(int id){
        if (checkId(id)){
            filmList.remove(getFilmObjById(id));
        }
    }
}
