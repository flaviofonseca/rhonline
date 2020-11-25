package br.com.rhonline.app.funcionario;

import java.util.Arrays;

public enum Sexo {

    FEMININO("F"), MASCULINO("M"), OUTROS("O");

    private String valor;

    Sexo(String o) {
        this.valor = o;
    }

    public String getValor() {
        return valor;
    }

    public static Sexo sexoPeloValor(String valor) {
        return Arrays.asList(values()).stream().filter(s -> s.getValor().equals(valor)).findFirst().orElse(Sexo.OUTROS);
    }
}
