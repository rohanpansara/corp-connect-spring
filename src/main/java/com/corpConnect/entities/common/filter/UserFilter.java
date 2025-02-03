package com.corpConnect.entities.common.filter;

import com.corpConnect.utils.constants.CommonConstants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilter {

    private int pageNumber = CommonConstants.DEFAULT_PAGE_NUMBER;
    private int rowsPerPage = CommonConstants.DEFAULT_NUMBER_OF_ROWS;
    private String searchedString;
    private String role;
    private boolean isAccountUnlocked;
    private boolean isAccountEnabled;
    private boolean isAccountNonExpired;

}
