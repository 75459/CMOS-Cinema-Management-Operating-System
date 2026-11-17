package cinema;

public class Sala {
    private int id; // id for identification in code
    private String number; // sale id number, like 2F, 20, whatever... (like actual sale number on the door)
    private int seatCount;
    private boolean clean;
    private boolean active;

    Sala(String number, int seatCount, int id){
        this.number = number;
        this.seatCount = seatCount;
        clean = true;
        active = true;
        this.id = id;
    }

    // ------------ GET ------------------
    int getId(){
        return id;
    }

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
    void setNumber(String number){
        this.number = number;
    }

    void setSeatCount(int seatCount){
        this.seatCount = seatCount;
    }

    void setClean(boolean clean){
        this.clean = clean;
    }

    void setActive(boolean active){
        this.active = active;
    }
}
