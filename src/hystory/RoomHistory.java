package hystory;

import roomRegistation.Room;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * The `RoomHistory` class represents the booking history of a room in a hotel management system.
 * It maintains a record of the room's booking periods and provides methods to check availability
 * and add booking history.
 */
public class RoomHistory implements Serializable {
    private Room room;
    private List<StartAndEndDate> startAndEndDates;

    /**
     * Initializes a new instance of the `RoomHistory` class for a specific room.
     *
     * @param room The room for which the booking history is maintained.
     */
    public RoomHistory(Room room) {
        this.room = room;
        this.startAndEndDates = new ArrayList<>();
    }

    /**
     * Checks if a given reservation period is available for the room.
     *
     * @param current       The reservation period to check.
     * @param startAndEndDate The reservation period currently booked.
     * @return True if the given reservation period is available, false otherwise.
     */
    public boolean checkDate(StartAndEndDate current, StartAndEndDate startAndEndDate) {
        LocalDate st1 = current.getStartDate();
        LocalDate end1 = current.getEndDate();

        LocalDate st2 = startAndEndDate.getStartDate();
        LocalDate end2 = startAndEndDate.getEndDate();


        boolean test1 = st1.isBefore(end2) && end1.isAfter(st2);
        boolean test2 = st1.isBefore(st2) && end1.isAfter(st2);
        boolean test3 = st1.isAfter(st2) && st1.isBefore(end2);

        return !(test1 || test2 || test3);
    }

    /**
     * Checks if a given reservation period is available for the room.
     *
     * @param st The reservation period to check.
     * @return True if the given reservation period is available, false otherwise.
     */
    public boolean isOpenBook(StartAndEndDate st) {
        if (startAndEndDates.isEmpty()) {
            if (st.getStartDate().isBefore(st.getEndDate())) {
                return true;
            }
        }
        for (StartAndEndDate startAndEndDate : startAndEndDates) {
            if (checkDate(st, startAndEndDate)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Adds a booking period to the room's history if it's available.
     *
     * @param startAndEndDate The reservation period to add.
     * @return True if the booking period was added successfully, false if the room is already booked.
     */
    public boolean addBookHistory(StartAndEndDate startAndEndDate) {
        if (isOpenBook(startAndEndDate)) {
            return true;
        } else {
            System.out.println("Are busy!");
            return false;
        }
    }

    /**
     * Adds a booking period to the room's history.
     *
     * @param startAndEndDate The reservation period to add.
     */
    public void addPeriod(StartAndEndDate startAndEndDate) {
        startAndEndDates.add(startAndEndDate);
    }

    /**
     * Retrieves the room associated with this booking history.
     *
     * @return The room associated with this booking history.
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Retrieves the price of the room.
     *
     * @return The price of the room.
     */
    public double getPrice() {
        return room.getPrice();
    }
}
