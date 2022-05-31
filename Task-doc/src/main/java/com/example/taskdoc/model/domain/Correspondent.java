package com.example.taskdoc.model.domain;

import com.example.taskdoc.model.dto.CorrenspondentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Correspondent extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    public CorrenspondentDto map2DTO(){
        CorrenspondentDto dto = new CorrenspondentDto();
        dto.setId(this.getId());
        dto.setName(this.getName());
        return dto;
    }

    public Correspondent(String name) {
        this.name = name;
    }
}
