package com.example.taskdoc.model.domain;

import com.example.taskdoc.model.dto.DeliveryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Delivery extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public DeliveryDto map2DTO(){
        DeliveryDto dto = new DeliveryDto();
        dto.setId(this.getId());
        dto.setName(this.getName());
        return dto;
    }

    public Delivery(String name) {
        this.name = name;
    }
}
