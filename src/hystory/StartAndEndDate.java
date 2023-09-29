package hystory;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;

public class StartAndEndDate implements Serializable {
    private LocalDate startDate;
    private LocalDate endDate;

    private StartAndEndDate(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    static private boolean validDate(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    static public StartAndEndDate createDate(LocalDate startDate, LocalDate endDate) throws DateTimeException {
        if (validDate(startDate) && validDate(endDate)) {
            return new StartAndEndDate(startDate, endDate);
        } else {
            throw new DateTimeException("Input date time Before Local Date Time now");
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }


    @Override
    public String toString() {
        return "startDate=" + startDate +
                ", endDate=" + endDate ;
    }
}
