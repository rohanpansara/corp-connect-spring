package com.employee_self_service.entities.common;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class NamedEntity extends BaseEntity {

    private String name;

    public NamedEntity(String name) {
        this();
        setName(name);
    }

    public NamedEntity(String name, Long id) {
        this(name);
        setId(id);
    }
}

