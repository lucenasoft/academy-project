package br.com.lucenasoft.academy.Enums;

public enum Course {
    ADMINISTRACAO("Administracao"),
    INFORMATICA("Informatica"),
    ENGENHARIA("Egenharia"),
    ANALISTA_DE_SISTEMAS("Analista_de_Sistemas");

    private String curso;

    private Course(String curso) {
        this.curso = curso;
    }
}
