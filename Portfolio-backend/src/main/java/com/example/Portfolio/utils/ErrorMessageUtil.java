package com.example.Portfolio.utils;

public class ErrorMessageUtil {
    public static final String PATH_VARIABLE_ERROR = "The path variable's data type received is invalid";
    public static final String NO_ADMINS = "There are no admins available";
    public static final String ADMIN_NOT_FOUND = "No admin was found linked to the ID provided";
    public static final String INSUFFICIENT_DATA = "Insufficient data received to process request";
    public static final String INVALID_EMAIL = "The email received is invalid";
    public static final String USERNAME_ALREADY_EXISTS = "The username received is not available";
    public static final String EMAIL_ALREADY_EXISTS = "The email received is not available";
    public static final String NO_PROJECTS = "There are no projects available";
    public static final String PROJECT_NOT_FOUND = "No project was found linked to the project provided";
    public static final String TITLE_ALREADY_EXISTS = "The project title received is not available";
    public static final String CODE_NOT_PERSONAL = "The code for the project is not public";
    public static final String CONSTRAINT_ERROR = "There was an error with data received, try again or contact administrators";
    public static final String TECHNOLOGIES_PARSE_ERROR = "Technologies weren't received in the appropiate format. Please, send them as an array";
    public static final String MALFORMED_TOKEN = "Token was expired or malformed";
    public static final String EXPIRED_TOKEN = "Token expired";
    public static final String UNSUPPORTED_TOKEN = "Token unsupported";
    public static final String INVALID_TOKEN = "Token was invalid";
    public static final String MISSING_TOKEN = "Token was not received";
}
