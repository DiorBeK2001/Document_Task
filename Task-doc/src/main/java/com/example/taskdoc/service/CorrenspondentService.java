package com.example.taskdoc.service;

import com.example.taskdoc.model.domain.Correspondent;
import com.example.taskdoc.model.dto.CorrenspondentDto;

import java.util.List;

public interface CorrenspondentService {

    Correspondent addCorrespondent(CorrenspondentDto correnspondentDto);

    Correspondent editCorrespondent(Integer id,CorrenspondentDto correnspondentDto);

    List<Correspondent> getAllCorrespondent();

    Correspondent getCorrespondent(Integer id);

    void deleteCorrespondent(Integer id);
}
