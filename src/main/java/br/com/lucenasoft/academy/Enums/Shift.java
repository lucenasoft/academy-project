package br.com.lucenasoft.academy.Enums;

public enum Shift {
    DIURNO("Diurno"),
    NOTURNO("Noturno");

    private String turno;

    private Shift(String turno) {
        this.turno = turno;
    }
}
