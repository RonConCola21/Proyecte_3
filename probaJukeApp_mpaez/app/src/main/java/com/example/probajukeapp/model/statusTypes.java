package com.example.probajukeapp.model;

public enum statusTypes {
    WHITELIST('W'),
    BLACKLIST('B'),
    REQUEST('R'),
    ;

    char status;

    statusTypes(char status) {
        this.status = status;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
