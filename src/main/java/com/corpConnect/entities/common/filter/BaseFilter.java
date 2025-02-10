package com.corpConnect.entities.common.filter;

import com.corpConnect.utils.constants.CommonConstants;
import lombok.Data;

@Data
public class BaseFilter {

    private int pageNumber = CommonConstants.DEFAULT_PAGE_NUMBER;
    private int rowsPerPage = CommonConstants.DEFAULT_NUMBER_OF_ROWS;
    private Long requiredId;
    private String searchedString;

}
