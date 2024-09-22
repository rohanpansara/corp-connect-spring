package com.employee_self_service.dtos.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO implements Comparable<BaseDTO>, Serializable {

    private Long id;

    @LastModifiedBy
    private String lastUpdatedBy;
    @LastModifiedDate
    private String lastUpdatedDate;

    @CreatedBy
    private String createdBy;
    @CreatedDate
    private String createdDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDTO that = (BaseDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[id=" + id + "]";
    }

    @Override
    public int compareTo(BaseDTO o) {
        if (o == null) {
            return 1;
        }
        return Long.compare(id, o.id);
    }
}
