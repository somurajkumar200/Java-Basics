package Service;

import Model.User;
import Util.OTPGenerator;
import java.util.HashMap;
import java.util.Scanner;

public class AuthenticationService {
    private HashMap<String, User> users;
    private String generatedOTP;
    public AuthenticationService(HashMap<String, User> users) {
        this.users = users;
    }

    public User login(String mobile, Scanner sc) throws Exception {
        if (!users.containsKey(mobile)) throw new Exception("User not found");
        generatedOTP = OTPGenerator.generate();
        System.out.println("OTP Sent: " + generatedOTP);
        System.out.print("Enter OTP: ");
        String input = sc.nextLine();
        if (!generatedOTP.equals(input)) throw new Exception("Invalid OTP");
        return users.get(mobile);
    }
}