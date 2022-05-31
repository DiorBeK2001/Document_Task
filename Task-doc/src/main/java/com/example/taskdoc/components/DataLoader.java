package com.example.taskdoc.components;

import com.example.taskdoc.model.domain.Correspondent;
import com.example.taskdoc.model.domain.Delivery;
import com.example.taskdoc.model.domain.Role;
import com.example.taskdoc.model.domain.User;
import com.example.taskdoc.model.enums.RoleName;
import com.example.taskdoc.repository.CorrenspondentRepo;
import com.example.taskdoc.repository.DeliveryRepo;
import com.example.taskdoc.repository.RoleRepo;
import com.example.taskdoc.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    CorrenspondentRepo correnspondentRepo;

    @Autowired
    DeliveryRepo deliveryRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {

            roleRepo.save(new Role(1, RoleName.ROLE_ADMIN));
            roleRepo.save(new Role(2, RoleName.ROLE_USER));

            userRepo.save(
                    new User(
                            "Admin",
                            "Admin@gmail.com",
                            passwordEncoder.encode("admin"),
                            "+998901234567",
                            new HashSet<>(roleRepo.findAllByRoleName(RoleName.ROLE_ADMIN)),
                            true
                    )
            );

            userRepo.save(
                    new User(
                            "User",
                            "user@gmail.com",
                            passwordEncoder.encode("user"),
                            "+998912345678",
                            new HashSet<>(roleRepo.findAllByRoleName(RoleName.ROLE_USER)),
                            true
                    )
            );

            correnspondentRepo.save(new Correspondent("ЦБ"));
            correnspondentRepo.save(new Correspondent("ГНИ"));
            correnspondentRepo.save(new Correspondent("ТСЖ"));

            deliveryRepo.save(new Delivery("Courier"));
            deliveryRepo.save(new Delivery("Email"));
            deliveryRepo.save(new Delivery("Telephonegramma"));
        }
    }
}
