package com.employee_self_service.utils;

public class EssConstants {

    private static String getDataIntegrityViolationMessage(String className){
        return "This " + className + " is already used. Please check again before deleting";
    }

    public static final class Record{
        public static final String RECORD_FOUND = "Record found successfully";
        public static final String RECORD_NOT_FOUND = "Record not found";
    }

    public static final class UserSuccess {
        public static final String USER_FOUND = "User found successfully";
        public static final String USER_CREATED = "User created successfully";
        public static final String USER_UPDATED = "User updated successfully";
        public static final String USER_DELETED = "User deleted successfully";

        public static final String USER_LIST_FOUND = "User list found successfully";

        public static final String LOGIN_SUCCESS = "Login successful";
        public static final String LOGOUT_SUCCESS = "Logout successful";

        public static final String REGISTRATION_SUCCESS = "Registration successful";

        public static final String PASSWORD_UPDATED_SUCCESSFULLY = "Password updated successfully";
        public static final String PASSWORD_RESET_SUCCESS = "Password reset successful";

        public static final String PROFILE_UPDATED_SUCCESSFULLY = "Profile updated successfully";

        public static final String USER_DEACTIVATED = "User account deactivated";
        public static final String USER_ACTIVATED = "User account activated";

        public static final String USER_ROLE_UPDATED = "User role updated successfully";
    }

    public static final class UserError {
        public static final String USER_NOT_FOUND = "User not found";

        public static final String CONFIRM_PASSWORD_DID_NOT_MATCH = "Password and Confirm Password doesn't match";
        public static final String EMAIL_EXISTS = "Email already exists";

        public static final String USER_ROLE_NOT_FOUND = "User Role not found";

        public static final String NOT_LOGGED_IN = "You need to login first";
        public static final String AUTHORIZATION_FAILED = "Authorization failed";
        public static final String INVALID_CREDENTIALS = "Invalid credentials";
        public static final String ACCOUNT_LOCKED = "Account is locked. Please contact HR.";
        public static final String ACCOUNT_DISABLED = "Account is disabled. Please contact HR.";
        public static final String ACCOUNT_EXPIRED = "Account has expired. Please contact HR.";

        public static final String LOGIN_FAILED = "Login failed";
        public static final String PASSWORD_RESET_FAILED = "Password reset failed";
        public static final String PROFILE_UPDATE_FAILED = "Profile update failed";

        public static final String DataIntegrityViolation = getDataIntegrityViolationMessage("user");

    }

    public static final class EmailSuccess {
        public static final String VERIFICATION_EMAIL_SENT = "Verification email sent";
        public static final String EMAIL_VERIFIED = "Email verified successfully";
        public static final String EMAIL_VERIFICATION_FAILED = "Email verification failed";
        public static final String PASSWORD_RESET_EMAIL_SENT = "Password reset link sent to email";
    }

    public static final class Holiday{
        public static final String HOLIDAY_CREATED = "Holiday created successfully";
        public static final String HOLIDAY_FOR_THE_DATE_EXISTS = "Holiday for the given date already exists";
        public static final String HOLIDAY_FOUND = "Holiday found successfully";
        public static final String HOLIDAY_NOT_FOUND = "Holiday not found";
        public static final String HOLIDAY_LIST_FOUND = "All holidays found successfully";
        public static final String HOLIDAY_LIST_EMPTY = "No holidays found";
        public static final String HOLIDAY_DELETED = "Holiday deleted successfully";
        public static final String TYPE_NOT_FOUND = "No such holiday type found";
        public static final String DataIntegrityViolation = getDataIntegrityViolationMessage("holiday");
    }

    public static final class HrAccessControl{
        public static final String USER_LOCKED = "User account was locked successfully";
        public static final String USER_UNLOCKED = "User account was unlocked successfully";
        public static final String USER_ENABLED = "User account was enabled successfully";
        public static final String USER_DISABLED = "User account was disabled successfully";
    }

    public static final class CompanyConfiguration{
        public static final String CONFIGURATION_CREATED = "Company configuration created successfully";
        public static final String CONFIGURATION_EXISTS = "Company configuration exists with same name";
        public static final String CONFIGURATION_FOUND = "Company configuration found successfully";
        public static final String CONFIGURATION_NOT_FOUND = "Company configuration not found";
        public static final String CONFIGURATION_DELETED = "Company configuration deleted successfully";
        public static final String CONFIGURATION_LIST_FOUND = "All company configurations found successfully";
        public static final String CONFIGURATION_LIST_EMPTY = "No company configurations found";
        public static final String DataIntegrityViolation = getDataIntegrityViolationMessage("company configuration");
    }

    public static final class JobTitles{
        public static final String JOB_TITLE_FOUND = "Job title found successfully";
        public static final String JOB_TITLE_NOT_FOUND = "Job title not found";
        public static final String JOB_TITLE_UPDATED = "Job title updated successfully";
        public static final String JOB_TITLE_DELETED = "Job title deleted successfully";
        public static final String JOB_TITLE_ALREADY_EXISTS = "Job title with the same name already exists";
        public static final String JOB_TITLE_LIST_FOUND = "All job titles found successfully";
        public static final String DataIntegrityViolation = getDataIntegrityViolationMessage("job title");
    }

}

