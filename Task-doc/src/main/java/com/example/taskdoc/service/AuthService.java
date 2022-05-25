package com.example.taskdoc.service;

import com.example.taskdoc.model.domain.User;
import com.example.taskdoc.model.dto.UserDto;
import com.example.taskdoc.model.enums.RoleName;
import com.example.taskdoc.repository.RoleRepo;
import com.example.taskdoc.repository.UserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@Log4j2
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    final
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepo roleRepo;

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public UserDetails getUserById(UUID userId) {
        return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("getUser"));
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepo.findByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException("Don't found"));
    }

    /**
     * User register method
     */
    public User registerUser(UserDto userDto) {
        try {
            User savedUser = userRepo.save(makeUser(userDto, false));
            log.debug("success: {}", savedUser);
            return savedUser;
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return null;
        }
    }

    public User makeUser(UserDto userDto, boolean admin) {
        User user = userDto.map2Entity();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(roleRepo.findAllByRoleName(!admin ? RoleName.ROLE_USER : RoleName.ROLE_ADMIN));
        user.setEnabled(true);
        return user;
    }
}
