package cinema;

public class Film {
    private int id;
    private String title;

    Film(int id, String title){
        this.id = id;
        this.title = title;
    }

    int getId(){
        return id;
    }

    String getTitle(){
        return title;
    }
}
