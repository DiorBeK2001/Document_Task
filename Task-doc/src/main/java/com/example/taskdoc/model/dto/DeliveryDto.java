package com.example.taskdoc.model.dto;

import com.example.taskdoc.model.domain.Correspondent;
import com.example.taskdoc.model.domain.Delivery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {

    private Integer id;

    private String name;

    public Delivery map2Entity() {
        Delivery delivery = new Delivery();
        delivery.setName(this.getName());
        return delivery;
    }
}
