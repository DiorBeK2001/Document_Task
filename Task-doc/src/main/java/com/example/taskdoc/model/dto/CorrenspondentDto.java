package com.example.taskdoc.model.dto;

import com.example.taskdoc.model.domain.Correspondent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorrenspondentDto implements Serializable {

    private Integer id;

    private String name;

    public Correspondent map2Entity() {
        Correspondent correspondent = new Correspondent();
        correspondent.setName(this.getName());
        return correspondent;
    }
}
