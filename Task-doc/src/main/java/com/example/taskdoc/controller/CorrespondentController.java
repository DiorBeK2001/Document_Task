package com.example.taskdoc.controller;

import com.example.taskdoc.model.domain.Correspondent;
import com.example.taskdoc.model.dto.CorrenspondentDto;
import com.example.taskdoc.service.impl.CorrenspondentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/correnspondents")
@Slf4j
public class CorrespondentController {

    @Autowired
    CorrenspondentServiceImpl correnspondentService;

    @PostMapping
    public Correspondent addCorrespondent(@RequestBody CorrenspondentDto dto) {
        return correnspondentService.addCorrespondent(dto);
    }

    @PutMapping("/{id}")
    public Correspondent editCorrespondent(@PathVariable Integer id, @RequestBody CorrenspondentDto dto) {
        return correnspondentService.editCorrespondent(id, dto);
    }

    @GetMapping("/{id}")
    public Correspondent getCorrespondent(@PathVariable Integer id) {
        return correnspondentService.getCorrespondent(id);
    }

    @GetMapping
    public List<Correspondent> getCorrespondentList() {
        return correnspondentService.getAllCorrespondent();
    }

    @DeleteMapping("/{id}")
    public void deleteCorrespondent(@PathVariable Integer id){
        log.debug("deleted: {}", id);
        correnspondentService.deleteCorrespondent(id);
    }
}
