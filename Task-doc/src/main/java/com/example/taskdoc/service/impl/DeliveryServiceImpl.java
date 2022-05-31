package com.example.taskdoc.service.impl;

import com.example.taskdoc.model.domain.Delivery;
import com.example.taskdoc.model.dto.DeliveryDto;
import com.example.taskdoc.repository.DeliveryRepo;
import com.example.taskdoc.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryRepo deliveryRepo;

    @Override
    public Delivery addDelivery(DeliveryDto dto) {
        try {
            log.debug("success saved");
            return deliveryRepo.save(dto.map2Entity());
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return new Delivery();
        }
    }

    @Override
    public Delivery editDelivery(Integer id, DeliveryDto dto) {
        try {
            Delivery delivery = deliveryRepo.findById(id).orElseThrow(() -> new ResolutionException("getDeliveryID"));
            if (delivery.getName().equals(dto.getName())) {
                log.error("This name already exist: {}", delivery.getName());
            }
            delivery.setName(dto.getName());
            log.debug("Success edited");
            return deliveryRepo.save(delivery);
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return new Delivery();
        }
    }

    @Override
    public List<Delivery> getAllDelivery() {
        log.debug("OK");
        return deliveryRepo.findAll();
    }

    @Override
    public Delivery getDelivery(Integer id) {
        try {
            log.debug("OK");
            return deliveryRepo.findById(id).orElseThrow(() -> new ResolutionException("getDeliveryID"));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return new Delivery();
        }
    }
}
