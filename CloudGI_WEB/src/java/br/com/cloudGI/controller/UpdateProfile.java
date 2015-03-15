package br.com.cloudGI.controller;

import br.com.cloudGI.entity.Profile;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.com.cloudGI.dao.impl.DAO_BD_Derby;

/**
 *
 * @author Poliana Nascimento
 */
@ManagedBean
@ViewScoped
public class UpdateProfile implements Serializable {

    private int id;
    private String name;
    private String email;
    private String password;
    private String login;
    Profile user = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Perfil");
    DAO_BD_Derby bd = new DAO_BD_Derby();
    Profile us = bd.selectUser(user.getLogin());

    public UpdateProfile() {

        this.name = us.getName();
        this.email = us.getEmail();
        this.login = us.getLogin();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassord(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void displayLocation() {
        FacesMessage msg;
        if (!name.isEmpty() && !email.isEmpty()) {
            DAO_BD_Derby bd = new DAO_BD_Derby();
            Profile pf = new Profile();
            pf.setEmail(email);
            pf.setId(user.getId());
            pf.setName(name);
            boolean result = bd.updateProfile(pf);
            if (result) {
                msg = new FacesMessage("Dados Salvos", "Atualização realizada com sucesso!");
            } else {
                msg = new FacesMessage("Dados nao Salvos");
            }

        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preenchimento Obrigatório *", "Preencher todos os campos");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
