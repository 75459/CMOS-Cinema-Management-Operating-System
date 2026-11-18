package cinema;

public class Film extends CinemaEntity{
    private String title;

    Film(int id, String title){
        super(id);
        this.title = title;
    }

    String getTitle(){
        return title;
    }

    @Override
    String getInfo() {
        return id + ". " + title;
    }
}
