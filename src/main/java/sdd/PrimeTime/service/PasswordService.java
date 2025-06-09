package sdd.PrimeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Ani Nguyen on 09/06/2025.
 * Author: An Nguyen
 */
@Service
public class PasswordService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${app.movie.save.password.hash}")
    private String storedPasswordHash;

    public boolean validatePassword(String rawPassword) {
        return passwordEncoder.matches(rawPassword, storedPasswordHash);
    }
}