package com.example.permissionmodule.enums;

public enum PermissionStatus {
    WAITING_FOR_APPROVAL(0,"WAITING_FOR_APPROVAL"),
    APPROVED(1,"APPROVED"),
    REJECTED(2,"REJECTED");

    private final int value;
    private final String key;

    PermissionStatus(int value, String key) {
        this.value = value;
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }


}
