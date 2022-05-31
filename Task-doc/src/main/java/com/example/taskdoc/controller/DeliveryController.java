package com.example.taskdoc.controller;

import com.example.taskdoc.model.domain.Correspondent;
import com.example.taskdoc.model.domain.Delivery;
import com.example.taskdoc.model.dto.CorrenspondentDto;
import com.example.taskdoc.model.dto.DeliveryDto;
import com.example.taskdoc.service.impl.CorrenspondentServiceImpl;
import com.example.taskdoc.service.impl.DeliveryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/correnspondent")
public class DeliveryController {

    @Autowired
    DeliveryServiceImpl deliveryService;

    @PostMapping
    public Delivery addCorrespondent(@RequestBody DeliveryDto dto) {
        return deliveryService.addDelivery(dto);
    }

    @PutMapping("/{id}")
    public Delivery editCorrespondent(@PathVariable Integer id, @RequestBody DeliveryDto dto) {
        return deliveryService.editDelivery(id, dto);
    }

    @GetMapping("/{id}")
    public Delivery getCorrespondent(@PathVariable Integer id) {
        return deliveryService.getDelivery(id);
    }

    @GetMapping
    public List<Delivery> getCorrespondentList() {
        return deliveryService.getAllDelivery();
    }
}
