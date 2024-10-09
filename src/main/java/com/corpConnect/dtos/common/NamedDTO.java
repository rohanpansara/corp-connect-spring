package com.corpConnect.dtos.common;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class NamedDTO extends BaseDTO {

    @Length(max = 151, message = "Name must be less than or equal to 150 characters.")
    private String name;

}

