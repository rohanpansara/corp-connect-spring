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

    @NotBlank(message = "Name must not be blank")
    @Length(max = 255, message = "Name must be less than or equal to 150 characters.")
    private String name;

}

