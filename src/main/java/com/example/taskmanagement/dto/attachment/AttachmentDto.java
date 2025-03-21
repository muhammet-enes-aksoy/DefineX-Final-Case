package com.example.taskmanagement.dto.attachment;

import com.example.taskmanagement.base.dto.BaseDto;
import com.example.taskmanagement.entity.Task;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachmentDto implements BaseDto {

    private Long id;

    private String filePath;

    private String fileName;

}
