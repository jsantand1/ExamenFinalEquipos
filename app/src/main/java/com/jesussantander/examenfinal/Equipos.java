package com.jesussantander.examenfinal;

public class Equipos {
    public String marca;
    public String modelo;
    public String color;
    public Integer ram;
    public Integer procesador;
    public Integer discoduro;
    public Integer sistemaoperativo;
    public Integer tipo;
    public Integer foto;
    public String idDb;

    public Equipos(){}

    public Equipos(String marca, String modelo, String color, Integer ram, Integer procesador, Integer discoduro, Integer sistemaoperativo, Integer tipo, Integer foto) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.ram = ram;
        this.procesador = procesador;
        this.discoduro = discoduro;
        this.sistemaoperativo = sistemaoperativo;
        this.tipo = tipo;
        this.foto = foto;
    }
}
