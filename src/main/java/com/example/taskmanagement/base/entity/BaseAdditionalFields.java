package com.example.taskmanagement.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class BaseAdditionalFields {
    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    @Column(name = "CREATED_BY")
    private Long createdBy;

    @Column(name = "UPDATED_BY")
    private Long updatedBy;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createDate;

    @Column(name = "UPDATED_DATE")
    private LocalDateTime updateDate;
}