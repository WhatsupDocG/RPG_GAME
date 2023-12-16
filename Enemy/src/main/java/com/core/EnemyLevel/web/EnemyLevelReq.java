package com.core.EnemyLevel.web;

import javax.validation.constraints.NotEmpty;

public class EnemyLevelReq {
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
