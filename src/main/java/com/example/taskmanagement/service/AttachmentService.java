package com.example.taskmanagement.service;

import com.example.taskmanagement.base.service.BaseEntityService;
import com.example.taskmanagement.dto.attachment.AttachmentDto;
import com.example.taskmanagement.dto.attachment.AttachmentUpdateDto;
import com.example.taskmanagement.dto.task.TaskDto;
import com.example.taskmanagement.entity.Attachment;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.exception.AttachmentNotFoundException;
import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.mapper.AttachmentMapper;
import com.example.taskmanagement.mapper.TaskMapper;
import com.example.taskmanagement.repository.AttachmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class AttachmentService extends BaseEntityService<Attachment, AttachmentRepository> {

    private final TaskService taskService;

    @Value("${app.attachments.dir:attachments}")
    private String attachmentDirectory;

    public AttachmentService(AttachmentRepository repository, TaskService taskService) {
        super(repository);
        this.taskService = taskService;
    }

    // Dosya yükleme işlemi
    @Transactional
    public AttachmentDto uploadAttachment(Long taskId, MultipartFile file) throws IOException {
        Task task = taskService.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        // Dosya ismini benzersiz hale getirme
        String savedFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // Dosya dizinini kontrol et ve oluştur
        File directory = new File(attachmentDirectory);
        if (!directory.exists()) {
            directory.mkdirs();  // Dizin yoksa oluştur
        }

        // Dosyayı hedef dizine kaydet
        File destination = new File(directory, savedFileName);
        file.transferTo(destination);

        // Attachment nesnesi oluştur ve ilişkilendir
        Attachment attachment = new Attachment();
        attachment.setFileName(file.getOriginalFilename());
        attachment.setFilePath(destination.getAbsolutePath());
        attachment.setTask(task);
        task.getAttachments().add(attachment);

        // Task DTO'yu güncelle


        // Attachment veritabanına kaydet
        return AttachmentMapper.MAPPER.converToDto(super.save(attachment));
    }

    // Dosya güncelleme işlemi
    @Transactional
    public AttachmentDto updateAttachment(Long id, MultipartFile file, AttachmentUpdateDto attachmentUpdateDto) throws IOException {
        // Attachment'ı bul
        Attachment attachment = super.findById(id)
                .orElseThrow(() -> new AttachmentNotFoundException("Attachment not found"));

        // Dosya ismini güncelle
        attachment.setFileName(attachmentUpdateDto.getNewFileName());

        // Dosya dizinini kontrol et ve oluştur
        File directory = new File(attachmentDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Dosyayı güncelle
        File destination = new File(directory, attachment.getFileName());
        file.transferTo(destination);

        // Yeni dosya yolunu güncelle
        attachment.setFilePath(destination.getAbsolutePath());

        // Attachment'ı veritabanına kaydet
        return AttachmentMapper.MAPPER.converToDto(super.save(attachment));
    }

    @Transactional
    public void deleteAttachment(Long id) {
        Attachment attachment = super.findById(id)
                .orElseThrow(() -> new AttachmentNotFoundException("Attachment not found"));

        File file = new File(attachment.getFilePath());
        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                throw new RuntimeException("Failed to delete the file.");
            }

            super.delete(attachment);
        }
    }
}