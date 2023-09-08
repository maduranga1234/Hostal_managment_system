package lk.ijse.orm_coursework.controller.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationController {

    public static boolean contactCheck(String contact) {
        String contactRegex = "^(?:7|0|(?:\\\\\\\\+94))[0-9]{9,10}$";
        Pattern pattern = Pattern.compile(contactRegex);
        Matcher matcher = pattern.matcher(contact);
        return matcher.matches();
    }

    public static boolean studentIdCheck(String custId) {
        String customerRegex = "^(STU-)[0-9]{3}$";
        Pattern pattern = Pattern.compile(customerRegex);
        Matcher matcher = pattern.matcher(custId);
        return matcher.matches();
    }
    public static boolean roomIdCheck(String custId) {
        String customerRegex = "^(ROOM-)[0-9]{3}$";
        Pattern pattern = Pattern.compile(customerRegex);
        Matcher matcher = pattern.matcher(custId);
        return matcher.matches();
    }

    public static boolean reservationIdCheck(String custId) {
        String customerRegex = "^(RES-)[0-9]{3}$";
        Pattern pattern = Pattern.compile(customerRegex);
        Matcher matcher = pattern.matcher(custId);
        return matcher.matches();
    }

    public static boolean customerNameValidate(String custName) {
        String customerRegex = "^[A-z\\s]{2,15}$";
        Pattern pattern = Pattern.compile(customerRegex);
        Matcher matcher = pattern.matcher(custName);
        return matcher.matches();
    }

    public static boolean Password (String password){
        Pattern idPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

        boolean matches = idPattern.matcher(password).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }

    public  static boolean salary(String salary) {
        Pattern idPattern = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$");
        boolean matches = idPattern.matcher(salary).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean DateOfBirth (String nic){
        Pattern idPattern = Pattern.compile("^([0-9]{4})-([0-9]{2})-([0-9]{2})$");

        boolean matches = idPattern.matcher(nic).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }
}
