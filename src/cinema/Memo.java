package cinema;

public class Memo extends CinemaEntity {
    private String content;

    Memo(int id, String content) {
        super(id);
        this.content = content;
    }


    String getContent() {
        return content;
    }

    @Override
    String getInfo() {
        return id + ". " + content;
    }

    @Override
    public String toString() {
        return "Ogłoszenie: " + getInfo();
    }
}
