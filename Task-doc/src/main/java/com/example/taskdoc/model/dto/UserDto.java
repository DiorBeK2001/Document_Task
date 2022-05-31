package com.example.taskdoc.model.dto;

import com.example.taskdoc.model.domain.Role;
import com.example.taskdoc.model.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {

    private Long id;

    private String fullName, phoneNumber;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Minimum eight characters, at least one letter and one number")
    private String password;

    @Email(message = "Please type your email")
    private String email;

    private Set<Role> roles;

    public User map2Entity() {
        User user = new User();
        user.setFullName(this.getFullName());
        user.setPhoneNumber(this.getPhoneNumber());
        user.setEmail(this.getEmail());
        return user;
    }
}
