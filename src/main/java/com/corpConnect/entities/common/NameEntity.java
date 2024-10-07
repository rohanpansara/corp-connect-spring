package com.corpConnect.entities.common;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

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

