package com.example.taskdoc.repository;

import com.example.taskdoc.model.domain.Role;
import com.example.taskdoc.model.enums.RoleName;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RoleRepo extends CrudRepository<Role, Integer> {
    Set<Role> findAllByRoleName(RoleName name);
}
