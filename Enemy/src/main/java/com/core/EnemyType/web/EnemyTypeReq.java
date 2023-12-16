package com.core.EnemyType.web;

import javax.validation.constraints.NotEmpty;

public class EnemyTypeReq {
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
