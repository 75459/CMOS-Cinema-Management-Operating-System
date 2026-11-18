package cinema;

import java.util.Comparator;

class ScreeningSeatsComparator implements Comparator<Screening> {
    @Override
    public int compare(Screening s1, Screening s2) {
        return Integer.compare(s2.getBoughtSeats(), s1.getBoughtSeats());
    }
}