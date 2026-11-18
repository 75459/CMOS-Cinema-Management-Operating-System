package cinema;

public class Film extends CinemaEntity{
    private String title;

    Film(int id, String title){
        super(id);
        this.title = title;
    }


    @Override
    String getInfo() {
        return id + ". " + title;
    }

    @Override
    public String toString() {
        return "Film: " + getInfo();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Film other = (Film) obj;
        if (title == null) {
            return other.title == null;
        }
        return title.equalsIgnoreCase(other.title);
    }

    @Override
    public int hashCode() {
        return title == null ? 0 : title.toLowerCase().hashCode();
    }
}
