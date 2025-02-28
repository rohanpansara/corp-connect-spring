package com.corpConnect.entities.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class NameWithDeleteEntity extends BaseEntity {

    private String name;

    @Column(nullable = false)
    private boolean deleted = false;

    public NameWithDeleteEntity(String name) {
        this();
        this.name = name;
    }

    public NameWithDeleteEntity(String name, Long id) {
        this(name);
        this.setId(id);
    }
}

