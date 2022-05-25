package com.example.taskdoc.repository;

import com.example.taskdoc.model.domain.Correspondent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CorrenspondentRepo extends JpaRepository<Correspondent, Integer> {

}
