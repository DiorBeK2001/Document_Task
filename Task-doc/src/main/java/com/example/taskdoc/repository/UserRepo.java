package com.example.taskdoc.repository;

import com.example.taskdoc.model.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends CrudRepository<User, UUID> {
    Optional<User> findByPhoneNumber(String phoneNumber);

//    Optional<User> findByPhoneNumber(String phoneNumber);
}
