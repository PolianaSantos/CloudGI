/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cloudGI.entity;
import com.jcraft.jsch.*; 
/**
 *
 * @author Poliana Nascimento
 */
public class MPInfoUser implements UserInfo{

    @Override
    public String getPassword(){ 
        return "openstack123"; 
    }

    @Override
    public boolean promptYesNo(String str){
        return true;
    }

    @Override
    public void showMessage(String message){

    }

    @Override
    public String getPassphrase() {
        return null;
    }

    @Override
    public boolean promptPassphrase(String paramString) {
        return true;
    }

    @Override
    public boolean promptPassword(String message){
        return true;
    }   
}
