package hystory;

import roomRegistation.Room;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class RoomHistory implements Serializable {
    private Room room;
    private List<StartAndEndDate> startAndEndDates;

    public RoomHistory(Room room) {
        this.room = room;
        this.startAndEndDates = new ArrayList<>();
    }

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

    public boolean isOpenBook(StartAndEndDate st) {
        if (startAndEndDates.isEmpty()) {
            return true;
        }
        for (StartAndEndDate startAndEndDate : startAndEndDates) {
            if (checkDate(st, startAndEndDate)) {
                return true;
            }
        }
        return false;
    }

    public boolean addBookHistory(StartAndEndDate startAndEndDate) {
        if (isOpenBook(startAndEndDate)) {
            startAndEndDates.add(startAndEndDate);
            return true;
        } else {
            System.out.println("Are busy!");
            return false;
        }
    }

    public void addPeriod(StartAndEndDate startAndEndDate) {
        startAndEndDates.add(startAndEndDate);
    }

    public Room getRoom() {
        return room;
    }

    public double getPrice() {
        return room.getPrice();
    }
}
