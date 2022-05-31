package com.example.taskdoc.service;

import com.example.taskdoc.model.domain.Delivery;
import com.example.taskdoc.model.dto.DeliveryDto;

import java.util.List;

public interface DeliveryService {

    Delivery addDelivery(DeliveryDto deliveryDto);

    Delivery editDelivery(Integer id, DeliveryDto deliveryDto);

    List<Delivery> getAllDelivery();

    Delivery getDelivery(Integer id);
}
