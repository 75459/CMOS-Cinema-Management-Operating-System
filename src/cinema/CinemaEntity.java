package cinema;

public abstract class CinemaEntity {
    private static int entityCount = 0; // 1. pole statyczne
    protected int id;

    CinemaEntity(int id) {
        this.id = id;
        entityCount++;
    }

    int getId() {
        return id;
    }

    public static int getEntityCount() { // 1. metoda statyczna
        return entityCount;
    }

    abstract String getInfo();
}