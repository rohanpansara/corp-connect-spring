package com.corpConnect.utils.constants;

import com.corpConnect.utils.functions.MessageCreator;

public class MessageConstants {

    private static String getDataIntegrityViolationMessage(String className) {
        return "This " + className + " is already used. Please check again before deleting";
    }

    public static final class Record {
        public static final String RECORD_FOUND = MessageCreator.getFoundMessage("Record");
        public static final String RECORD_NOT_FOUND = MessageCreator.getNotFoundMessage("Record");
    }

    public static final class UserSuccess {
        public static final String USER_FOUND = MessageCreator.getFoundMessage("User");
        public static final String USER_CREATED = MessageCreator.getCreatedMessage("User");
        public static final String USER_UPDATED = MessageCreator.getUpdatedMessage("User");
        public static final String USER_DELETED = MessageCreator.getDeletedMessage("User", false);
        public static final String USER_DELETED_PERMANENTLY = MessageCreator.getDeletedMessage("User", true);

        public static final String USER_LIST_FOUND = MessageCreator.getListFoundMessage("User");
        public static final String USER_LIST_EMPTY = MessageCreator.getListEmptyMessage("User");

        public static final String LOGIN_SUCCESS = MessageCreator.getLoggedInMessage();

        public static final String PASSWORD_UPDATED_SUCCESSFULLY = MessageCreator.getCustomCRUDMessage("Password", "updated");
        public static final String PASSWORD_RESET_SUCCESS = MessageCreator.getCustomCRUDMessage("Password", "reset");
        public static final String PROFILE_UPDATED_SUCCESSFULLY = MessageCreator.getUpdatedMessage("User profile");
    }

    public static final class UserError {
        public static final String USER_NOT_FOUND = MessageCreator.getNotFoundMessage("User");

        public static final String CONFIRM_PASSWORD_DID_NOT_MATCH = "Password and Confirm Password doesn't match";
        public static final String EMAIL_EXISTS = MessageCreator.getExistsMessage("User", "email");

        public static final String USER_ROLE_NOT_FOUND = MessageCreator.getNotFoundMessage("User role");

        public static final String USER_NOT_LOGGED_IN = "You need to login first";
        public static final String AUTHORIZATION_FAILED = "Authorization failed";
        public static final String INVALID_CREDENTIALS = "Invalid credentials";
        public static final String ACCOUNT_LOCKED = "Account is locked. Please contact HR.";
        public static final String ACCOUNT_DISABLED = "Account is disabled. Please contact HR.";
        public static final String ACCOUNT_EXPIRED = "Account has expired. Please contact HR.";

        public static final String DataIntegrityViolation = getDataIntegrityViolationMessage("user");

    }

    public static final class EmailSuccess {
        public static final String VERIFICATION_EMAIL_SENT = "Verification email sent";
        public static final String EMAIL_VERIFIED = "Email verified successfully";
        public static final String EMAIL_VERIFICATION_FAILED = "Email verification failed";
        public static final String PASSWORD_RESET_EMAIL_SENT = "Password reset link sent to email";
    }

    public static final class Holiday {
        public static final String HOLIDAY_CREATED = MessageCreator.getCreatedMessage("Holiday");
        public static final String HOLIDAY_FOR_THE_DATE_EXISTS = MessageCreator.getExistsMessage("Holiday", "date");
        public static final String HOLIDAY_OF_THE_NAME_EXISTS = MessageCreator.getExistsMessage("Holiday", "name");
        public static final String HOLIDAY_FOUND = MessageCreator.getFoundMessage("Holiday");
        public static final String HOLIDAY_NOT_FOUND = MessageCreator.getNotFoundMessage("Holiday");
        public static final String HOLIDAY_LIST_FOUND = MessageCreator.getListFoundMessage("Holiday");
        public static final String HOLIDAY_LIST_EMPTY = MessageCreator.getListEmptyMessage("Holiday");
        public static final String HOLIDAY_DELETED = MessageCreator.getDeletedMessage("Holiday", false);
        public static final String TYPE_NOT_FOUND = MessageCreator.getNotFoundMessage("Holiday type");
        public static final String DataIntegrityViolation = getDataIntegrityViolationMessage("holiday");
    }

    public static final class HrAccessControl {
        public static final String USER_LOCKED = "User account was locked successfully";
        public static final String USER_UNLOCKED = "User account was unlocked successfully";
        public static final String USER_ENABLED = "User account was enabled successfully";
        public static final String USER_DISABLED = "User account was disabled successfully";
    }

    public static final class CompanyConfiguration {
        public static final String CONFIGURATION_CREATED = "Company configuration created successfully";
        public static final String CONFIGURATION_EXISTS = "Company configuration exists with same name";
        public static final String CONFIGURATION_FOUND = "Company configuration found successfully";
        public static final String CONFIGURATION_NOT_FOUND = "Company configuration not found";
        public static final String CONFIGURATION_DELETED = "Company configuration deleted successfully";
        public static final String CONFIGURATION_LIST_FOUND = "All company configurations found successfully";
        public static final String CONFIGURATION_LIST_EMPTY = "No company configurations found";
        public static final String DataIntegrityViolation = getDataIntegrityViolationMessage("company configuration");
    }

    public static final class JobTitle {
        public static final String JOB_TITLE_CREATED = MessageCreator.getCreatedMessage("Job title");
        public static final String JOB_TITLE_FOUND = MessageCreator.getFoundMessage("Job title");
        public static final String JOB_TITLE_NOT_FOUND = MessageCreator.getNotFoundMessage("Job title");
        public static final String JOB_TITLE_UPDATED = MessageCreator.getUpdatedMessage("Job title");
        public static final String JOB_TITLE_DELETED = MessageCreator.getDeletedMessage("Job title", false);
        public static final String JOB_TITLE_DELETED_PERMANENTLY = MessageCreator.getDeletedMessage("Job title", true);
        public static final String JOB_TITLE_ALREADY_EXISTS = MessageCreator.getExistsMessage("Job title", "name");
        public static final String JOB_TITLE_LIST_FOUND = MessageCreator.getListFoundMessage("job title");
        public static final String DataIntegrityViolation = getDataIntegrityViolationMessage("job title");
    }

    public static final class WorkShift {
        public static final String WORK_SHIFT_FOUND = MessageCreator.getFoundMessage("Work shift");
        public static final String WORK_SHIFT_LIST_FOUND = MessageCreator.getListFoundMessage("work shift");
        public static final String WORK_SHIFT_NOT_FOUND = MessageCreator.getNotFoundMessage("Work shift");
        public static final String WORK_SHIFT_CREATED = MessageCreator.getCreatedMessage("Work shift");
        public static final String WORK_SHIFT_DELETED = MessageCreator.getDeletedMessage("Work shift", false);
        public static final String WORK_SHIFT_UPDATED = MessageCreator.getUpdatedMessage("Work shift");
        public static final String WORK_SHIFT_ALREADY_EXISTS = MessageCreator.getExistsMessage("Work shift", "name");
    }

}

