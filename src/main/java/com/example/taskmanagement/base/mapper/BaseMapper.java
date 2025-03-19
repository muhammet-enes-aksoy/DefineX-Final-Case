package com.example.taskmanagement.base.mapper;

import com.example.taskmanagement.base.dto.BaseDto;
import com.example.taskmanagement.base.entity.BaseEntity;
import jakarta.persistence.MappedSuperclass;

import java.util.List;

@MappedSuperclass
public interface BaseMapper<E extends BaseEntity, D extends BaseDto> {

    E converToEntity(D dto);
    D converToDto(E entity);
    List<D> converToDtoList(List<E> entityList);
    List<E> converToEntityList(List<E> dtoList);
}