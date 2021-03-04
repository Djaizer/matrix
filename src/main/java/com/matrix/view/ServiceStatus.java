package com.matrix.view;

public enum ServiceStatus {

    RUNNING("green"),
    STOPPED("red");

    public String status;

    ServiceStatus(String status) {
        this.status = status;
    }

}
