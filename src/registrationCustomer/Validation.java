package registrationCustomer;

import java.io.*;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation implements Serializable {
    private String email;
    private String name;

    public boolean validationSymbolDog() {
        String test1 = "^[A-z0-9]+([_!#$%&’*+/=?`{|}~^.-][A-z0-9]+)*(?!.*([_!#$%&’*+/=?`{|}~^.-])\\1)*@[A-z0-9]+\\.[A-z0-9]+(\\.[A-z0-9]+)*$";
        Pattern pattern = Pattern.compile(test1);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validEmail(String email) {
        String path = "src/registrationCustomer/domenForMail.txt";
        boolean test = false;
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String domen = email.substring(email.indexOf('@') + 1, email.length());
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals(domen)) {
                    test = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return test;
    }

    public boolean validName(String name) {
        String regex = "^([A-Za-z])+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}