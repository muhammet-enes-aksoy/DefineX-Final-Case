package com.example.taskmanagement.dto.attachment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachmentCreateDto {

    private String filePath;

    private String fileName;

}
