package com.core.location.web;

import javax.validation.constraints.NotEmpty;

public class LocationReq {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
