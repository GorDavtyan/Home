package hystory;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * The `StartAndEndDate` class represents a reservation period in a hotel management system.
 * It includes a start date and an end date, and provides methods for creating valid instances
 * and retrieving the start and end dates.
 */
public class StartAndEndDate implements Serializable {
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Private constructor to create a new instance of `StartAndEndDate` with specified start and end dates.
     *
     * @param startDate The start date of the reservation period.
     * @param endDate   The end date of the reservation period.
     */

    private StartAndEndDate(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Checks if a given date is valid, i.e., after the current date.
     *
     * @param date The date to validate.
     * @return True if the date is valid (after the current date), false otherwise.
     */
    static private boolean validDate(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    /**
     * Creates a new instance of `StartAndEndDate` with the specified start and end dates if they are valid.
     *
     * @param startDate The start date of the reservation period.
     * @param endDate   The end date of the reservation period.
     * @return A valid `StartAndEndDate` instance.
     * @throws DateTimeException if the input dates are not valid (before the current date).
     */
    static public StartAndEndDate createDate(LocalDate startDate, LocalDate endDate) throws DateTimeException {
        if (validDate(startDate) && validDate(endDate)) {
            return new StartAndEndDate(startDate, endDate);
        } else {
            throw new DateTimeException("Input date time Before Local Date Time now");
        }
    }

    /**
     * Retrieves the start date of the reservation period.
     *
     * @return The start date of the reservation period.
     */
    public LocalDate getStartDate() {
        return startDate;
    }
    /**
     * Retrieves the end date of the reservation period.
     *
     * @return The end date of the reservation period.
     */

    public LocalDate getEndDate() {
        return endDate;
    }


    /**
     * Returns a string representation of the reservation period.
     *
     * @return A string containing the start and end dates.
     */
    @Override
    public String toString() {
        return "startDate=" + startDate +
                ", endDate=" + endDate ;
    }
}
