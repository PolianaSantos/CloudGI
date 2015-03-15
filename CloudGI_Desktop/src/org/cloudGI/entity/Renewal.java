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
public class Renewal {
    private int id_instanceUser;
    private int size;
    private int next;

    public int getId_instanceUser() {
        return id_instanceUser;
    }

    public void setId_instanceUser(int id_instanceUser) {
        this.id_instanceUser = id_instanceUser;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNext() {
        return next;
    }

    public void setProxima(int next) {
        this.next = next;
    }
    
    
}
