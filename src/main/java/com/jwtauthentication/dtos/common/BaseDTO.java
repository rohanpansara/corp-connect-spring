package com.jwtauthentication.dtos.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO {

    private Integer id;

    @LastModifiedBy
    private String lastUpdatedBy;
    @LastModifiedDate
    private String lastUpdatedDate;

    private String createdBy;
    private String createdDate;
}
