package hystory;

import registrationCustomer.Customer;
import roomRegistation.Room;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomerHistory implements Serializable {
    private Customer customer;
    private Map<Room, Set<StartAndEndDate>> setMap;

    public CustomerHistory(Customer customer) {
        this.customer = customer;
        this.setMap = new HashMap<>();
    }

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

    public Customer getCustomer() {
        return customer;
    }

    public Map<Room, Set<StartAndEndDate>> getSetMap() {
        return setMap;
    }
}
