package com.corpConnect.utils.constants;

import com.corpConnect.utils.functions.MessageCreator;

public class MessageConstants {

    private static String getDataIntegrityViolationMessage(String className) {
        return "This " + className + " is already used. Please check again before deleting";
    }

    public static final class JWT {
        public static final String INVALID_TOKEN = MessageCreator.getJWTErrorMessage("Invalid", "The token is not valid for the user");
        public static final String SECURITY_EXCEPTION = MessageCreator.getJWTErrorMessage("Security" ,"The token's signature is not valid");
        public static final String MALFORMED_JWT_EXCEPTION = MessageCreator.getJWTErrorMessage("Malformed Jwt" ,"The token structure is not valid");
        public static final String EXPIRED_JWT_EXCEPTION = MessageCreator.getJWTErrorMessage("Expired Jwt" ,"The token has expired and is no longer valid");
        public static final String UNSUPPORTED_JWT_EXCEPTION = MessageCreator.getJWTErrorMessage("Unsupported Jwt" ,"The token type is not supported");
        public static final String ILLEGAL_ARGUMENT_EXCEPTION = MessageCreator.getJWTErrorMessage("Illegal Argument" ,"The token is missing claims or the claims are empty");
    }

    public static final class Record {
        public static final String RECORD_CREATED = MessageCreator.getCreatedMessage("Record");
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
        public static final String LOGOUT_SUCCESS = MessageCreator.getLoggedOutMessage();
        public static final String USER_SESSION_VERIFIED = MessageCreator.getSessionVerificationMessage(true);
        public static final String TOKEN_REFRESHED = "User token refreshed";

        public static final String PASSWORD_SET_SUCCESSFULLY = MessageCreator.getCustomCRUDMessage("Password", "set");
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

    public static final class OTP {
        public static final String OTP_VERIFIED = "OTP Verified Successfully";
        public static final String PENDING_OTP_FOUND = "Pending OTP Verification Found";
        public static final String PENDING_OTP_NOT_FOUND = "No Pending OTP Verification Found";
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
        public static final String CONFIGURATION_CREATED = MessageCreator.getCreatedMessage("Configuration");
        public static final String CONFIGURATION_ALREADY_EXISTS = MessageCreator.getExistsMessage("Configuration", "name");
        public static final String CONFIGURATION_FOUND = MessageCreator.getFoundMessage("Configuration");
        public static final String CONFIGURATION_NOT_FOUND = MessageCreator.getNotFoundMessage("Configuration");
        public static final String CONFIGURATION_UPDATED = MessageCreator.getUpdatedMessage("Configuration");
        public static final String CONFIGURATION_DELETED = MessageCreator.getDeletedMessage("Configuration", false);
        public static final String CONFIGURATION_DELETED_PERMANENTLY = MessageCreator.getDeletedMessage("Configuration", true);
        public static final String CONFIGURATION_LIST_FOUND = MessageCreator.getListFoundMessage("configuration");
        public static final String DataIntegrityViolation = getDataIntegrityViolationMessage("configuration");
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
        public static final String DataIntegrityViolation = getDataIntegrityViolationMessage("work shift");
    }

    public static final class Department {
        public static final String DEPARTMENT_FOUND = MessageCreator.getFoundMessage("Department");
        public static final String DEPARTMENT_LIST_FOUND = MessageCreator.getListFoundMessage("department");
        public static final String DEPARTMENT_NOT_FOUND = MessageCreator.getNotFoundMessage("Department");
        public static final String DEPARTMENT_CREATED = MessageCreator.getCreatedMessage("Department");
        public static final String DEPARTMENT_DELETED = MessageCreator.getDeletedMessage("Department", false);
        public static final String DEPARTMENT_UPDATED = MessageCreator.getUpdatedMessage("Department");
        public static final String DEPARTMENT_ALREADY_EXISTS = MessageCreator.getExistsMessage("Department", "name");
        public static final String DataIntegrityViolation = getDataIntegrityViolationMessage("department");
    }

    public static final class Meeting {
        public static final String MEETING_FOUND = MessageCreator.getFoundMessage("Meeting");
        public static final String MEETING_LIST_FOUND = MessageCreator.getListFoundMessage("Meeting");
        public static final String MEETING_NOT_FOUND = MessageCreator.getNotFoundMessage("Meeting");
        public static final String MEETING_CREATED = MessageCreator.getCreatedMessage("Meeting");
        public static final String MEETING_DELETED = MessageCreator.getDeletedMessage("Meeting", false);
        public static final String MEETING_UPDATED = MessageCreator.getUpdatedMessage("Meeting");
        public static final String MEETING_ALREADY_EXISTS = MessageCreator.getExistsMessage("Meeting", "time");
        public static final String DataIntegrityViolation = getDataIntegrityViolationMessage("Meeting");
    }

    public static final class MeetingRoom {
        public static final String MEETING_ROOM_FOUND = MessageCreator.getFoundMessage("Meeting room");
        public static final String MEETING_ROOM_LIST_FOUND = MessageCreator.getListFoundMessage("meeting room");
        public static final String MEETING_ROOM_NOT_FOUND = MessageCreator.getNotFoundMessage("Meeting room");
        public static final String MEETING_ROOM_CREATED = MessageCreator.getCreatedMessage("Meeting room");
        public static final String MEETING_ROOM_DELETED = MessageCreator.getDeletedMessage("Meeting room", false);
        public static final String MEETING_ROOM_UPDATED = MessageCreator.getUpdatedMessage("Meeting room");
        public static final String MEETING_ROOM_ALREADY_EXISTS = MessageCreator.getExistsMessage("Meeting room", "name");
        public static final String DataIntegrityViolation = getDataIntegrityViolationMessage("meeting room");
    }

    public static final class Leave {
        public static final String LEAVE_STATUS_NOT_FOUND = MessageCreator.getNotFoundMessage("Leave status");
    }

    public static final class LeaveType {
        public static final String LEAVE_TYPE_CREATED = MessageCreator.getCreatedMessage("Leave type");
        public static final String LEAVE_TYPE_FOUND = MessageCreator.getFoundMessage("Leave type");
        public static final String LEAVE_TYPE_NOT_FOUND = MessageCreator.getNotFoundMessage("Leave type");
        public static final String LEAVE_TYPE_UPDATED = MessageCreator.getUpdatedMessage("Leave type");
        public static final String LEAVE_TYPE_DELETED = MessageCreator.getDeletedMessage("Leave type", false);
        public static final String LEAVE_TYPE_DELETED_PERMANENTLY = MessageCreator.getDeletedMessage("Leave type", true);
        public static final String LEAVE_TYPE_ALREADY_EXISTS = MessageCreator.getExistsMessage("Leave type", "name");
        public static final String LEAVE_TYPE_LIST_FOUND = MessageCreator.getListFoundMessage("Leave type");
        public static final String DataIntegrityViolation = getDataIntegrityViolationMessage("Leave type");
    }

}

