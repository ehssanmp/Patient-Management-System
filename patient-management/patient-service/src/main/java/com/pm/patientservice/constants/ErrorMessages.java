package com.pm.patientservice.constants;

public final class ErrorMessages {
    private ErrorMessages() {}

    public static final String
            VALIDATION_FAILED =
            "Validation failed for one or more fields";

    public static final String
            EMAIL_ALREADY_EXISTS =
            "The email address provided is already registered.";
    public static final String
            PATIENT_NOT_FOUND =
            "Patient with the given ID was not found.";
    public static final String
            INTERNAL_SERVER_ERROR = "An unexpected error occurred.";
}
