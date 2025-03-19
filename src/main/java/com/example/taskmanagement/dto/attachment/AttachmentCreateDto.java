package com.example.taskmanagement.dto.attachment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttachmentCreateDto {

    private String filePath;

    private String fileName;

}
