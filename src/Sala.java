public class Sala {
    public int Id;
    public String number;
    public int seatCount;
    public boolean isClean;
    public boolean active;

    public Sala(String number, int seatCount, int Id){
        this.number = number;
        this.seatCount = seatCount;
        isClean = true;
        active = true;
        this.Id = Id;
    }
}
