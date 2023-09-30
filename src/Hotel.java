import hystory.CustomerHistory;
import hystory.RoomHistory;
import hystory.StartAndEndDate;
import registrationCustomer.Customer;
import registrationCustomer.Validation;
import roomRegistation.RegistrationRoom;
import roomRegistation.Room;
import roomRegistation.RoomType;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The `Hotel` class represents a hotel management system with features to add rooms, customers,
 * generate reports, book rooms, and perform other related operations. It implements the Serializable
 * interface for object serialization.
 */
public class Hotel implements Serializable {

    private List<CustomerHistory> customerHistories = new ArrayList<>();
    private List<RoomHistory> roomHistorys = new ArrayList<>();

    /**
     * Add a room to the hotel based on user input.
     *
     * @param scanner The scanner object to read user input.
     */
    public void addRoom(Scanner scanner) {
        int index;
        do {
            index = getIndex(scanner);
            scanner.nextLine();
        } while (index < 0 || index > 3);
        if (index == 0) {
            return;
        }

        Room room = new RegistrationRoom().add(index);
        roomHistorys.add(new RoomHistory(room));
    }

    private int getIndex(Scanner scanner) {
        System.out.println("Please input index of room type");
        int index;
        System.out.println(0 + " exit");
        System.out.println(1 + " " + RoomType.SINGLE);
        System.out.println(2 + " " + RoomType.DOUBLE);
        System.out.println(3 + " " + RoomType.DELUXE);
        try {
            index = scanner.nextInt();
        } catch (InputMismatchException e) {
            index = -1;
        }
        return index;
    }

    /**
     * Add a customer to the hotel's customer history.
     *
     * @param scanner The scanner object to read user input.
     */
    public void addCustomer(Scanner scanner) {
        String name = "";
        String email = "";
        Validation emailValidation = new Validation();
        name = inputCustomerName(scanner, emailValidation);
        email = inputCustomerEmail(scanner, emailValidation);
        customerHistories.add(new CustomerHistory(new Customer(name, email)));
    }

    private String inputCustomerName(Scanner scanner, Validation emailValidation) {
        System.out.println("Please enter the client name");
        String name = scanner.nextLine();
        while (!emailValidation.validName(name)) {
            System.out.println("Please enter the valid name");
            name = scanner.nextLine();
        }
        return name;
    }

    private String inputCustomerEmail(Scanner scanner, Validation emailValidation) {
        System.out.println("Please enter the email for customer");
        String email = scanner.nextLine();
        while (!emailValidation.validEmail(email)) {
            System.out.println("You entered an invalid email address, please enter the valid email");
            email = scanner.nextLine();
        }
        return email;
    }


    /**
     * Generate a report based on room number and save it to a file.
     *
     * @param scanner       The scanner object to read user input.
     * @param dataOfReports The directory where reports are stored.
     */
    public void generateReport(Scanner scanner, String dataOfReports) {
        int roomNumber = inputRoomNumber(scanner);
        scanner.nextLine();
        String context = report(roomNumber);
        if (context == null) {
            System.out.println("There is no room for that number");
            return;
        }
        System.out.print("Enter file name to save the report: ");
        String fileName = scanner.nextLine();
        fileName = dataOfReports + fileName + ".txt";
        System.out.println(context);
        try (Writer writer = new FileWriter(fileName)) {
            writer.write(context);
            System.out.println("Report generated successfully.");
        } catch (IOException e) {
            System.out.println("Failed to generate report.");
        }
    }

    private Customer inputCustomerEmailFor(Scanner scanner) {
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        return findCustomer(email);
    }

    private RoomType inputRoomType(Scanner scanner) {
        int index;
        do {
            index = getIndex(scanner);
        } while (index < 0 || index > 3);


        return switch (index) {
            case 1 -> RoomType.SINGLE;
            case 2 -> RoomType.DOUBLE;
            case 3 -> RoomType.DELUXE;
            default -> null;
        };
    }

    private StartAndEndDate inputDate(Scanner scanner) {
        LocalDate startDate;
        LocalDate endDate;
        while (true) {

            try {
                System.out.print("Enter start date (YYYY-MM-dd): ");
                String startDateStr = scanner.nextLine();
                validDate(startDateStr);
                startDate = LocalDate.parse(startDateStr);
                System.out.print("Enter end date (YYYY-MM-dd): ");
                String endDateStr = scanner.nextLine();
                validDate(endDateStr);
                endDate = LocalDate.parse(endDateStr);
                break;
            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
            }
        }


        StartAndEndDate reserveDuration = null;
        try {
            reserveDuration = StartAndEndDate.createDate(startDate, endDate);
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        }
        return reserveDuration;
    }

    /**
     * Book a room for a customer based on user input.
     *
     * @param scanner The scanner object to read user input.
     */
    public void bookRoom(Scanner scanner) {
        Customer customer = inputCustomerEmailFor(scanner);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }


        RoomType typeRoom = inputRoomType(scanner);
        scanner.nextLine();
        if (typeRoom == null) {
            System.out.println("We don't have such a room");
            return;
        }

        StartAndEndDate reserveDuration = inputDate(scanner);
        if (reserveDuration == null) {
            return;
        }


