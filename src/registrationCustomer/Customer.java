package registrationCustomer;

import java.io.Serializable;
import java.util.Objects;

/**
 * The `Customer` class represents a customer with a name and an email address.
 * It implements the Serializable interface for object serialization.
 */
public class Customer implements Serializable {

    private String name;
    private String email;


    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
