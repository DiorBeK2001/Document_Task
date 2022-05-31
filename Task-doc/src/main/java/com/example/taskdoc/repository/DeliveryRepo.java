package com.example.taskdoc.repository;

import com.example.taskdoc.model.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepo extends JpaRepository<Delivery, Integer> {

}
