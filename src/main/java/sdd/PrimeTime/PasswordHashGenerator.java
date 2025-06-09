package sdd.PrimeTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Ani Nguyen on 09/06/2025.
 * Author: An Nguyen
 */
public class PasswordHashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "";
        String hashedPassword = encoder.encode(rawPassword);
        System.out.println("Gehashtes Passwort: " + hashedPassword);
    }
}