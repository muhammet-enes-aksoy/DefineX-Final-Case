package com.example.taskmanagement.controller;

import com.example.taskmanagement.base.RestResponse;
import com.example.taskmanagement.dto.comment.CommentCreateDto;
import com.example.taskmanagement.dto.comment.CommentDto;
import com.example.taskmanagement.dto.comment.CommentUpdateDto;
import com.example.taskmanagement.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    
    @PostMapping
    public ResponseEntity<RestResponse<CommentDto>> createComment(@PathVariable Long taskId,
                                                                  @PathVariable Long userId,
                                                                  @RequestBody CommentCreateDto commentCreateDto) {
        return ResponseEntity.ok(RestResponse.of(commentService.createComment(taskId, userId, commentCreateDto)));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<RestResponse<CommentDto>> updateComment(@PathVariable Long CommentId,
                                                                  @RequestBody CommentUpdateDto commentUpdateDto) {
        return ResponseEntity.ok(RestResponse.of(commentService.updateComment(CommentId, commentUpdateDto)));
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<RestResponse<String>> deleteComment(@PathVariable Long CommentId) {
        commentService.deleteComment(CommentId);
        return ResponseEntity.ok(RestResponse.of("Comment deleted!"));
    }
}
