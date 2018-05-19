package br.com.alexcorporation.mockup.model;

import java.io.Serializable;

/**
 * Created by Alex on 17/05/2018.
 */

public class City implements Serializable{
    private String Nome;
    private String Estado;
    private String pontos;

    public City(String nome, String estado) {
        this.Nome = nome;
        this.Estado = estado;
    }

    public City() {
    }

    public String getNome() {
        return Nome;
    }

    public String getEstado() {
        return Estado;
    }

    public String getPontos() {
        return pontos;
    }

    public void setPontos(String pontos) {
        this.pontos = pontos;
    }
}
