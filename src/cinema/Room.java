package cinema;

public class Room extends CinemaEntity {
    private String number;
    private int seatCount;
     private boolean clean;
     private boolean active;

    Room(String number, int seatCount, int id) {
        super(id);
        this.number = number;
        this.seatCount = seatCount;
        this.clean = true;
        this.active = true;
    }

    // ------------ GET ------------------

    String getNumber(){
        return number;
    }

    int getSeatCount(){
        return seatCount;
    }

    boolean isClean(){
        return clean;
    }

    boolean isActive(){
        return active;
    }


    // ------------ SET ------------------

    void setClean(boolean clean){
        this.clean = clean;
    }

    void setActive(boolean active){
        this.active = active;
    }

    @Override
    String getInfo() {
        return id + ". Sala " + number + " | Miejsca: " + seatCount + " | Czysta: " + clean + " | Sprawna: " + active;
    }

    @Override
    public String toString() {
        return "Sala: " + getInfo();
    }
}
