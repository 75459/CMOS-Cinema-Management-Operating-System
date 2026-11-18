package cinema;

public abstract class CinemaEntity {
    private static int entityCount = 0;
    protected int id;

    CinemaEntity(int id) {
        this.id = id;
        entityCount++;
    }

    int getId() {
        return id;
    }

    public static int getEntityCount() {
        return entityCount;
    }

    // Metoda generyczna formatująca dowolną tablicę obiektów (np. Film[], Screening[])
    public static <T> String formatArray(T[] array) {
        if (array == null || array.length == 0) {
            return "Brak danych do wyświetlenia.";
        }
        String result = "";
        for (T item : array) {
            result = result + item.toString() + "\n";
        }
        return result;
    }

    abstract String getInfo();
}