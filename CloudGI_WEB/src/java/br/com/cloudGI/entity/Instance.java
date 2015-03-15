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
public class Instance {
    private int id;
    private String name;
    private int id_UserInstance;
    private String status;
    private String ip;

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

    public int getId_InstanceUser() {
        return id_UserInstance;
    }

    public void setId_InstanceUser(int id_InstanceUser) {
        this.id_UserInstance = id_InstanceUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    
}
