package com.corpConnect.utils.constants;

public class LogConstants {

    public static String getAuditorNotFoundMessage(String className, Long id, String specificMessage) {
        return buildMessage("Auditor Not Found", specificMessage, "Setting 'createdBy' or 'lastUpdatedBy' to the value 'system' for " + className + " with ID-" + id);
    }

    public static String getCreatedSuccessfullyMessage(String className, String fieldName, Object fieldValue, String specificMessage) {
        return buildMessage("Created Successfully", specificMessage, "Attempt to create a " + className + " with " + fieldName + "-" + fieldValue);
    }

    public static String getAlreadyExistsWhileCreatingMessage(String className, Object field, String specificMessage) {
        return buildMessage("Already Exists", specificMessage, "Attempt to create a " + className + " with value '" + field + "'");
    }

    public static String getUpdatedSuccessfullyMessage(String className, String fieldName, Object fieldValue, String toUpdateField, Object toUpdateValue, String specificMessage) {
        return buildMessage("Updated Successfully", specificMessage, "Attempt to update a " + className + " of " + fieldName + "-" + fieldValue + " with " + toUpdateField + "-" + toUpdateValue);
    }

    public static String getAlreadyExistsWhileUpdatingMessage(String className, Object field, String specificMessage) {
        return buildMessage("Already Exists", specificMessage, "Attempt to update a " + className + " with value '" + field + "'");
    }

    public static String getAlreadyUpdatedByIdMessage(String className, Long id, String specificMessage) {
        return buildMessage("Already Updated", specificMessage, "Attempt to update a " + className + " of ID-" + id);
    }

    public static String getIsUsedSomewhereMessage(String className, String fieldName, Object fieldValue, String specificMessage) {
        return buildMessage("Is Used", specificMessage, "Attempt to delete a " + className + " with " + fieldName + "-" + fieldValue);
    }

    public static String getNotFoundMessage(String className, String action, String fieldName, Object fieldValue, String specificMessage) {
        return buildMessage("Not Found", specificMessage, "Attempt to " + action + " " + className + " with " + fieldName + "-" + fieldValue);
    }

    public static String getDeletedSuccessfullyMessage(String className, String deleteType, String fieldName, Object fieldValue, String specificMessage) {
        return buildMessage("Deleted " + deleteType, specificMessage, "Attempt to delete a " + className + " with " + fieldName + "-" + fieldValue);
    }

    // Helper method to dynamically build the message
    private static String buildMessage(String action, String specificMessage, String baseMessage) {
        if (specificMessage != null && !specificMessage.isEmpty()) {
            return action + " [" + specificMessage + "]: " + baseMessage;
        }
        return action + ": " + baseMessage;
    }
}
