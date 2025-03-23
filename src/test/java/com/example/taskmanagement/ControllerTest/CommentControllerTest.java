package com.example.taskmanagement.ControllerTest;

import com.example.taskmanagement.controller.CommentController;
import com.example.taskmanagement.dto.comment.CommentCreateDto;
import com.example.taskmanagement.dto.comment.CommentDto;
import com.example.taskmanagement.dto.comment.CommentUpdateDto;
import com.example.taskmanagement.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CommentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Test
    @DisplayName("Create comment - success")
    void testCreateComment() throws Exception {
        CommentCreateDto createDto = CommentCreateDto.builder().text("Test comment").build();
        CommentDto responseDto = CommentDto.builder().id(1L).text("Test comment").build();

        when(commentService.createComment(1L, 1L, createDto)).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/comments/task/1/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.text").value("Test comment"));
    }

    @Test
    @DisplayName("Update comment - success")
    void testUpdateComment() throws Exception {
        CommentUpdateDto updateDto = CommentUpdateDto.builder().newText("Updated comment").build();
        CommentDto responseDto = CommentDto.builder().id(1L).text("Updated comment").build();

        when(commentService.updateComment(1L, updateDto)).thenReturn(responseDto);

        mockMvc.perform(put("/api/v1/comments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.text").value("Updated comment"));
    }

    @Test
    @DisplayName("Delete comment - success")
    void testDeleteComment() throws Exception {
        doNothing().when(commentService).deleteComment(1L);

        mockMvc.perform(delete("/api/v1/comments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Comment deleted!"));
    }
}
