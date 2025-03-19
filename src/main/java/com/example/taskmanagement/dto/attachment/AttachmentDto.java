package com.example.taskmanagement.dto.attachment;

import com.example.taskmanagement.base.dto.BaseDto;
import com.example.taskmanagement.entity.Task;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttachmentDto implements BaseDto {

    private Long id;

    private String filePath;

    private String fileName;

    private Task task;
}