        boolean room = bookRoomHelper(customer, typeRoom, reserveDuration);
        if (!room) {
            System.out.println("No available rooms of the specified type.");
            return;
        }

        System.out.println("Room booked successfully.");
    }

    /**
     * Validate the date format entered by the user.
     *
     * @param date The date string entered by the user.
     */
    public void validDate(String date) throws DateTimeException {
        boolean test = validDateFormat(date);
        if (!test) {
            throw new DateTimeException("Invalid statement, please enter the valid date");
        }
    }

    /**
     * Check if a date string follows a valid format (YYYY-MM-dd).
     *
     * @param date The date string to validate.
     * @return True if the date is in a valid format, false otherwise.
     */
    public boolean validDateFormat(String date) {
        String regex = "^(19[5-9][0-9]|20([0-9][0-9])-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01]))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        boolean test = false;
        if (matcher.matches()) {
            String[] split = date.split("-");
            int year = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]);
            int day = Integer.parseInt(split[2]);

            if (((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && (day >= 1 && day <= 31)) || ((month == 4 || month == 6 || month == 9 || month == 11) && day >= 1 && day <= 30)) {
                return true;
            } else if ((month == 2 && (year % 4 == 0 && year % 100 != 0) && (day >= 1 && day <= 29)) || (month == 2 && day >= 1 && day <= 28)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to book a room for a customer.
     *
     * @param customer         The customer to book the room for.
     * @param typeRoom         The type of room to book.
     * @param startEndDateTime The start and end date for the reservation.
     * @return True if the room was booked successfully, false otherwise.
     */
    private boolean bookRoomHelper(Customer customer, RoomType typeRoom, StartAndEndDate startEndDateTime) {
        Optional<RoomHistory> roomHistory = findRoom(typeRoom, startEndDateTime);
        if (roomHistory.isEmpty()) {
            return false;
        }


        Optional<CustomerHistory> foundedCustomer = findCustomer(customer);
        if (foundedCustomer.isEmpty()) {
            return false;
        }

        RoomHistory roomHistory1 = roomHistory.get();
        roomHistory1.addBookHistory(startEndDateTime);
        if (foundedCustomer.get().addBookHistory(roomHistory1.getRoom(), startEndDateTime)) {
            roomHistorys.forEach(x -> {
                if (x.getRoom().equals(roomHistory1.getRoom())) {
                    x.addPeriod(startEndDateTime);
                }
            });
        }
        System.out.println(generateBill(foundedCustomer.get(), roomHistory1, startEndDateTime));
        return true;
    }


    /**
     * Find a customer by their email address.
     *
     * @param email The email address of the customer to find.
     * @return The found customer or null if not found.
     */
    public Customer findCustomer(String email) {
        for (CustomerHistory customerHistory : customerHistories) {
            if (customerHistory.getCustomer().getEmail().equals(email)) {
                return customerHistory.getCustomer();
            }
        }
        return null;
    }

    private Optional<RoomHistory> findRoom(RoomType typeRoom, StartAndEndDate startEndDateTime) {
        return roomHistorys.stream()
                .filter(room -> room.getRoom().getRoomType().equals(typeRoom))
                .filter(arg -> arg.isOpenBook(startEndDateTime))
                .findFirst();
    }


    private Optional<CustomerHistory> findCustomer(Customer customer) {
        return customerHistories.stream()
                .filter(arg -> arg.getCustomer().equals(customer))
                .findFirst();
    }

    private String generateBill(CustomerHistory foundedCustomer, RoomHistory room, StartAndEndDate startEndDateTime) {
        return "Consumer Name: " + foundedCustomer.getCustomer().getName() + " " + "Email: " + foundedCustomer.getCustomer().getEmail() + " " + "Room Number: " + room.getRoom().getRoomID() + " " + "Room price: " + room.getRoom().getPrice() + " " + "Time book" + startEndDateTime.toString();
    }

    private int inputRoomNumber(Scanner scanner) {
        int roomNumber;
        while (true) {
            try {
                System.out.print("Enter room number: ");
                roomNumber = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer.");
            }
        }
        return roomNumber;
    }

    private String report(int roomNumber) {
        String context = null;
        for (CustomerHistory customerHistory : customerHistories) {
            for (Room room : customerHistory.getSetMap().keySet()) {
                if (room.getRoomID() == roomNumber) {
                    context = "Customer Name: " + customerHistory.getCustomer().getName() + " " + "Room number :" + roomNumber + "\n" + customerHistory.getSetMap().get(room).toString() + "\n";
                }
            }
        }
        return context;
    }


    /**
     * Serialize the current `Hotel` object to a file.
     *
     * @param filePath The path to the file where the object will be serialized.
     */
    public void serializeHotel(String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this);
            System.out.println("Hotel object serialized and saved to file: " + filePath);
        } catch (IOException e) {
            System.out.println("Non object for serialize");
        }
    }

    /**
     * Deserialize a `Hotel` object from a file.
     *
     * @param filePath The path to the file from which the object will be deserialized.
     * @return The deserialized `Hotel` object or null if deserialization fails.
     */
    public static Hotel deserializeHotel(String filePath) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            Hotel hotel = (Hotel) objectIn.readObject();
            System.out.println("Hotel object deserialized from file: " + filePath);
            return hotel;
        } catch (Exception e) {
            return null;
        }
    }
}
