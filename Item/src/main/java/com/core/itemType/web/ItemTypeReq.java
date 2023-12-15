package com.core.itemType.web;

import javax.validation.constraints.NotEmpty;

public class ItemTypeReq {
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
