package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    private final UserRepo userRepo;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public DemoApplication(UserRepo repository) {
        this.userRepo = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostConstruct
    public void initUsers() {
        try {
            String encodePassword = passwordEncoder.encode("1234");
            List<User> users = Stream.of(
                    new User(1L, "imesh", "199907502281", "piyumal", encodePassword)
            ).toList();
            userRepo.saveAll(users);
        } catch (Exception e) {
            logger.error("An error occurred during user initialization.", e);
        }
    }

}
