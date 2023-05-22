package br.com.lucenasoft.academy.Enums;

public enum Status {
    ACTIVE("Active"),
    BRAIDED("Braided"),
    CANCELED("Canceled");

    private String status;

    private Status(String status) {
        this.status = status;
    }
}
