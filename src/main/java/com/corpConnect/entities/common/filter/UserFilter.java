package com.corpConnect.entities.common.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserFilter extends BaseFilter {

    private String role;
    private boolean accountUnlocked;
    private boolean accountEnabled;
    private boolean accountNonExpired;

}
