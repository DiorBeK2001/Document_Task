package com.example.taskdoc.repository;

import com.example.taskdoc.model.domain.Attachment;
import org.springframework.data.repository.CrudRepository;

public interface AttachmentRepo extends CrudRepository<Attachment, Long> {

}
