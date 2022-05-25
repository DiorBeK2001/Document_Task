package com.example.taskdoc.model.dto;

import com.example.taskdoc.model.domain.Role;
import com.example.taskdoc.model.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {

    private Long id;

    private String fullName, email, phoneNumber, password;

    private Set<Role> roles;

    public User map2Entity() {
        User user = new User();
        user.setFullName(this.getFullName());
        user.setEmail(this.getEmail());
        return user;
    }
}
