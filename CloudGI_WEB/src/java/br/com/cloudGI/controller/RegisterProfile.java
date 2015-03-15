package br.com.cloudGI.controller;

import br.com.cloudGI.entity.Profile;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.com.cloudGI.dao.impl.DAO_BD_Derby;

/**
 *
 * @author Poliana Nascimento
 */
@ViewScoped
@ManagedBean(name = "registerProfile")
@SessionScoped
public class RegisterProfile implements Serializable {

    private String name;
    private String password;
    private String confirmPassword;
    private String email;
    private String login;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void displayLocation() {
        FacesMessage msg;
        if (!name.isEmpty() && !password.isEmpty() && !email.isEmpty() && !login.isEmpty()) {
            Profile perfil = new Profile();
            DAO_BD_Derby bd = new DAO_BD_Derby();
            perfil.setEmail(email);
            perfil.setLogin(login);
            perfil.setName(name);
            perfil.setPassword(password);

            if (!password.equals(confirmPassword)) {
                msg = new FacesMessage("Dados não Salvos", "Senhas não conferem");
            } else {

                ArrayList<String> listaUser = bd.listLogin();
                Boolean result, userOK = true;
                for (String listaUser1 : listaUser) {
                    if (login.equalsIgnoreCase(listaUser1)) {
                        userOK = false;
                        break;
                    }
                    userOK = true;
                }
                if (userOK) {
                    result = bd.saveProfile(perfil);
                    if (result) {
                        msg = new FacesMessage("Dados Salvos", "Cadastro realizado com sucesso!");
                    } else {
                        msg = new FacesMessage("Dados não Salvos", "Ocorreu um erro inesperado");
                    }
                } else {
                    msg = new FacesMessage("Dados não Salvos", "Usuario já existe no sistema");
                }
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preenchimento Obrigatório *", "Preencher todos os campos");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
