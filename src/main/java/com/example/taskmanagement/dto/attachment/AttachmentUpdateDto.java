package com.example.taskmanagement.dto.attachment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttachmentUpdateDto {
    private String newFilePath;

    private String newFileName;
}
