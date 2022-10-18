package com.example.supportPortal;

import com.example.supportPortal.model.User;
import com.example.supportPortal.repository.UserRepository;
import com.example.supportPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;

import static com.example.supportPortal.constant.FileConstant.USER_FOLDER;

@SpringBootApplication
public class SupportPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupportPortalApplication.class, args);
        new File(USER_FOLDER).mkdirs();
    }

    @Bean
    public CommandLineRunner commandLineRunnerBean(UserService userService) {
        return (args) -> {
            userService.addNewUser("Joe",
                    "Cole",
                    "user1",
                    "joecole@icloud.com",
                    "ROLE_ADMIN", true,
                    true,
                    null);

            userService.addNewUser("John",
                    "Wick",
                    "user2",
                    "johnwick@icloud.com",
                    "ROLE_SUPER_ADMIN", true,
                    true,
                    null);
        };
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
