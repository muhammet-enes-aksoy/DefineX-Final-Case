package com.example.taskmanagement.base.service;

import com.example.taskmanagement.base.entity.BaseAdditionalFields;
import com.example.taskmanagement.base.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity, R extends JpaRepository<E, Long>> {

    private final R repository;

    public List<E> findAll() {
        return repository.findAll().stream()
                .filter(entity -> entity.getBaseAdditionalFields().isActive())
                .collect(Collectors.toList());
    }

    public Optional<E> findById(Long id) {
        return repository.findById(id)
                .filter(entity -> entity.getBaseAdditionalFields().isActive());

    }

    public E save(E entity) {
        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();
        if (baseAdditionalFields == null) {
            baseAdditionalFields = new BaseAdditionalFields();
            baseAdditionalFields.setCreateDate(LocalDateTime.now());
            baseAdditionalFields.setCreatedBy(0L);
        }

        if (entity.getId() == null) {
            baseAdditionalFields.setCreateDate(LocalDateTime.now());
            baseAdditionalFields.setCreatedBy(0L);
        }

        baseAdditionalFields.setUpdateDate(LocalDateTime.now());
        baseAdditionalFields.setUpdatedBy(0L);
        baseAdditionalFields.setActive(true);
        entity.setBaseAdditionalFields(baseAdditionalFields);
        entity = repository.save(entity);

        return entity;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(E entity) {
        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();
        if (baseAdditionalFields == null) {
            baseAdditionalFields = new BaseAdditionalFields();
        }

        baseAdditionalFields.setActive(false);
        baseAdditionalFields.setUpdateDate(LocalDateTime.now());

        entity.setBaseAdditionalFields(baseAdditionalFields);

        repository.save(entity);

    }

    public E findByIdWithControl(Long id) {
        return findById(id)
                .filter(entity -> entity.getBaseAdditionalFields().isActive())
                .orElseThrow(() -> new RuntimeException("Entity not found or inactive"));
    }

    public boolean isExist(Long id) {
        return repository.existsById(id);
    }
}