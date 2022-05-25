package com.example.taskdoc.service.impl;

import com.example.taskdoc.model.domain.Correspondent;
import com.example.taskdoc.model.dto.CorrenspondentDto;
import com.example.taskdoc.repository.CorrenspondentRepo;
import com.example.taskdoc.service.CorrenspondentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
@Slf4j
public class CorrenspondentServiceImpl implements CorrenspondentService {

    @Autowired
    CorrenspondentRepo correnspondentRepo;

    @Override
    public Correspondent addCorrespondent(CorrenspondentDto dto) {
        try {
            log.debug("success saved");
            return correnspondentRepo.save(dto.map2Entity());
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return new Correspondent();
        }
    }

    @Override
    public Correspondent editCorrespondent(Integer id, CorrenspondentDto dto) {
        try {
            Correspondent correspondent = correnspondentRepo.findById(id).orElseThrow(() -> new ResolutionException("getID"));
            correspondent.setName(dto.getName());
            log.debug("success edited");
            return correnspondentRepo.save(correspondent);
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return new Correspondent();
        }
    }

    @Override
    public List<Correspondent> getAllCorrespondent() {
        log.debug("OK");
        return correnspondentRepo.findAll();
    }

    @Override
    public Correspondent getCorrespondent(Integer id) {
        log.debug("OK");
        return correnspondentRepo.findById(id).orElseThrow();
    }
}
