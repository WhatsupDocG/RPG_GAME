package com.core.spellType.web;

import javax.validation.constraints.NotEmpty;

public class SpellTypeReq {
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
