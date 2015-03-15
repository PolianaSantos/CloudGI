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
public class ServerUser {
    String user;
    String host;
    int port;

    public ServerUser() {
        this.user = "devstack";
        this.host = "192.168.25.26";
        //this.host = "10.103.17.59";
        this.port = 22;
    }
    
    

    public String getUser() {
        return user;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
    
    
}
