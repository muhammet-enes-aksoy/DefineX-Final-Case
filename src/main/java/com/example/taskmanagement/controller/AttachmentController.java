package com.example.taskmanagement.controller;

import com.example.taskmanagement.base.RestResponse;
import com.example.taskmanagement.dto.attachment.AttachmentCreateDto;
import com.example.taskmanagement.dto.attachment.AttachmentDto;
import com.example.taskmanagement.dto.attachment.AttachmentUpdateDto;
import com.example.taskmanagement.entity.Attachment;
import com.example.taskmanagement.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/attachments")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping(value = "/task/{taskId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestResponse<AttachmentDto>> createAttachment(@PathVariable Long taskId,
                                                                        @RequestPart ("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(RestResponse.of(attachmentService.uploadAttachment(taskId, file)));
    }

    @PutMapping(value = "/{attachmentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestResponse<AttachmentDto>> updateAttachment(@PathVariable Long attachmentId,
                                                                        @RequestPart ("file") MultipartFile file,
                                                                  @RequestBody AttachmentUpdateDto attachmentUpdateDto) throws IOException {
        return ResponseEntity.ok(RestResponse.of(attachmentService.updateAttachment(attachmentId, file, attachmentUpdateDto)));
    }
    @DeleteMapping("/{attachmentId}")
    public ResponseEntity<RestResponse<String>> deleteAttachment(@PathVariable Long AttachmentId) {
        attachmentService.deleteAttachment(AttachmentId);
        return ResponseEntity.ok(RestResponse.of("Attachment deleted!"));
    }

}
