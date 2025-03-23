/*package com.example.taskmanagement.ControllerTest;

import com.example.taskmanagement.controller.AttachmentController;
import com.example.taskmanagement.dto.attachment.AttachmentDto;
import com.example.taskmanagement.dto.attachment.AttachmentUpdateDto;
import com.example.taskmanagement.exception.AttachmentNotFoundException;
import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.service.AttachmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AttachmentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AttachmentService attachmentService;

    @InjectMocks
    private AttachmentController attachmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(attachmentController).build();
    }

    @Test
    void createAttachment() throws Exception {

        Long taskId = 1L;
        AttachmentDto attachmentDto = AttachmentDto.builder()
                .id(1L)
                .fileName("testFile.txt")
                .filePath("/path/to/file")
                .build();

        when(attachmentService.uploadAttachment(any(Long.class), any(MultipartFile.class))).thenReturn(attachmentDto);

        MockMultipartFile file = new MockMultipartFile("file", "testFile.txt", MediaType.TEXT_PLAIN_VALUE, "Test file content".getBytes());

        mockMvc.perform(multipart("/api/v1/attachments/task/{taskId}", taskId)
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.id").value(1L))
                .andExpect(jsonPath("$.response.fileName").value("testFile.txt"))
                .andExpect(jsonPath("$.response.filePath").value("/path/to/file"));

        verify(attachmentService, times(1)).uploadAttachment(any(Long.class), any(MultipartFile.class));
    }

    @Test
    void createAttachment_ThrowsTaskNotFoundException() throws Exception {

        Long taskId = 1L;
        when(attachmentService.uploadAttachment(any(Long.class), any(MultipartFile.class)))
                .thenThrow(new TaskNotFoundException("Task not found"));

        MockMultipartFile file = new MockMultipartFile("file", "testFile.txt", MediaType.TEXT_PLAIN_VALUE, "Test file content".getBytes());

        mockMvc.perform(multipart("/api/v1/attachments/task/{taskId}", taskId)
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isNotFound());

        verify(attachmentService, times(1)).uploadAttachment(any(Long.class), any(MultipartFile.class));
    }

    @Test
    void updateAttachment() throws Exception {

        Long attachmentId = 1L;
        AttachmentDto attachmentDto = AttachmentDto.builder()
                .id(1L)
                .fileName("updatedFile.txt")
                .filePath("/path/to/updatedFile")
                .build();

        AttachmentUpdateDto attachmentUpdateDto = AttachmentUpdateDto.builder()
                .newFileName("updatedFile.txt")
                .build();

        when(attachmentService.updateAttachment(any(Long.class), any(AttachmentUpdateDto.class))).thenReturn(attachmentDto);

        mockMvc.perform(put("/api/v1/attachments/{attachmentId}", attachmentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"newFileName\":\"updatedFile.txt\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.id").value(1L))
                .andExpect(jsonPath("$.response.fileName").value("updatedFile.txt"))
                .andExpect(jsonPath("$.response.filePath").value("/path/to/updatedFile"));

        verify(attachmentService, times(1)).updateAttachment(any(Long.class), any(AttachmentUpdateDto.class));
    }

    @Test
    void updateAttachment_ThrowsAttachmentNotFoundException() throws Exception {

        Long attachmentId = 1L;
        when(attachmentService.updateAttachment(any(Long.class), any(AttachmentUpdateDto.class)))
                .thenThrow(new AttachmentNotFoundException("Attachment not found"));

        mockMvc.perform(put("/api/v1/attachments/{attachmentId}", attachmentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"newFileName\":\"updatedFile.txt\"}"))
                .andExpect(status().isNotFound());

        verify(attachmentService, times(1)).updateAttachment(any(Long.class), any(AttachmentUpdateDto.class));
    }

    @Test
    void deleteAttachment() throws Exception {

        Long attachmentId = 1L;
        doNothing().when(attachmentService).deleteAttachment(any(Long.class));

        mockMvc.perform(delete("/api/v1/attachments/{attachmentId}", attachmentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value("Attachment deleted!"));

        verify(attachmentService, times(1)).deleteAttachment(any(Long.class));
    }

    @Test
    void deleteAttachment_ThrowsAttachmentNotFoundException() throws Exception {

        Long attachmentId = 1L;
        doThrow(new AttachmentNotFoundException("Attachment not found")).when(attachmentService).deleteAttachment(any(Long.class));

        mockMvc.perform(delete("/api/v1/attachments/{attachmentId}", attachmentId))
                .andExpect(status().isNotFound());

        verify(attachmentService, times(1)).deleteAttachment(any(Long.class));
    }
}*/