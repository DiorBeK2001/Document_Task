package com.example.taskdoc.controller;

import com.example.taskdoc.model.domain.User;
import com.example.taskdoc.security.CurrentUser;
import com.example.taskdoc.service.impl.AttachmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/attachment")
@Slf4j
public class AttachmentController {

    @Autowired
    AttachmentServiceImpl attachmentServiceImpl;

    @PostMapping("/upload-file-list")
    public HttpEntity<?> uploadFile(MultipartHttpServletRequest request,
                                    @ApiIgnore @CurrentUser User user, @RequestParam(required = false, defaultValue = "null") String name) {
        return attachmentServiceImpl.uploadFileList(request, user, name);
    }

    @GetMapping("/byte-file/{id}")
    public HttpEntity<?> byteFile(@PathVariable(required = false) Long id) {
        return attachmentServiceImpl.byteFile(id);
    }
}
