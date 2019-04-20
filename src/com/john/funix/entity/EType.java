package com.john.funix.entity;

public enum EType {

    STAFF("Staff"),
    TEACHER("Teacher");

    String type;
    EType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static EType from(String type) {
        for (EType value : EType.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return STAFF;
    }
}
