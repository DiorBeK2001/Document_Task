package com.example.taskdoc.model.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResUploadFile {
    private Long fileId;
    private String fileName;
    private String fileType;
    private long size;
    private String link;
}
