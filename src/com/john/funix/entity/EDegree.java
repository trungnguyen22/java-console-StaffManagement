/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.john.funix.entity;

//enumerate degree level of a teacher
public enum EDegree {
    BACHELOR(1),
    MASTER(2),
    DOCTOR(3);

    int type;

    EDegree(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static EDegree from(int type) {
        for (EDegree value : EDegree.values()) {
            if (value.getType() == type) {
                return value;
            }
        }
        return BACHELOR;
    }
}
