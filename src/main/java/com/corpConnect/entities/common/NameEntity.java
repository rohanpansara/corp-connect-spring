package com.corpConnect.entities.common;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class NameEntity extends BaseEntity {

    private String name;

    public NameEntity(String name) {
        this();
        setName(name);
    }

    public NameEntity(String name, Long id) {
        this(name);
        setId(id);
    }
}

