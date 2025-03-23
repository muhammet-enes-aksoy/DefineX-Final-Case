package com.example.taskmanagement.ControllerTest;

import com.example.taskmanagement.controller.AttachmentController;
import com.example.taskmanagement.dto.attachment.AttachmentDto;
import com.example.taskmanagement.dto.attachment.AttachmentUpdateDto;
import com.example.taskmanagement.service.AttachmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AttachmentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AttachmentService attachmentService;

    @InjectMocks
    private AttachmentController attachmentController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(attachmentController).build();
    }

    @Test
    @DisplayName("Create attachment - success")
    void testCreateAttachment() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Test content".getBytes());
        AttachmentDto responseDto = AttachmentDto.builder().id(1L).filePath("/uploads/test.txt").fileName("test.txt").build();

        when(attachmentService.uploadAttachment(1L, file)).thenReturn(responseDto);

        mockMvc.perform(multipart("/api/v1/attachments/task/1")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.filePath").value("/uploads/test.txt"))
                .andExpect(jsonPath("$.data.fileName").value("test.txt"));
    }

    @Test
    @DisplayName("Update attachment - success")
    void testUpdateAttachment() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "updated.txt", "text/plain", "Updated content".getBytes());
        AttachmentUpdateDto updateDto = AttachmentUpdateDto.builder().newFileName("updated.txt").build();
        AttachmentDto responseDto = AttachmentDto.builder().id(1L).filePath("/uploads/updated.txt").fileName("updated.txt").build();

        when(attachmentService.updateAttachment(eq(1L),  any(AttachmentUpdateDto.class))).thenReturn(responseDto);

        mockMvc.perform(multipart("/api/v1/attachments/1")

                        .param("newFileName", "updated.txt"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.fileName").value("updated.txt"));
    }

    @Test
    @DisplayName("Delete attachment - success")
    void testDeleteAttachment() throws Exception {
        doNothing().when(attachmentService).deleteAttachment(1L);

        mockMvc.perform(delete("/api/v1/attachments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Attachment deleted!"));
    }
}
