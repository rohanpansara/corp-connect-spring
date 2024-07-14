package com.jwtauthentication.entities.common;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class NamedEntity extends BaseEntity {

    private String name;

    public NamedEntity() {
        super();
    }

    public NamedEntity(String name) {
        this();
        setName(name);
    }

    public NamedEntity(String name, Long id) {
        this(name);
        setId(id);
    }
}

