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
public class Instance {

    private String ip;
    private int id;
    private String name;
    private String status;
    private String id_flavor;
    private String id_image;
    private int id_instanceUser;

    public int getId_instanceUser() {
        return id_instanceUser;
    }

    public void setId_usuarioInstancia(int id_instanceUser) {
        this.id_instanceUser = id_instanceUser;
    }
    

    public String getId_flavor() {
        return id_flavor;
    }

    public void setId_flavor(String id_flavor) {
        this.id_flavor = id_flavor;
    }

    public String getId_image() {
        return id_image;
    }

    public void setId_image(String id_image) {
        this.id_image = id_image;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
