package com.jwtauthentication.utils;

public class EssConstants {
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
        public static final String USER_NOT_CREATED = "User not created";
        public static final String USER_NOT_UPDATED = "User not updated";
        public static final String USER_NOT_DELETED = "User not deleted";

        public static final String CONFIRM_PASSWORD_DID_NOT_MATCH = "Password and Confirm Password doesn't match";
        public static final String EMAIL_EXISTS = "Email already exists";

        public static final String NOT_LOGGED_IN = "You need to login first";
        public static final String AUTHORIZATION_FAILED = "Authorization failed";
        public static final String INVALID_CREDENTIALS = "Invalid credentials";
        public static final String ACCOUNT_LOCKED = "Account is locked. Please contact HR.";
        public static final String ACCOUNT_DISABLED = "Account is disabled. Please contact HR.";
        public static final String ACCOUNT_EXPIRED = "Account has expired. Please contact HR.";

        public static final String LOGIN_FAILED = "Login failed";
        public static final String REGISTRATION_FAILED = "Registration failed";

        public static final String PASSWORD_RESET_FAILED = "Password reset failed";
        public static final String PROFILE_UPDATE_FAILED = "Profile update failed";

    }

    public static final class EmailSuccess {
        public static final String VERIFICATION_EMAIL_SENT = "Verification email sent";
        public static final String EMAIL_VERIFIED = "Email verified successfully";
        public static final String EMAIL_VERIFICATION_FAILED = "Email verification failed";
        public static final String PASSWORD_RESET_EMAIL_SENT = "Password reset link sent to email";
    }

}

