package com.example.taskmanagement.base.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable, Cloneable, BaseEntityModel {

    @Embedded
    private BaseAdditionalFields baseAdditionalFields;

}