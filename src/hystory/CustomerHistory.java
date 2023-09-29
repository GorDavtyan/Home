package hystory;

import registrationCustomer.Customer;
import roomRegistation.Room;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The `CustomerHistory` class represents the booking history of a customer in a hotel management system.
 * It maintains a record of the customer's bookings for different rooms, along with the corresponding
 * reservation dates.
 */
public class CustomerHistory implements Serializable {
    private Customer customer;
    private Map<Room, Set<StartAndEndDate>> setMap;

    /**
     * Initializes a new instance of the `CustomerHistory` class for a specific customer.
     *
     * @param customer The customer for whom the booking history is maintained.
     */
    public CustomerHistory(Customer customer) {
        this.customer = customer;
        this.setMap = new HashMap<>();
    }

    /**
     * Adds a booking record for a room along with the reservation period.
     *
     * @param room             The room being booked.
     * @param startAndEndDate  The start and end dates of the reservation.
     * @return True if the booking record was added successfully, false otherwise.
     */
    public boolean addBookHistory(Room room, StartAndEndDate startAndEndDate) {
        if (!setMap.containsKey(room)) {
            setMap.put(room, new HashSet<>());
        }

        Set<StartAndEndDate> dt = setMap.get(room);
        if (dt.add(startAndEndDate)) {
            setMap.put(room, dt);
            return true;
        }
        System.out.println("data logging is available");
        return false;
    }

    /**
     * Retrieves the customer associated with this booking history.
     *
     * @return The customer associated with this booking history.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Retrieves a mapping of rooms to their booking periods for the customer.
     *
     * @return A mapping of rooms to their booking periods.
     */
    public Map<Room, Set<StartAndEndDate>> getSetMap() {
        return setMap;
    }
}
