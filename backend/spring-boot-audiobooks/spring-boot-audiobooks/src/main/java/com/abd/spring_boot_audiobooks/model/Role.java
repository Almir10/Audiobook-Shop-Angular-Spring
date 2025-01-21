package com.abd.spring_boot_audiobooks.model;

public enum Role {

    USER, //default role
    ADMIN, //can use CRUD operations
    SYSTEM_MANAGER // used for internal API operations
}
