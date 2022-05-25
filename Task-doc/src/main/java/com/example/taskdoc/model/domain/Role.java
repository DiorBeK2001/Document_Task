package com.example.taskdoc.model.domain;

import com.example.taskdoc.model.enums.RoleName;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @Override
    public String getAuthority() {
        return roleName.name();
    }

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }
}

