package com.core.spellLevel.web;

import javax.validation.constraints.NotEmpty;

public class SpellLevelReq {
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
