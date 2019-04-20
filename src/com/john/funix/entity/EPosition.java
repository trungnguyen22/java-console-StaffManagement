/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.john.funix.entity;

//enumerate position of a staff
public enum EPosition {
    HEAD(1),
    VICE_HEAD(2),
    STAFF(3);

    int type;

    EPosition(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static EPosition from(int type) {
        for (EPosition value : EPosition.values()) {
            if (value.getType() == type) {
                return value;
            }
        }
        return STAFF;
    }

    public static EPosition from(String type) {
        for (EPosition value : EPosition.values()) {
            if (value.toString().equals(type)) {
                return value;
            }
        }
        return STAFF;
    }

}
