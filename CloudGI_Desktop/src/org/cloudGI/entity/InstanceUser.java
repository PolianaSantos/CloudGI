/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cloudGI.entity;

/**
 *
 * @author Poliana Nascimento
 */
public class InstanceUser {

    private int id;
    private int id_flavor;
    private int id_user;
    private int id_service;
    private int id_image;
    private int id_fault;
    private String name;

    public int getId_fault() {
        return id_fault;
    }

    public void setId_fault(int id_falha) {
        this.id_fault = id_falha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_flavor() {
        return id_flavor;
    }

    public void setId_flavor(int id_flavor) {
        this.id_flavor = id_flavor;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_usuario) {
        this.id_user = id_usuario;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_servico) {
        this.id_service = id_servico;
    }

    public int getId_image() {
        return id_image;
    }

    public void setId_image(int id_imagem) {
        this.id_image = id_imagem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
