package com.example.taskmanagement.dto.comment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentUpdateDto {
    private String newText;
}
