/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cloudGI.entity;

/**
 *
 * @author Poliana Nascimento
 */
public class Flavor {

    private int id;
    private int id_open;
    private String name;

    public int getId_open() {
        return id_open;
    }

    public void setId_open(int id_open) {
        this.id_open = id_open;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
