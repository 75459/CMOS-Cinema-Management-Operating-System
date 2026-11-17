package cinema;

public class Memo {
    private int id;
    private String content;

    Memo(int id, String content){
        this.id = id;
        this.content = content;
    }

    int getId(){
        return id;
    }

    String getContent(){
        return content;
    }
}
