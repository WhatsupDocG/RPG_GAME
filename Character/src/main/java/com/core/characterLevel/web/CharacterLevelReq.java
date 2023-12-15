package com.core.characterLevel.web;

import javax.validation.constraints.NotEmpty;

public class CharacterLevelReq {
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
