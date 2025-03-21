package com.example.taskmanagement.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentUpdateDto {
    private String newText;
}
