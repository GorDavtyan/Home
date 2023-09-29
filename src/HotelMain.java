import roomRegistation.RoomType;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelMain {
    private Hotel hotel;
    private static final  String allPathForInfo = "allPathForInfo.txt";
    private static final String dataOfSerialization = "src/dataOfSerialization/";
    private static final String dataOfReports = "src/dataOfReports/";


    public void run() {

        String filePath = selectInfoFilePath();
        if (filePath == null) {
            hotel = new Hotel();
        } else {
            hotel = Hotel.deserializeHotel(filePath);
        }

        Scanner scanner = new Scanner(System.in);

        String choice;

        do {
            System.out.println("1. Add a room");
            System.out.println("2. Add a customer");
            System.out.println("3. Book a room");
            System.out.println("4. Generate report for a room");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> hotel.addRoom(scanner);
                case "2" -> hotel.addCustomer(scanner);
                case "3" -> hotel.bookRoom(scanner);
                case "4" -> hotel.generateReport(scanner, dataOfReports);
                case "0" -> {
                    exit(scanner);
                    System.out.println("Exiting...");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        } while (!choice.equals("0"));
    }

    public void exit(Scanner scanner) {

        int index;
        do {
            System.out.println("You want to input file name for save information");
            System.out.println("1. To input name");
            System.out.println("2. default name");
            try {
                index = scanner.nextInt();
            } catch (InputMismatchException e) {
                index = -1;
            }
            scanner.nextLine();
        } while (index < 1 || index > 2);
        String path = "hotel";
        if (index == 1) {
            System.out.println("Please enter file-name for save information");
            path = scanner.nextLine();
        }
        path = dataOfSerialization + path + ".txt";
        hotel.serializeHotel(path);

        try (FileWriter fileWriter = new FileWriter(allPathForInfo)){
            fileWriter.write(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String selectInfoFilePath() {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(allPathForInfo))){
            return fileReader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
